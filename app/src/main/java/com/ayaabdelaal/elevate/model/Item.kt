package com.ayaabdelaal.elevate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class Item(
    @ColumnInfo(name = "name") private val name : String,
    @ColumnInfo(name = "total_times") private val total :Int = 1,
    @ColumnInfo(name = "color") private val color : Int,
    @PrimaryKey(autoGenerate = true) private val id: Int = 0,
    @ColumnInfo(name = "done_times") private var _done : Int = 0,
){
    val percentageDone : Double
    get() { return _done.toDouble()/total.toDouble() }

    val done : Int
    get() {return _done}

}
