package com.ayaabdelaal.elevate.model.data

import androidx.room.*
import com.ayaabdelaal.elevate.model.Goal
import kotlinx.coroutines.flow.Flow


@Dao
interface GoalDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(goal: Goal)

    @Delete
    suspend fun delete(goal: Goal)

    @Query("SELECT * from goal")
    fun getItems(): Flow<List<Goal>>
}

