package com.example.remotemediatorapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BeverageEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val firstMadeDate: String,
    val imageUrl: String? = null
)
