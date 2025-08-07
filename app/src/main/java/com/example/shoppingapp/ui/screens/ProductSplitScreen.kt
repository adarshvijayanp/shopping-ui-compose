package com.example.shoppingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shoppingapp.viewmodel.PosViewModel
import com.example.shoppingapp.ui.components.HoldOrderItem

@Composable
fun ProductSplitScreen(
    modifier: Modifier = Modifier,
    viewModel: PosViewModel = viewModel()
) {
    val cartProducts = viewModel.sampleCartProducts
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight()
                .padding(4.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            HoldOrderItem(products = cartProducts)
        }

        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxHeight()
                .padding(4.dp)
                .background(Color(0xFFF8F8F8))
        ) {
            ProductListWithDraggableDetail(viewModel)
        }
    }
}
