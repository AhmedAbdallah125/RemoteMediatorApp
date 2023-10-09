package com.example.remotemediatorapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BeverageDao {

    // update row if it exist or add new one
    @Upsert
    suspend fun upsertAll(beverages: List<BeverageEntity>)

    // room will handle paging source that will generate code required for this
    @Query("SELECT * FROM beverageentity")
    fun pagingSource(): PagingSource<Int, BeverageEntity>

    @Query("DELETE FROM beverageentity")
    suspend fun clearAll()
}