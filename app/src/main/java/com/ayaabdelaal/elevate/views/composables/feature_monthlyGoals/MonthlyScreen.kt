package com.ayaabdelaal.elevate.views.composables.feature_monthlyGoals

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaabdelaal.elevate.R
import com.ayaabdelaal.elevate.ui.theme.*
import com.ayaabdelaal.elevate.viewmodel.MonthlyViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MonthlyScreen(viewModel: MonthlyViewModel, padding:PaddingValues, navigateToEditScreen : () -> Unit){
    Box(Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.mipmap.goals_background),
            contentDescription ="",
        modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(padding)
        .padding(16.dp)) {




        item{
            MonthHeader(viewModel.theme,viewModel.about)
        }

        item {
            Text("Goals :", fontSize = 36.sp, color = night3, modifier = Modifier.padding(16.dp))
        }

        items(viewModel.goals.value){
            GoalItem(goal = it.goal)
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
                    .clickable { navigateToEditScreen() },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(color = darkGray, text = "Edit", fontSize = 18.sp)
            }

        }
    }
    }

}


@Composable
fun MonthHeader(theme : MutableState<String>, about: MutableState<String>){



    
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween) {
        val simpleDate = SimpleDateFormat("MMMM")
        val currentDate = simpleDate.format(Date())
        Text(text = currentDate, fontSize = 36.sp, color = night3, modifier = Modifier.padding(8.dp))
        Text("is all about", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        Text(theme.value, fontSize = 36.sp, color = night3 , modifier = Modifier.padding(8.dp))
        
        Row(modifier = Modifier
            .padding(8.dp)
            .clip(Shapes.medium)
            .background(morning3)
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){
            
            Text(text = about.value)

        }


    }
    
}

@Composable
fun GoalItem(goal : String) {

    Row(
        Modifier
            .padding(8.dp)
            .clip(Shapes.medium)
            .background(todo3)
            .fillMaxWidth()
            .padding(16.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically){
        Text(goal, fontSize = 24.sp, color = lightGray)
    }

}