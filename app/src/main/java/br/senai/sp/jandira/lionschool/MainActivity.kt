package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                StartedScreen()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun StartedScreen() {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        colorResource(id = R.color.white),
                        colorResource(id = R.color.blue_default)
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.logo_name),
                color = colorResource(id = R.color.blue_default),
                fontWeight = FontWeight(700),
                fontSize = 40.sp
            )
            Image(
                painter = painterResource(id = R.drawable.studant),
                contentDescription = "",
                modifier = Modifier
                    .height(340.dp)
                    .width(190.dp)
            )
            Text(
                text = stringResource(id = R.string.welcome),
                color = colorResource(id = R.color.white),
                fontWeight = FontWeight(700),
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
            TextButton(
                onClick = {
                    val openCourses = Intent(context, CoursesActivity::class.java)
                    context.startActivity(openCourses)
                },
                modifier = Modifier
                    .height(75.dp)
                    .width(225.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.yellow_default))
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.lets_go),
                        color = colorResource(id = R.color.white),
                        fontWeight = FontWeight(700),
                        fontSize = 40.sp
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward_24),
                        contentDescription = "",
                        modifier = Modifier.size(40.dp),
                        tint = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}
