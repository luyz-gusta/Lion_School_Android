package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.component.HeaderScreen
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query


class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LionSchoolTheme {
                val siglaCurso = intent.getStringExtra("sigla")
                val nomeCurso = intent.getStringExtra("nome")
                Studentscreen(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}

@Composable
fun Studentscreen(curso:String, nomeCurso: String) {

    var yearStudentState by remember { mutableStateOf("") }

    var listaAlunos by remember { mutableStateOf(listOf<Student>()) }

    // Estado para controlar a opção selecionada
    var selectedOption by remember { mutableStateOf(1) }

    val context = LocalContext.current

    // Cria uma chamada para o endpoint
    val call = RetrofitFactory().getAlunosService().getAlunosByCurso(curso)

    // Executar a chamada
    call.enqueue(object  : Callback<StudentList> {
        override fun onResponse(
            call: Call<StudentList>,
            response: Response<StudentList>
        ) {
            listaAlunos = response.body()!!.informacoes
        }

        override fun onFailure(call: Call<StudentList>, t: Throwable) {
            Log.i("curso", "onFailure: ${t.message}")

        }
    })


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderScreen(context)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = nomeCurso,
                    color = colorResource(id = R.color.blue_default),
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                ToggleButton(selectedOption, onOptionSelected = { option ->
                    selectedOption = option
                    // Lógica para tratar a opção selecionada
                    when (option) {
                        1 -> {
                            // Opção 1 selecionada
                            // Faça algo aqui
                        }
                        2 -> {
                            // Opção 2 selecionada
                            // Faça algo aqui
                        }
                        3 -> {
                            // Opção 3 selecionada
                            // Faça algo aqui
                        }
                    }
                })
                OutlinedTextField(
                    value = yearStudentState,
                    onValueChange = {
                        yearStudentState = it
                    },
                    modifier = Modifier
                        .width(270.dp)
                        .height(53.dp),
                    shape = RoundedCornerShape(30.dp),
                    maxLines = 1,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_outlined_year),
                            color = colorResource(id = R.color.gray_text),
                            fontWeight = FontWeight(700),
                            fontSize = 15.sp
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_24),
                            contentDescription = "",
                            tint = colorResource(id = R.color.gray_text)
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.blue_default),
                        unfocusedBorderColor = colorResource(id = R.color.blue_default),
                        backgroundColor = colorResource(id = R.color.second_blue),
                        cursorColor = colorResource(id = R.color.blue_default)
                    )
                )
            }
            LazyColumn(){
                items(listaAlunos){
                    var cor = colorResource(id = R.color.blue_default)

                    if (it.status == "Cursando")
                        cor = colorResource(id = R.color.blue_default)
                    else
                        cor = colorResource(id = R.color.yellow_default)

                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                            .height(220.dp)
                            .width(200.dp),
                        shape = RoundedCornerShape(15.dp),
                        backgroundColor = cor
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AsyncImage(
                                model = it.foto,
                                contentDescription = "",
                                modifier = Modifier.size(120.dp)
                            )
                            Text(
                                text = it.nome,
                                color = colorResource(id = R.color.white),
                                fontWeight = FontWeight(500),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    shadow = Shadow(
                                        color = Color.Black,
                                        offset = Offset(0f, 4f),
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview (){
    LionSchoolTheme() {
        Studentscreen(curso = "DS", "Técnico em Desenvolvimento de Sistemas")
    }
}


@Composable
fun ToggleButton(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        val options = listOf("Todos", "Cursando", "Finalizado")
        options.forEachIndexed { index, option ->
            val isSelected = index + 1 == selectedOption
            Button(
                onClick = { onOptionSelected(index + 1) },
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(horizontal = 6.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isSelected) colorResource(id = R.color.blue_default) else colorResource(
                        id = R.color.second_blue
                    ),
                    contentColor = if (isSelected) colorResource(id = R.color.yellow_default) else colorResource(
                        id = R.color.blue_default
                    )
                )
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.body1,
                    fontSize = 16.sp

                )
            }
        }
    }
}





