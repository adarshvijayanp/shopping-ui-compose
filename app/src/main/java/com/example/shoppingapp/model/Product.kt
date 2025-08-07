package com.example.shoppingapp.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val colors: List<String> = emptyList(),
    val models: List<String> = emptyList(),
    val storages: List<String> = emptyList()
)
