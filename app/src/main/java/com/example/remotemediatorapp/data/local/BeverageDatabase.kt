package com.example.remotemediatorapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BeverageEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BeverageDatabase : RoomDatabase() {
    abstract val dao: BeverageDao

}