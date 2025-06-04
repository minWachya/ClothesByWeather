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
fun ClothesInfo() {
    Row{
        TextButton(
            onClick = {},
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(14.dp)
                .weight(1f)
        ) {
            Text(
                text = "⭐ 민소매, 반팔, 반바지, 원피스",
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}