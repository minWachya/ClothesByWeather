package com.example.clothesbyweather

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesbyweather.ui.home.HomeScreen
import com.example.clothesbyweather.ui.permission.PermissionRequestScreen
import com.example.clothesbyweather.ui.theme.ClothesByWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClothesByWeatherTheme {
                Surface(modifier = Modifier.Companion.fillMaxSize()) {
//                    HomeScreen(
//                        modifier = Modifier
//                    )
                    PermissionRequestScreen(modifier = Modifier)
                }
            }
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun MainPreview() {
    ClothesByWeatherTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(
                modifier = Modifier
            )
        }
    }
}