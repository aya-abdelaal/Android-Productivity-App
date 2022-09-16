package com.ayaabdelaal.elevate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goal")
data class Goal(
@ColumnInfo(name = "goal") val goal:String,
@PrimaryKey(autoGenerate = true) @ColumnInfo(name= "id") var id: Int = 0)
