package br.senai.sp.jandira.lionschool.component

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.CoursesActivity
import br.senai.sp.jandira.lionschool.R
import br.senai.sp.jandira.lionschool.StudentsActivity

@Composable
fun HeaderStudents(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
            Icon(
                painter = painterResource(id = R.drawable.baseline_keyboard_double_arrow_left_24),
                contentDescription = "",
                tint = colorResource(id = R.color.blue_default),
                modifier = Modifier.size(60.dp)
                    .clickable {
                        var openCourses = Intent(context, StudentsActivity::class.java)
                        context.startActivity(openCourses)
                    }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(colorResource(id = R.color.yellow_default))
        )
    }
}