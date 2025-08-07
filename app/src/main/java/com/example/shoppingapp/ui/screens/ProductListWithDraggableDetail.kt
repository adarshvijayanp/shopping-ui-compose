package com.example.shoppingapp.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.ui.components.ColoredBoxItem
import com.example.shoppingapp.viewmodel.PosViewModel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ProductListWithDraggableDetail(
    viewModel: PosViewModel,
    modifier: Modifier = Modifier
) {
    val products = viewModel.products
    val selectedProduct = viewModel.selectedProduct

    val scope = rememberCoroutineScope()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val minHeight = 120.dp
    val maxHeight = screenHeight - 50.dp
    val detailInitialHeight = screenHeight / 2
    val offsetY = remember { Animatable(screenHeight.value) }
    val density = LocalDensity.current.density

    LaunchedEffect(selectedProduct) {
        if (selectedProduct != null) {
            offsetY.animateTo(
                targetValue = screenHeight.value - detailInitialHeight.value,
                animationSpec = tween(350)
            )
        } else {
            offsetY.animateTo(
                targetValue = screenHeight.value,
                animationSpec = tween(350)
            )
        }
    }

    Box(modifier.fillMaxSize()) {
        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.6f)
                    .background(Color(0xFFF8F8F8))
            ) {
                items(products.size) { idx ->
                    val product = products[idx]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                            .clickable { viewModel.selectProduct(product) },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier
                                .size(48.dp)
                                .background(Color.Gray))
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .background(Color.LightGray)
            ) {
                ColoredBoxItem()
            }
        }

        if (selectedProduct != null) {
            val pxOffsetY = with(LocalDensity.current) { offsetY.value.dp.toPx() }
            Box(
                modifier = Modifier
                    .offset { IntOffset(0, pxOffsetY.roundToInt()) }
                    .fillMaxWidth()
                    .height(screenHeight - offsetY.value.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.large)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { _, dragAmount ->
                                val newOffset = (offsetY.value + dragAmount / density)
                                    .coerceIn(
                                        screenHeight.value - maxHeight.value,
                                        screenHeight.value - minHeight.value
                                    )
                                scope.launch {
                                    offsetY.snapTo(newOffset)
                                }
                            },
                            onDragEnd = {
                                scope.launch {
                                    if (offsetY.value < screenHeight.value - (maxHeight.value + minHeight.value) / 2) {
                                        offsetY.animateTo(screenHeight.value - maxHeight.value)
                                    } else {
                                        offsetY.animateTo(screenHeight.value - minHeight.value)
                                    }
                                }
                            }
                        )
                    }
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(width = 40.dp, height = 4.dp)
                        .align(Alignment.TopCenter)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(2.dp),
                            clip = false
                        )
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    ProductDetailScreen(
                        productName = selectedProduct.name,
                        colors = selectedProduct.colors,
                        models = selectedProduct.models,
                        storages = selectedProduct.storages,
                        selectedColor = viewModel.selectedColor,
                        selectedModel = viewModel.selectedModel,
                        selectedStorage = viewModel.selectedStorage,
                        onColorSelected = viewModel::selectColor,
                        onModelSelected = viewModel::selectModel,
                        onStorageSelected = viewModel::selectStorage,
                        modifierOptions = viewModel.modifierOptions,
                        selectedModifiers = viewModel.selectedModifiers,
                        onIncrementModifier = viewModel::incrementModifier,
                        onDecrementModifier = viewModel::decrementModifier,
                        priceText = "AED ${viewModel.selectedPrice}",
                        onClose = viewModel::clearSelection,
                    )
                }
            }
        }
    }
}
