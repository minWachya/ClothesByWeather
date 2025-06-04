package com.example.clothesbyweather.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clothesbyweather.ui.theme.ClothesByWeatherTheme

@Composable
fun Home(modifier: Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier.padding(vertical = 8.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "25°",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "맑음",
                modifier = Modifier.padding(top = 23.dp, bottom = 48.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            ClothesInfo()
            WeatherInfo(modifier)
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
        Home(modifier = Modifier)
    }
}