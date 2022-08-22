package com.ayaabdelaal.elevate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @PrimaryKey(autoGenerate = true) private val id: Int = 0,
    @ColumnInfo(name = "total_times") private val total :Int = 1,
    @ColumnInfo(name = "color") private val color : Int,
    @ColumnInfo(name = "done_times") private var done : Int = 0
){
    val percentageDone : Double
    get() { return done.toDouble()/total.toDouble() }

}
