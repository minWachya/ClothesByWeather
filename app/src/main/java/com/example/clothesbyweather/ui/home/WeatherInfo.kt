package com.example.clothesbyweather.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clothesbyweather.domain.entity.HomeWeather


@Composable
fun WeatherInfo(modifier: Modifier, weatherList: List<HomeWeather>) {
    Column(
        modifier = modifier
            .padding(vertical = 24.dp)
            .background(color = MaterialTheme.colorScheme.surfaceContainer,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Text(
            modifier = modifier.padding(16.dp, 18.dp, 16.dp, 0.dp),
            text = "6/4",
            style = MaterialTheme.typography.titleMedium
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 18.dp),
        ) {
            items(weatherList) { item ->
                WeatherElement(modifier = modifier, weather = item)
            }
        }
    }
}

@Composable
private fun WeatherElement(modifier: Modifier, weather: HomeWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = weather.time,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            modifier = modifier.padding(vertical = 20.dp),
            text = weather.weatherEmoji,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            modifier = modifier.padding(bottom = 10.dp),
            text = "${weather.temperature}°",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            modifier = modifier.padding(bottom = 8.dp),
            text = "💧${weather.humidity}%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Text(
            text = "☂️ ${weather.precipitation}%",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}