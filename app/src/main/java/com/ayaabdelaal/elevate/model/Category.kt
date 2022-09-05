package com.ayaabdelaal.elevate.model

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.ayaabdelaal.elevate.R
import com.ayaabdelaal.elevate.ui.theme.*


sealed class Category(
    val color: Color,
    val name:String,
    val icon: Int,
    val gradient: Brush
){
    object Morning : Category(morning3, "Morning Routine", R.drawable.ic_sun_24, Brush.horizontalGradient(
        listOf(morning2, morning1)))
    object Night : Category(night3, "Night Routine", R.drawable.ic_moon_24, Brush.horizontalGradient(
        listOf( night2,night1)))
    object Todo: Category(todo3, "TODO", R.drawable.ic_todo_24, Brush.horizontalGradient(
        listOf(todo2, todo1)))
    object Habit : Category(habits3,"Habits", R.drawable.ic_habit_24, Brush.horizontalGradient(
        listOf( habits2, habits1)))

}
