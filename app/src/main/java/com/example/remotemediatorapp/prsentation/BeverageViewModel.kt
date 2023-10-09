package com.example.remotemediatorapp.prsentation

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.map
import com.example.remotemediatorapp.data.local.BeverageEntity
import com.example.remotemediatorapp.data.remote.toBeverage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
@HiltViewModel
class BeverageViewModel(
    pager: Pager<Int, BeverageEntity>
) : ViewModel() {

    val beverageFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toBeverage() }
        }
}