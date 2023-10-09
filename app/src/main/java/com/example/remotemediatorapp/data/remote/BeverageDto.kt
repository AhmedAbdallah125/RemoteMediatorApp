package com.example.remotemediatorapp.data.remote

// DTO data transfer object name for network model that uses for serialize and deserialize between processes
data class BeverageDto(
    val id: Int,
    val name: String,
    val tagLine: String,
    val describtion: String,
    val first_made_date: String,
    val image_url: String? = null
)