package com.ayaabdelaal.elevate.views.composables.feature_todoList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ayaabdelaal.elevate.model.Item
import com.ayaabdelaal.elevate.ui.theme.lightGray
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel


@Composable
fun AllItemsScreen(viewModel: ToDoListViewModel, onNavigate : () -> Unit){

   LazyColumn(modifier = Modifier
       .background(lightGray)
       .fillMaxSize()
       .padding(16.dp)){

       item{
           Header {
               onNavigate()
           }
       }

       items(viewModel.getItemsList().value) {
           ItemLayout(item = it, didItem = {item: Item -> viewModel.didItem(item)},
           resetItem = {item: Item -> viewModel.resetItem(item)},
           deleteItem = {item: Item -> viewModel.deleteItem(item)})
           }

    }
}





