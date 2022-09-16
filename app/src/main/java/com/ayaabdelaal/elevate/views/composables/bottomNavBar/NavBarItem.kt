package com.ayaabdelaal.elevate.views.composables.bottomNavBar

import androidx.annotation.IdRes
import com.ayaabdelaal.elevate.R

sealed class NavBarItem(
    val routes : List<String>,
    val icon: Int,
    val name : String
){

    object ToDoList : NavBarItem(listOf("categoryItemsList","allItemsList"), R.drawable.ic_todo_24 ,"TODO")
    object Monthly : NavBarItem (listOf("monthlyScreen","monthlyEditScreen"), R.drawable.ic_month_24, "Monthly Goals")
}
