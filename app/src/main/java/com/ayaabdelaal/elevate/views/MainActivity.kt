package com.ayaabdelaal.elevate.views

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ayaabdelaal.elevate.model.ElevateApplication
import com.ayaabdelaal.elevate.model.data.ItemDao
import com.ayaabdelaal.elevate.model.data.MonthlyDataStore
import com.ayaabdelaal.elevate.views.composables.navHost
import com.ayaabdelaal.elevate.ui.theme.ElevateTheme
import com.ayaabdelaal.elevate.viewmodel.MonthlyViewModel
import com.ayaabdelaal.elevate.viewmodel.ToDoListViewModel


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ToDoviewModel by viewModels<ToDoListViewModel> {
            ToDoListViewModel.ToDoListViewModelFactory((this.applicationContext as ElevateApplication).database.itemDao())
        }

        val MonthlyViewModel by viewModels<MonthlyViewModel> {
            MonthlyViewModel.MonthlyViewModelFactory((this.applicationContext as ElevateApplication).database.goalDao(),
                MonthlyDataStore(this.applicationContext)
            )
        }




        setContent {
            ElevateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navHost(ToDoviewModel, MonthlyViewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ElevateTheme {

    }
}