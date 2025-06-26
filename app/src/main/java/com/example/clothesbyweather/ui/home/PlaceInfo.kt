package com.example.clothesbyweather.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlaceInfo(address: String, getAddress: () -> Unit) {
    Row{
        TextButton(
            onClick = getAddress,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(14.dp)
                .weight(1f)
        ) {
            Text(
                text = "📍$address",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}