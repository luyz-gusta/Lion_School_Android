package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.component.HeaderScreen
import br.senai.sp.jandira.lionschool.model.Student
import br.senai.sp.jandira.lionschool.model.StudentGrade
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class StudentGradeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                // A surface container using the 'background' color from the theme
                val matriculaAluno = intent.getStringExtra("matricula")
                StudentGradeScreen(matriculaAluno.toString())
            }
        }
    }
}

@Composable
fun StudentGradeScreen(matricula: String) {
    val context = LocalContext.current

    var aluno by remember {
        mutableStateOf(StudentGrade(
            "",
            "",
            "",
            "",
            emptyList()
        ))
    }

    // Cria uma chamada para o endpoint
    val call = RetrofitFactory().getAlunosService().getAlunosByMatricula(matricula)

    // Executar a chamada
    call.enqueue(object : retrofit2.Callback<StudentGrade> {
        override fun onResponse(
            call: Call<StudentGrade>,
            response: Response<StudentGrade>
        ) {
          if (response.isSuccessful){
              val studentResponse = response.body()
              if (studentResponse != null){
                  aluno = studentResponse
              }
          }else{
              Log.e("teste", "Erro na resposta da API: ${response.code()}")
          }
        }

        override fun onFailure(call: Call<StudentGrade>, t: Throwable) {
            Log.i("teste", "onFailure: ${t.message} ")
        }

    })

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderScreen(context = context)
            Spacer(modifier = Modifier.height(10.dp))

            var cor = colorResource(id = R.color.blue_default)

            if (aluno.status == "Cursando")
                cor = colorResource(id = R.color.blue_default)
            else
                cor = colorResource(id = R.color.yellow_default)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = cor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                AsyncImage(
                    model = aluno.foto,
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = aluno.nome,
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0f, 4f),
                        )
                    ),
                    modifier = Modifier.width(225.dp)
                )
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .height(380.dp),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = colorResource(id = R.color.white)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(), 
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(aluno.disciplinas){
                            var barra = 2.4 * it.media.toDouble()
                            var corBarra = colorResource(id = R.color.blue_default)

                            if (it.media.toDouble() > 60){
                                corBarra = colorResource(id = R.color.blue_default)
                            }else if (it.media.toDouble() < 60 && it.media.toDouble() > 50){
                                corBarra = colorResource(id = R.color.yellow_default)
                            }else{
                                corBarra = Color.Red
                            }

                            Column(modifier = Modifier
                                .width(240.dp)
                                .height(40.dp)
                            ) {
                                Text(
                                    text = it.nomeDisciplina,
                                    fontWeight = FontWeight(700),
                                    fontSize = 12.sp,
                                    color = colorResource(id = R.color.black)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Box(
                                    modifier = Modifier
                                        .height(17.5.dp)
                                        .width(240.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(
                                            colorResource(id = R.color.second_blue)
                                        )
                                ){
                                    Box(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(
                                                corBarra
                                            )
                                            .width(barra.dp)
                                            .padding(0.dp, 0.dp, 5.dp, 0.dp),
                                        contentAlignment = Alignment.CenterEnd
                                    ){
                                        Text(
                                            text = it.media + "%",
                                            fontWeight = FontWeight(700),
                                            fontSize = 12.sp,
                                            color = colorResource(id = R.color.white)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview2() {
    LionSchoolTheme {
        StudentGradeScreen("20151001016")
    }
}