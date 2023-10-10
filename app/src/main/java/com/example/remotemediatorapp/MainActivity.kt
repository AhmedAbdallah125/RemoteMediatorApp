package com.example.remotemediatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.remotemediatorapp.prsentation.BeverageScreen
import com.example.remotemediatorapp.prsentation.BeverageViewModel
import com.example.remotemediatorapp.ui.theme.RemoteMediatorAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemoteMediatorAppTheme {
                // A surface container using the 'background' color from the theme

                val viewModel = hiltViewModel<BeverageViewModel>()
                val beverages = viewModel.beverageFlow.collectAsLazyPagingItems()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BeverageScreen(beverages = beverages)
                }
            }
        }
    }
}
