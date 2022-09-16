package com.ayaabdelaal.elevate.views.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ayaabdelaal.elevate.ui.theme.darkGray
import com.ayaabdelaal.elevate.ui.theme.darkerGray
import com.ayaabdelaal.elevate.ui.theme.lightGray
import com.ayaabdelaal.elevate.views.composables.bottomNavBar.NavBarItem


@Composable
fun BottomNavBar (navController: NavController,
    onClick : (String) -> Unit){

    val backStackEntry = navController.currentBackStackEntryAsState()
    val items = listOf(NavBarItem.ToDoList, NavBarItem.Monthly)

    BottomNavigation(
        elevation = 5.dp,
        backgroundColor = lightGray
    ) {

        items.forEach { item ->
            val selected = item.routes.contains( backStackEntry.value?.destination?.route)
            BottomNavigationItem(selected = selected, onClick = {onClick(item.routes[0])},
            selectedContentColor = darkerGray,
            unselectedContentColor = darkGray,
            icon = {
                Icon(painter = painterResource(id = item.icon), contentDescription = item.name)

            })
        }



    }
}

