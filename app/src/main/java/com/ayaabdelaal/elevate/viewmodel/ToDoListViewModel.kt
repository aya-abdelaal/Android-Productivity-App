package com.ayaabdelaal.elevate.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ayaabdelaal.elevate.model.Item
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.ayaabdelaal.elevate.model.Category
import com.ayaabdelaal.elevate.model.data.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ToDoListViewModel(private val itemDao: ItemDao) : ViewModel(){

    private var itemsFlow: Flow<List<Item>>
    private val items: MutableState<List<Item>>
    private val morningItems : MutableState<List<Item>>
    private val nightItems : MutableState<List<Item>>
    private val habitItems : MutableState<List<Item>>
    private val todoItems : MutableState<List<Item>>

    init {
        itemsFlow = getAllItems()
        items= mutableStateOf(emptyList())
        morningItems= mutableStateOf(emptyList())
        nightItems = mutableStateOf(emptyList())
        habitItems= mutableStateOf(emptyList())
        todoItems= mutableStateOf(emptyList())
        this.setItems()
    }

    fun setItems(){
        viewModelScope.launch {
            itemsFlow.collect {
                items.value = it.sortedWith({a:Item, b:Item -> itemComparator(a,b)})
                morningItems.value = it.filter { it.category == Category.Morning }
                nightItems.value = it.filter { it.category == Category.Night}
                habitItems.value = it.filter { it.category == Category.Habit }
                todoItems.value = it.filter { it.category == Category.Todo }

            }
        }
    }


    private fun getAllItems() : Flow<List<Item>> {
        return itemDao.getItems()
    }

    fun addItem(name: String, total : String, category: Category){
        if(validateItem(name,total)) {
            viewModelScope.launch {
                val item = Item(name, total.toInt(), category = category)
                itemDao.insert(item)
            }
        }
    }

    private fun validateItem(name:String,total: String) : Boolean{
        return (name != "" && total.toIntOrNull() != null && total.toInt() > 0 )
    }

     fun deleteItem(item: Item){
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun updateItem(item: Item){
        viewModelScope.launch {
            itemDao.update(item)
        }
    }


   fun didItem(item: Item){
       if(item.percentageDone.value < 1){
          val newItem = item.copy(done = item.done + 1)
           updateItem(newItem)
       }
   }

    fun resetItem(item: Item){
        if(item.done != 0) {
            val newItem = item.copy(done = 0)
            updateItem(newItem)
        }
    }

    fun getCategoryItems(category: Category): MutableState<List<Item>> {
        return when(category){
            Category.Habit -> habitItems
            Category.Morning -> morningItems
            Category.Night -> nightItems
            Category.Todo -> todoItems
        }
    }

    fun getItemsList(): MutableState<List<Item>> {
        return items
    }

private fun itemComparator(a:Item, b:Item) : Int {
    return if(a.category == b.category)
        0
    else if(a.category == Category.Night) 1
        else {
            if(a.category == Category.Morning) -1
            else {
                if(a.category == Category.Habit){
                    if(b.category == Category.Morning)
                        1
                    else -1
                } else {
                    if(b.category == Category.Morning || b.category == Category.Habit)
                        1
                    else -1
                }
            }
        }
    }





class ToDoListViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToDoListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToDoListViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
}


