package com.ayaabdelaal.elevate.views.composables.feature_monthlyGoals

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaabdelaal.elevate.R
import com.ayaabdelaal.elevate.ui.theme.*
import com.ayaabdelaal.elevate.viewmodel.MonthlyViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EditMonthlyScreen(
    viewModel: MonthlyViewModel,
    padding: PaddingValues,
    navigateToMonthScreen : () -> Unit
){
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.mipmap.goals_background),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }

    var theme by remember {
        mutableStateOf(viewModel.theme.value)
    }

    var about by remember {
        mutableStateOf(viewModel.about.value)
    }


    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp)){


        item{
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween) {
                val simpleDate = SimpleDateFormat("MMMM")
                val currentDate = simpleDate.format(Date())
                Text(text = currentDate, fontSize = 36.sp, color = night3, modifier = Modifier.padding(8.dp))
                Text("is all about", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
                TextField(value = theme,
                    onValueChange = {theme = it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = night3,
                        focusedBorderColor = night3,
                        unfocusedBorderColor = lightGray),
                    modifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 36.sp)
                )

                Row(modifier = Modifier
                    .padding(8.dp)
                    .clip(Shapes.medium)
                    .background(morning3)
                    .fillMaxWidth()
                    .padding(18.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){

                    TextField(value = about,
                        onValueChange = {about = it},
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = darkGray,
                            focusedBorderColor = night3,
                            unfocusedBorderColor = lightGray),
                        modifier = Modifier.padding(8.dp),
                        maxLines = 7
                    )

                }
            }
        }



        item {
            Text("Goals :", fontSize = 36.sp, color = night3, modifier = Modifier.padding(16.dp))
        }
        items(viewModel.goals.value){
            GoalItemDeleteable(goal = it.goal, {viewModel.deleteGoal(it)})
        }

        item{
            AddGoalSection({goal:String -> viewModel.insertGoal(goal)})
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(16.dp)
                    .clip(shape = Shapes.medium)
                    .border(2.dp, color = darkGray, Shapes.medium)
                    .padding(8.dp)
                    .clickable {
                        viewModel.saveMonthly(theme, about)
                        navigateToMonthScreen()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(color = darkGray, text = "Save", fontSize = 18.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(16.dp)
                    .clip(shape = Shapes.medium)
                    .border(2.dp, color = darkGray, Shapes.medium)
                    .padding(8.dp)
                    .clickable {
                        navigateToMonthScreen()
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(color = darkGray, text = "Cancel", fontSize = 18.sp)
            }

        }





    }

}

@Composable
fun AddGoalSection(onSave : (String) -> Unit) {
    var enabled by remember {
        mutableStateOf(false)
    }

    if(!enabled){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp)
                .clip(shape = Shapes.medium)
                .border(2.dp, color = darkGray, Shapes.medium)
                .padding(8.dp)
                .clickable {
                    enabled = !enabled
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(color = darkGray, text = "Add Goal", fontSize = 18.sp)
        }

    }else{

        var value by remember{ mutableStateOf("")}

        Row(
            Modifier
                .padding(8.dp)
                .clip(Shapes.medium)
                .background(todo3)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            TextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(0.7f),
                value = value,
                onValueChange = { value = it},
                placeholder = { Text("Goal", color = lightGray) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = lightGray,
                    focusedBorderColor = darkGray,
                    unfocusedBorderColor = lightGray)
            )

            IconButton(onClick = { onSave(value)
            enabled = !enabled}) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "save",
                    tint = lightGray,
                    modifier = Modifier.size(24.dp)
                )
            }


        }

    }
}

@Composable
fun GoalItemDeleteable(goal : String, onDelete : () -> Unit) {

    Row(
        Modifier
            .padding(8.dp)
            .clip(Shapes.medium)
            .background(todo3)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Text(goal, fontSize = 24.sp, color = lightGray)

        IconButton(onClick = { onDelete()}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete_24),
                contentDescription = "delete item",
                tint = lightGray,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}