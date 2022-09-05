package com.ayaabdelaal.elevate.model.data

import androidx.room.*
import com.ayaabdelaal.elevate.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item")
    fun getItems(): Flow<List<Item>>

    @Query("SELECT * from item where done != total")
    fun getItemsLeft() : Flow<List<Item>>

    @Query("DELETE FROM item")
    fun clearTable()


}