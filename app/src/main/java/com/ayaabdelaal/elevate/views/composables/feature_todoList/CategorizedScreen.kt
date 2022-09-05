package com.ayaabdelaal.elevate.views.composables.feature_todoList

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaabdelaal.elevate.model.Category
import com.ayaabdelaal.elevate.model.Item
import com.ayaabdelaal.elevate.ui.theme.Shapes
import com.ayaabdelaal.elevate.ui.theme.lightGray
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel

@Composable
fun CategorizedScreen(viewModel: ToDoListViewModel, onNavigate : () -> Unit){

    val categories = listOf(Category.Morning, Category.Habit, Category.Todo, Category.Night)

    LazyColumn(modifier = Modifier
        .background(lightGray)
        .fillMaxSize()
        .padding(24.dp)
        ) {

        item{
            Header {
                onNavigate()
            }
        }

        items(categories) {
            val isItemSectionVisible by remember { mutableStateOf(mutableStateOf(false)) }

            CategoryLayout( it, isItemSectionVisible) {isItemSectionVisible.value = !isItemSectionVisible.value}
            ItemsInCategory(category = it, viewModel = viewModel, isItemSectionVisible = isItemSectionVisible)
        }

    }
}



@Composable
fun CategoryLayout(category: Category, isItemSectionVisible: MutableState<Boolean>, onclick : () -> Unit){

    val isRotated by remember {
        mutableStateOf(isItemSectionVisible)
    }


    val angle: Float by animateFloatAsState(
        targetValue = if (isRotated.value) 360F else 0F,
        animationSpec = tween(
            durationMillis = 1500,
            easing = FastOutSlowInEasing
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
    ){
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.medium)
                .background(category.gradient)
                .clickable {
                    onclick()
                }
                .padding(horizontal = 26.dp)){

            Text(category.name, color = lightGray, fontSize = 24.sp)

            Icon( painter = painterResource(id =category.icon), contentDescription = category.name,
                tint = lightGray,
                modifier = Modifier
                    .size(30.dp)
                    .rotate(angle))
        }

    }


}

@Composable
fun ItemsInCategory(category: Category, viewModel: ToDoListViewModel, isItemSectionVisible: MutableState<Boolean>) {
    val enabled by remember {
        mutableStateOf(isItemSectionVisible)
    }

    val height : Float by animateFloatAsState(
        targetValue = if (enabled.value) 1F else 0F,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        )
    )

    if (enabled.value) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxHeight(height)
        ) {
            for(item in viewModel.getCategoryItems(category).value) {
                ItemLayout(item = item, didItem = { item: Item -> viewModel.didItem(item) },
                    resetItem = { item: Item -> viewModel.resetItem(item) },
                    deleteItem = { item: Item -> viewModel.deleteItem(item) })
            }

                AddItemSection(category = category,
                    onSave = { name: String, total: String, category: Category ->
                        viewModel.addItem(
                            name,
                            total,
                            category
                        )
                    })

        }
    }
}