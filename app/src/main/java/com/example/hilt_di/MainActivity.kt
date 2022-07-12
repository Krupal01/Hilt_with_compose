package com.example.hilt_di

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hilt_di.ui.theme.Hilt_DiTheme
import com.example.hilt_di.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : MainViewModel by viewModels()
        setContent {
            Hilt_DiTheme {
                // A surface container using the 'background' color from the theme
                val items = remember {
                    viewModel._items
                }
                val users = remember {
                    viewModel._users
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn{
                        items(items){item : String ->
                            Text(
                                text = item,
                                modifier = Modifier.fillMaxWidth().padding(all = 10.dp)
                            )
                        }
                        items(users){user: String ->
                            Text(
                                text = user,
                                modifier = Modifier.fillMaxWidth().padding(all = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
