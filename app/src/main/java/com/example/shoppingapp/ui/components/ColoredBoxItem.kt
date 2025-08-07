package com.example.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColoredBoxItem() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val colors = listOf(
            Color.Gray, Color.Green, Color.Cyan, Color.Red,
            Color.Magenta, Color.Black, Color.Yellow, Color.Blue,
            Color.Gray, Color.Green, Color.Cyan, Color.Red,
            Color.Magenta, Color.Black, Color.Yellow, Color.Blue
        )
        val itemCount = 16
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(itemCount) { index ->
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .background(colors[index])
                )
            }
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = "Checkout -> AED 160.00")
        }
    }
}
