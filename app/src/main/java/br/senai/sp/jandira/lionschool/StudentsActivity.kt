package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.component.HeaderScreen
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class StudentsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LionSchoolTheme {
                val siglaCurso = intent.getStringExtra("Curso")
                val nomeCurso = intent.getStringExtra("NomeCurso")
                Studentscreen(siglaCurso.toString(), nomeCurso.toString())
            }
        }
    }
}

@Composable
fun Studentscreen(curso:String, nomeCurso: String) {

    var yearStudentState by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderScreen(context)
            Text(
                text = nomeCurso,
                color = colorResource(id = R.color.blue_default),
                fontWeight = FontWeight(500),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
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
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreview (){
    LionSchoolTheme() {
        Studentscreen(curso = "DS", "Tecnico em Desenvolvimento de Sistemas")
    }
}




