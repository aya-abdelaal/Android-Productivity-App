package com.ayaabdelaal.elevate.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ayaabdelaal.elevate.model.Goal
import com.ayaabdelaal.elevate.model.data.GoalDao
import com.ayaabdelaal.elevate.model.data.MonthlyDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class MonthlyViewModel(private val goalDao: GoalDao, private val monthlyDataStore: MonthlyDataStore): ViewModel( ) {


    var goals : MutableState<List<Goal>> = mutableStateOf(emptyList())
    var theme : MutableState<String> = mutableStateOf("")
    var about : MutableState<String> = mutableStateOf("")

    init {
        viewModelScope.launch{
            theme.value = monthlyDataStore.getMonthlyTheme.first()
            about.value = monthlyDataStore.getMonthlyAbout.first()
            getAllGoals().collect{goals.value = it}
        }
    }



    fun saveMonthly(theme:String, about:String){
        viewModelScope.launch(Dispatchers.IO) {
            monthlyDataStore.saveMonthly(theme,about)
        }
    }

    fun getAllGoals() : Flow<List<Goal>> {
        return goalDao.getItems()
    }

    fun deleteGoal(goal: Goal){
        viewModelScope.launch {
            goalDao.delete(goal)
        }
    }


    fun insertGoal(goal: String){
        viewModelScope.launch {
            goalDao.insert(Goal(goal))
        }
    }


    class MonthlyViewModelFactory(private val goalDao: GoalDao, private val monthlyDataStore: MonthlyDataStore) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MonthlyViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MonthlyViewModel(goalDao,monthlyDataStore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}


