package com.ayaabdelaal.elevate.model.data

import androidx.room.TypeConverter
import com.ayaabdelaal.elevate.model.Category

class Converters {

    @TypeConverter
    fun fromCategory(category: Category): String {
        return when (category) {
            Category.Habit -> "habit"
            Category.Morning -> "morning"
            Category.Night -> "night"
            Category.Todo -> "todo"
        }
    }

    @TypeConverter
    fun toCategory(string: String): Category {
        return when (string) {
            "habit" -> Category.Habit
            "morning" -> Category.Morning
            "night" -> Category.Night
            "todo" -> Category.Todo
            else -> Category.Todo
        }
    }

}