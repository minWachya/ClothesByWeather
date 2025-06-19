package com.example.clothesbyweather.ui.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import java.io.IOException
import java.util.Locale

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

private fun getAddress(context: Context, lat: Double, lng: Double): String = try {
    val geocoder = Geocoder(context, Locale.KOREA)
    val address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
    address[0].thoroughfare
} catch (e: IOException) {
    Toast.makeText(context, "주소를 가져 올 수 없습니다", Toast.LENGTH_SHORT).show()
    "주소 다시 가져오기"
}

private fun getCurrentLocation(context: Context): String {
    var fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        return "권한 설정 필요"
    }

    var address = "클릭하여 주소 다시 가져오기"
    fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
        override fun isCancellationRequested() = false
    })
        .addOnSuccessListener { location ->
            if (location != null) {
                val lat = location.latitude
                val lon = location.longitude
                address = getAddress(context, lat, lon)
                Log.d("mmm PlaceInfo", address)
            }
        }
    return address
}