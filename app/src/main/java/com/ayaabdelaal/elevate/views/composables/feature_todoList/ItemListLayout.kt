package com.ayaabdelaal.elevate.views.composables.feature_todoList


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ayaabdelaal.elevate.R
import com.ayaabdelaal.elevate.model.Category
import com.ayaabdelaal.elevate.model.Item
import com.ayaabdelaal.elevate.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun Header(switchListView : () -> Unit){
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(110.dp)){

        Column {
            val simpleDate = SimpleDateFormat("EEE, MMM dd")
            val currentDate = simpleDate.format(Date())
            Text(currentDate, Modifier.padding(8.dp))
        }

        IconButton(onClick = { switchListView() }) {
            Icon(painter = painterResource(R.drawable.ic_view_list_24), contentDescription = "Toggle list view")
        }

    }
}

@Composable
fun AddItemSection(category: Category,
                   onSave : (String,String,Category) -> Unit){

    var addBoxEnabled by remember {
        mutableStateOf(false)
    }

    if(!addBoxEnabled) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(16.dp)
                .clip(shape = Shapes.medium)
                .border(2.dp, color = darkGray, Shapes.medium)
                .padding(8.dp)
                .clickable { addBoxEnabled = true },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(color = darkGray, text = "+",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontSize = 18.sp)
            Text(color = darkGray, text= "Add Item", fontSize = 18.sp)
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(shape = Shapes.medium)
                .border(2.dp, color = category.color, Shapes.medium)
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var name by remember{ mutableStateOf("")}
            var timesDone by remember {
                mutableStateOf("")
            }

            Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){

                TextField(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(0.65f),
                    value = name,
                    onValueChange = { name = it},
                    placeholder = { Text("Name", color = category.color) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = category.color,
                        focusedBorderColor = darkGray,
                        unfocusedBorderColor = lightGray)
                )

                            TextField(
                            modifier = Modifier.fillMaxWidth(),
                    value = timesDone,
                    onValueChange = { timesDone = it},
                    placeholder = { Text("Times",  color = category.color)},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = category.color,
                        focusedBorderColor = darkGray,
                        unfocusedBorderColor = lightGray)
                )

            }




            Row(modifier = Modifier.padding(8.dp)){
                Button(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(0.5f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = category.color
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = ({onSave(name,timesDone,category)
                        addBoxEnabled = false})){
                    Text("save" , color = lightGray)
                }

                Button(modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = category.color
                    ),
                    shape = RoundedCornerShape(24.dp),
                    onClick = { addBoxEnabled = false}) {
                    Text("cancel", color = lightGray)
                }
            }


        }
    }

}





@Composable
fun ItemLayout(item: Item, didItem : (Item) -> Unit, resetItem : (Item) -> Unit, deleteItem: (Item) -> Unit) {


    val percentageAnimation by animateFloatAsState(targetValue = item.percentageDone.value.toFloat())




    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp)
            .clickable {
                didItem(item)
            }


    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.medium)
                .border(5.dp, item.category.color, Shapes.medium)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(percentageAnimation)
                .clip(Shapes.medium)
                .background(item.category.color)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.medium)
                .padding(vertical = 8.dp, horizontal = 26.dp)
        ) {

            Column {
                Text(
                    text = item.name,
                    color = getItemTextColor(item.percentageDone.value, item.category.color),
                    fontSize = 20.sp
                )
                Text(
                    text = "${item.done} / ${item.total}",
                    color = getItemTextColor(item.percentageDone.value, item.category.color),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 6.dp)
                )
            }

            if(item.percentageDone.value < 1){
            IconButton(onClick = { deleteItem(item) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_delete_24),
                    contentDescription = "delete item",
                    tint = getItemResetColor(item.percentageDone.value, item.category.color),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        else {
        IconButton(onClick = { resetItem(item) }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reset_24),
                contentDescription = "reset item",
                tint = getItemResetColor(item.percentageDone.value, item.category.color),
                modifier = Modifier.size(24.dp)
            )
        }
    }

    }
    }
}

fun getItemTextColor(percentageDone : Double, color: Color) : Color{
    return if(percentageDone < 0.2){
        color
    }
    else lightGray
}
fun getItemResetColor(percentageDone : Double, color: Color) : Color{
    return if(percentageDone < 0.9){
        color
    }
    else lightGray
}






