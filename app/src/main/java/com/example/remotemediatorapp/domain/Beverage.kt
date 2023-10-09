package com.example.remotemediatorapp.domain

// business madel that will be used in whole app
data class Beverage(
    val id: Int,
    val name: String,
    val tagLine: String,
    val description: String,
    val firstMadeDate: String,
    val imageUrl: String? = null
)
