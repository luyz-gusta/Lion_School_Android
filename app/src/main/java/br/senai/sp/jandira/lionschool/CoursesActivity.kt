package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.model.Curso
import br.senai.sp.jandira.lionschool.model.CursoList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoursesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CoursesScreen()
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CoursesScreen() {

    var nameCourseState by remember {
        mutableStateOf("")
    }

    var listaCursos by remember {
        mutableStateOf(listOf<Curso>( ))
    }

    val context = LocalContext.current

    // Cria uma chamada para o endpoint
    val call = RetrofitFactory().getCursosService().getCursos()

    // Executar a chamada
    call.enqueue(object  : Callback<CursoList>{
        override fun onResponse(
            call: Call<CursoList>,
            response: Response<CursoList>
        ) {
            listaCursos = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CursoList>, t: Throwable) {
            Log.i("curso", "onFailure: ${t.message}")

        }

    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.width(120.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_image),
                        contentDescription = "",
                        modifier = Modifier
                            .width(50.dp)
                            .height(60.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.logo_name),
                        modifier = Modifier.width(64.dp),
                        color = colorResource(id = R.color.blue_default),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(colorResource(id = R.color.yellow_default))
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = nameCourseState,
                onValueChange = {
                    nameCourseState = it
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(53.dp),
                shape = RoundedCornerShape(30.dp),
                maxLines = 1,
                placeholder = {
                        Text(
                            text = stringResource(id = R.string.text_outlined),
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
            Spacer(modifier = Modifier.height(15.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_assignment_24),
                        contentDescription = "",
                        tint = colorResource(id = R.color.yellow_default),
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.courses),
                        color = colorResource(id = R.color.blue_default),
                        fontWeight = FontWeight(700),
                        fontSize = 36.sp
                    )
                }
                LazyColumn(){
                    items(listaCursos){
                        Spacer(modifier = Modifier.height(32.dp))
                        Column(
                            modifier = Modifier
                                .width(320.dp)
                                .height(160.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            colorResource(id = R.color.blue_default),
                                            colorResource(id = R.color.third_blue_gradient)
                                        )
                                    )
                                )
                                .padding(horizontal = 20.dp, vertical = 10.dp)
                                .clickable {
                                    val openStudents = Intent(context, StudentsActivity::class.java)
                                    openStudents.putExtra("sigla", it.sigla)
                                    openStudents.putExtra("nome", it.nome)
                                    context.startActivity(openStudents)
                                },
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(75.dp),
                                verticalAlignment = Alignment.Bottom,
                            ) {
                                AsyncImage(
                                    model = it.icone,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .width(80.dp)
                                        .height(62.dp),
                                    colorFilter = ColorFilter.tint(Color.White)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = it.sigla,
                                    fontSize =  64.sp,
                                    fontWeight = FontWeight(700),
                                    color = colorResource(id = R.color.white),
                                )
                            }
                            Text(
                                text = it.nome,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                color = colorResource(id = R.color.white)
                            )
                            Row(
                                modifier = Modifier
                                    .height(17.dp)
                                    .width(90.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_access_time_filled_24),
                                    contentDescription = "",
                                    modifier = Modifier.size(15.dp),
                                    tint = colorResource(id = R.color.yellow_default)
                                )
                                Text(
                                    text = "${it.carga} horas",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(500),
                                    color = colorResource(id = R.color.white)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
