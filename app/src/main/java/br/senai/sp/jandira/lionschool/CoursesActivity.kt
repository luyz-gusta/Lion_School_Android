package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

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

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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
                        modifier = Modifier.width(62.dp),
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
            OutlinedTextField(
                value = nameCourseState,
                onValueChange = {
                    nameCourseState = it
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(50.dp),
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
                        .padding(horizontal = 20.dp, vertical = 15.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.vector_ds),
                            contentDescription = "",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
            }
        }
    }
}
