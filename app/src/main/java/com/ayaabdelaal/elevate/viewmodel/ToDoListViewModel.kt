package com.ayaabdelaal.elevate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ayaabdelaal.elevate.model.Item
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.ayaabdelaal.elevate.model.Category
import com.ayaabdelaal.elevate.model.data.CategoryDao
import com.ayaabdelaal.elevate.model.data.ItemDao

class ToDoListViewModel(private val itemDao: ItemDao) : ViewModel(){

    private fun addItem(name: String, total : Int, color: Int){
        val item = Item(name,total,color)
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun deleteItem(item: Item){
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    private fun updateItem(item: Item){
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

   fun didItem(item: Item){
       if(item.percentageDone < 1){
          val newItem = item.copy(_done = item.done + 1)
           updateItem(newItem)
       }
   }

    fun resetItem(item: Item){
        if(item.done != 0) {
            val newItem = item.copy(_done = 0)
            updateItem(newItem)
        }
    }



}

