package com.ayaabdelaal.elevate.views.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel
import com.ayaabdelaal.elevate.views.composables.feature_todoList.AllItemsScreen
import com.ayaabdelaal.elevate.views.composables.feature_todoList.CategorizedScreen

@Composable
fun navHost(viewModel: ToDoListViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categoryItemsList"){
        composable("allItemsList"){
            AllItemsScreen(viewModel, {navController.navigate("categoryItemsList")})
        }

        composable("categoryItemsList"){
            CategorizedScreen(viewModel = viewModel, {navController.navigate("allItemsList")})
        }
    }

    
}