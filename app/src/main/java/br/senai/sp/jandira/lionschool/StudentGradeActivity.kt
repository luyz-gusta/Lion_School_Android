package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import br.senai.sp.jandira.lionschool.model.CursoList
import br.senai.sp.jandira.lionschool.model.StudentGrade
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

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

//    var alunoNotas by remember {
//        mutableStateOf(listOf<StudentGrade>( ))
//    }
//
//    // Cria uma chamada para o endpoint
//    val call = RetrofitFactory().getAlunosService().getAlunosByMatricula(matricula)
//
//    // Executar a chamada
//    call.enqueue(object  : Callback<StudentGrade> {
//        override fun onResponse(
//            call: Call<StudentGrade>,
//            response: Response<StudentGrade>
//        ) {
////            alunoNotas = response.body()!!.disciplinas
//        }
//
//        override fun onFailure(call: Call<CursoList>, t: Throwable) {
//            Log.i("curso", "onFailure: ${t.message}")
//
//        }
//
//    })

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(15.dp))
                    .background(color = colorResource(id = R.color.blue_default)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp)
                )
                Text(
                    text = "HÉLIDA BENTO DE OLIVEIRA LINS",
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight(500),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(0f, 4f),
                        )
                    )
                )
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .height(380.dp)
                        .padding(8.dp, 9.dp),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = colorResource(id = R.color.white)
                ) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {

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