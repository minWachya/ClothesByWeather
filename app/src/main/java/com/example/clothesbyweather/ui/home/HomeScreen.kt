package com.example.clothesbyweather.ui.home

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clothesbyweather.ui.theme.ClothesByWeatherTheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    weatherViewModel: HomeViewModel = viewModel()) {
    val weatherList by weatherViewModel.weatherList.collectAsStateWithLifecycle()
    val curTemperature by weatherViewModel.curTemperature.collectAsStateWithLifecycle()
    val curWeather by weatherViewModel.curWeather.collectAsStateWithLifecycle()
    val clothesByWeather by weatherViewModel.clothesByWeather.collectAsStateWithLifecycle()

    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier.padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "${curTemperature}°",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = curWeather,
                modifier = Modifier.padding(top = 23.dp, bottom = 48.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            ClothesInfo(clothesByWeather)
            WeatherInfo(modifier = modifier, weatherList = weatherList)
            PlaceInfo()
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun HomePreview() {
    ClothesByWeatherTheme {
        HomeScreen(modifier = Modifier)
    }
}