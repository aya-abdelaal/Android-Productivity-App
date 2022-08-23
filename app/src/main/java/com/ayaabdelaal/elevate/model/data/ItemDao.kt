package com.ayaabdelaal.elevate.model.data

import androidx.room.*
import com.ayaabdelaal.elevate.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item")
    fun getItems(): List<Item>

    @Query("SELECT * from item where done_times != total_times")
    fun getItemsLeft() : List<Item>


}