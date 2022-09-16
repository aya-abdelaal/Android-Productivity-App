package com.ayaabdelaal.elevate.views.composables

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayaabdelaal.elevate.viewmodel.MonthlyViewModel
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel
import com.ayaabdelaal.elevate.views.composables.feature_monthlyGoals.EditMonthlyScreen
import com.ayaabdelaal.elevate.views.composables.feature_monthlyGoals.MonthlyScreen
import com.ayaabdelaal.elevate.views.composables.feature_todoList.AllItemsScreen
import com.ayaabdelaal.elevate.views.composables.feature_todoList.CategorizedScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun navHost(toDoviewModel: ToDoListViewModel, monthlyViewModel: MonthlyViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categoryItemsList"){
        composable("allItemsList"){
            Scaffold(bottomBar = {
                BottomNavBar(navController = navController, onClick = {
                        route :String -> navController.navigate(route)})
            }) {padding ->
                AllItemsScreen(toDoviewModel, {navController.navigate("categoryItemsList")}, padding)
            }
        }

        composable("categoryItemsList"){
            Scaffold(bottomBar = {
                BottomNavBar(navController = navController, onClick = {
                        route :String -> navController.navigate(route)})
            }) {padding ->
                CategorizedScreen(viewModel = toDoviewModel, {navController.navigate("allItemsList")}, padding)
            }
        }

        composable("monthlyScreen"){
            Scaffold(bottomBar = {
                BottomNavBar(navController = navController, onClick = {
                        route :String -> navController.navigate(route)})
            }) {padding ->
                    MonthlyScreen(viewModel = monthlyViewModel, padding, {navController.navigate("monthlyEditScreen")})
            }       
        }


        composable("monthlyEditScreen"){
            Scaffold(bottomBar = {
                BottomNavBar(navController = navController, onClick = {
                        route :String -> navController.navigate(route)})
            }) {padding ->
                EditMonthlyScreen(viewModel = monthlyViewModel, padding, {navController.navigate("monthlyScreen")})
            }
        }
    }

    
}