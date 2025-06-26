package com.example.clothesbyweather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clothesbyweather.ui.home.HomeScreen
import com.example.clothesbyweather.ui.permission.PermissionRequestScreen
import com.example.clothesbyweather.ui.theme.ClothesByWeatherTheme
import com.example.clothesbyweather.util.CoordinateConverter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.gms.tasks.Task
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.Locale

@AndroidEntryPoint
@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClothesByWeatherTheme {
                val permissionState = rememberMultiplePermissionsState(
                    listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    ),
                )

                Surface(modifier = Modifier.Companion.fillMaxSize()) {
                    if (permissionState.allPermissionsGranted) {
//                        getLastUserLocation(this)
                        HomeScreen(modifier = Modifier)
                    }
                    else PermissionRequestScreen(modifier = Modifier)
                }
            }
        }
    }
}
//
//@SuppressLint("MissingPermission")
//private fun getLastUserLocation(context: Context) {
//    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
//    fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
//        override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken = CancellationTokenSource().token
//        override fun isCancellationRequested(): Boolean = false
//    }).addOnSuccessListener { location ->
//            location?.let {
//                Log.d("mmm plae", "${it.latitude}, ${it.longitude}")
//                val coordinatesXy = CoordinateConverter().convertToXy(lat = it.latitude, lon = it.longitude)
//                val placeName = getAddress(context, it.latitude, it.longitude)
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.d("mmm plae", exception.message.toString())
//        }
//}
//
//private fun getAddress(context: Context, lat: Double, lng: Double): String? = try {
//    Log.d("mmm getAddress", "getAddress1")
//    val geocoder = Geocoder(context, Locale.KOREA)
//    val address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
//    Log.d("mmm getAddress", address.toString())
//    address[0].thoroughfare
//} catch (e: IOException) {
//    null
//}

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