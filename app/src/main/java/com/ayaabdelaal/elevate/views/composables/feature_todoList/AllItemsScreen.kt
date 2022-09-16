package com.ayaabdelaal.elevate.views.composables.feature_todoList

import android.widget.ToggleButton
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ayaabdelaal.elevate.model.Item
import com.ayaabdelaal.elevate.ui.theme.darkerGray
import com.ayaabdelaal.elevate.ui.theme.habits2
import com.ayaabdelaal.elevate.ui.theme.lightGray
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel


@Composable
fun AllItemsScreen(viewModel: ToDoListViewModel, onNavigate : () -> Unit, padding : PaddingValues){

    var showDoneItemsToggle by remember { mutableStateOf(true) }

   LazyColumn(modifier = Modifier
       .background(lightGray)
       .fillMaxSize()
       .padding(padding)
       .padding(16.dp)){


       item{
           Header {
               onNavigate()
           }

           Row(horizontalArrangement = Arrangement.End,
           verticalAlignment = Alignment.Top){

               Spacer(modifier = Modifier.weight(1f))

               IconToggleButton(checked = showDoneItemsToggle,
                   onCheckedChange = {showDoneItemsToggle = !showDoneItemsToggle}) {

                   val transition = updateTransition(showDoneItemsToggle)

                   val tint by transition.animateColor(label = "iconColor") { isChecked ->
                       if (isChecked) habits2 else darkerGray
                   }
                   Icon(
                       imageVector = if(showDoneItemsToggle) Icons.Filled.Done else Icons.Outlined.Done,
                               contentDescription = "Icon",
                       tint = tint,
                       modifier = Modifier.size(36.dp)
                   )
               }
           }
       }

       items(if(showDoneItemsToggle)
           viewModel.getItemsList().value
       else viewModel.getItemsList().value.filter { !(it.percentageDone.value == 1.0) }) {
           ItemLayout(item = it, didItem = {item: Item -> viewModel.didItem(item)},
           resetItem = {item: Item -> viewModel.resetItem(item)},
           deleteItem = {item: Item -> viewModel.deleteItem(item)})
           }

    }
}





