package com.example.remotemediatorapp.data.remote

import com.example.remotemediatorapp.data.local.BeverageEntity
import com.example.remotemediatorapp.domain.Beverage

fun BeverageDto.toBeverageEntity(): BeverageEntity {
    return BeverageEntity(
        id = id,
        name = name,
        tagLine = tagLine,
        description = describtion,
        firstMadeDate = first_made_date,
        imageUrl = image_url
    )
}fun BeverageEntity.toBeverage(): Beverage {
    return Beverage(
        id = id,
        name = name,
        tagLine = tagLine,
        description = description,
        firstMadeDate = firstMadeDate,
        imageUrl = imageUrl
    )
}