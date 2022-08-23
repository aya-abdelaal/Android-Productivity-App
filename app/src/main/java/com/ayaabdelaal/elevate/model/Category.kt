package com.ayaabdelaal.elevate.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) private val id: Int = 0,
    @ColumnInfo(name = "color") private var color : Int,
    @ColumnInfo(name = "name") private var name : String,
    @ColumnInfo(name = "items") private val items : MutableList<Item> = mutableListOf<Item>()
) {

    fun addItem( item: Item){
        items.add(item)
    }

    fun deleteItem(item: Item){
        items.remove(item)
    }



}
