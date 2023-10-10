package com.example.remotemediatorapp.data.remote

import com.example.remotemediatorapp.data.local.BeverageEntity
import com.example.remotemediatorapp.domain.Beverage

fun BeverageDto?.toBeverageEntity(): BeverageEntity {
    return BeverageEntity(
        id = this?.id ?: 4,
        name = this?.name ?: "",
        tagLine = this?.tagline ?: "",
        description = this?.description ?: "",
        firstMadeDate = this?.first_brewed ?: "",
        imageUrl = this?.image_url
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