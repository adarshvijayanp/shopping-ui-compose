package com.example.shoppingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.model.ModifierOption
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ProductDetailScreen(
    productName: String,
    colors: List<String>,
    models: List<String>,
    storages: List<String>,
    selectedColor: String,
    selectedModel: String,
    selectedStorage: String,
    onColorSelected: (String) -> Unit,
    onModelSelected: (String) -> Unit,
    onStorageSelected: (String) -> Unit,
    modifierOptions: List<ModifierOption>,
    selectedModifiers: Map<Int, Int>,
    onIncrementModifier: (Int) -> Unit,
    onDecrementModifier: (Int) -> Unit,
    priceText: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        IconButton(onClick = onClose, modifier = Modifier.align(Alignment.End)) {
            Icon(Icons.Default.Close, contentDescription = "Back")
        }
        Row(modifier = Modifier.weight(1f)) {
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(Color.LightGray)
            ) {
                Text(productName, modifier = Modifier.align(Alignment.Center))
            }
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(start = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Choose Color:")
                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    colors.forEach {
                        FilterChip(
                            selected = selectedColor == it,
                            onClick = { onColorSelected(it) },
                            label = { Text(it) }
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))

                Text("Choose Model:")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    models.forEach {
                        FilterChip(
                            selected = selectedModel == it,
                            onClick = { onModelSelected(it) },
                            label = { Text(it) }
                        )
                    }
                }
                Spacer(Modifier.height(12.dp))

                Text("Choose Storage:")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    storages.forEach {
                        FilterChip(
                            selected = selectedStorage == it,
                            onClick = { onStorageSelected(it) },
                            label = { Text(it) }
                        )
                    }
                }
                ModifierSelector(
                    modifierOptions = modifierOptions,
                    selectedModifiers = selectedModifiers,
                    onIncrement = onIncrementModifier,
                    onDecrement = onDecrementModifier
                )
            }
        }

        Text(
            text = priceText,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.End)
        )
    }
}



@Composable
fun ModifierSelector(
    modifierOptions: List<ModifierOption>,
    selectedModifiers: Map<Int, Int>,
    onIncrement: (id: Int) -> Unit,
    onDecrement: (id: Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedModifier by remember { mutableIntStateOf(0) }

    Column {
        Text("Modifiers:")
        Box {
            Button(onClick = { expanded = true }) {
                Text(modifierOptions[selectedModifier].name)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                modifierOptions.forEachIndexed { item, option ->
                    DropdownMenuItem(
                        text = { Text(option.name) },
                        onClick = {
                            selectedModifier = item
                            expanded = false
                        }
                    )
                }
            }
        }

        val selected = modifierOptions[selectedModifier]
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Qty:")
            IconButton(onClick = { onDecrement(selected.id) }) { Text("−") }
            Text("${selectedModifiers[selected.id] ?: 0}")
            IconButton(onClick = { onIncrement(selected.id) }) { Text("+") }
            Spacer(Modifier.width(8.dp))
            Text("AED ${selected.price}")
        }
    }
}
