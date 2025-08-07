package com.example.shoppingapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.model.CartProduct
import com.example.shoppingapp.model.ModifierOption
import com.example.shoppingapp.model.Product

class PosViewModel : ViewModel() {
    val products = listOf(
        Product(
            id = 1, name = "I Phone 16", price = 2499.0,
            colors = listOf("Black", "White", "Red", "Black"),
            models = listOf("16 Pro", "16 Pro Max"),
            storages = listOf("128GB", "256GB", "512GB")
        ),
        Product(id = 2, name = "Apple iWatch", price = 1099.0),
        Product(id = 3, name = "Macbook Pro", price = 4999.0),
        Product(id = 4, name = "Airpods", price = 399.0),
        Product(id = 5, name = "HomePod", price = 899.0),
        Product(id = 6, name = "iPad Pro", price = 2199.0),
        Product(id = 7, name = "Accessory Charger", price = 49.0)
    )

    var selectedProduct by mutableStateOf<Product?>(null)
        private set

    var selectedColor by mutableStateOf("")
        private set

    var selectedModel by mutableStateOf("")
        private set

    var selectedStorage by mutableStateOf("")
        private set

    fun selectProduct(product: Product) {
        selectedProduct = product
        selectedColor = product.colors.firstOrNull() ?: ""
        selectedModel = product.models.firstOrNull() ?: ""
        selectedStorage = product.storages.firstOrNull() ?: ""
    }
    fun clearSelection() { selectedProduct = null }

    fun selectColor(color: String) { selectedColor = color }
    fun selectModel(model: String) { selectedModel = model }
    fun selectStorage(storage: String) { selectedStorage = storage }

    val selectedPrice: Double
        get() = selectedProduct?.price ?: 0.0


    val modifierOptions = listOf(
        ModifierOption(1, "Mobile Cover", 20.0),
        ModifierOption(2, "Screen Cover", 20.0),
        ModifierOption(3, "Charger", 50.0),
        ModifierOption(4, "Back Case", 15.0)
    )

    var selectedModifiers by mutableStateOf<Map<Int, Int>>(emptyMap())
        private set

    fun setModifierQuantity(id: Int, quantity: Int) {
        selectedModifiers = if (quantity > 0) {
            selectedModifiers + (id to quantity)
        } else {
            selectedModifiers - id
        }
    }

    fun incrementModifier(id: Int) {
        val current = selectedModifiers[id] ?: 0
        setModifierQuantity(id, current + 1)
    }

    fun decrementModifier(id: Int) {
        val current = selectedModifiers[id] ?: 0
        if (current > 0) setModifierQuantity(id, current - 1)
    }


    val sampleCartProducts = listOf(
        CartProduct(1, "I Phone 16", 3499.0),
        CartProduct(2, "Aashirvad Aata Quality Grains 1Kg", 20.0)
    )

}
