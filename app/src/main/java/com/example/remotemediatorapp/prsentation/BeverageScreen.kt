package com.example.remotemediatorapp.prsentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems


import com.example.remotemediatorapp.domain.Beverage

@Composable
fun BeverageScreen(
// The class responsible for accessing the data from a Flow of PagingData
    beverages: LazyPagingItems<Beverage>
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = beverages.loadState) {
        if (beverages.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error is: ${(beverages.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (beverages.loadState.refresh is LoadState.Loading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                items(beverages.itemCount) {
                    beverages[it]?.let { it1 -> BeverageItem(beverage = it1) }
                }

                item {
                    if (beverages.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}

