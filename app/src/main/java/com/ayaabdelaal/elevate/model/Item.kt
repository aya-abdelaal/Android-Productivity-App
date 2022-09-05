package com.ayaabdelaal.elevate.model

import androidx.annotation.NonNull
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item")
data class Item(
    @ColumnInfo(name = "name") @NonNull var name : String,
    @ColumnInfo(name = "total") var total :Int = 1,
    @ColumnInfo(name = "done") var done : Int = 0,
    @ColumnInfo(name = "category") var category: Category,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name= "id") var id: Int = 0){

    constructor() : this("",1, 0, Category.Todo, 0)


    val percentageDone : MutableState<Double>
    get() { return mutableStateOf(done.toDouble()/total.toDouble()) }




}
