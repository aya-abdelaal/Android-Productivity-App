package com.ayaabdelaal.elevate.model.data

import androidx.room.*
import com.ayaabdelaal.elevate.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(catgeory: Category)

    @Update
    suspend fun update(catgeory: Category)

    @Delete
    suspend fun delete(catgeory: Category)

    @Query("SELECT * from category")
    fun getCategories(): Flow<List<Category>>
}