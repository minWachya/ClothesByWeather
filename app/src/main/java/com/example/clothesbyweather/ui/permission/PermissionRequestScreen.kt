package com.example.clothesbyweather.ui.permission

import android.Manifest
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clothesbyweather.R
import com.example.clothesbyweather.domain.entity.PermissionInfo
import com.example.clothesbyweather.ui.theme.ClothesByWeatherTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequestScreen() {
    val permissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
    )

    val permissionList = listOf(
        PermissionInfo(
            titleRes = R.string.fine_location_title,
            descriptionRes = R.string.fine_location_description,
            imageVector = Icons.Default.LocationOn
        )
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(permissionList) { permissionInfo ->
                PermissionItem(
                    permissionInfo = permissionInfo
                )
            }
        }

        if (permissionState.allPermissionsGranted) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "설정 완료", modifier = Modifier.background(Color.Transparent))
            }
        }
        else if(permissionState.shouldShowRationale) {
            Text("정확한 날씨 정보를 얻기 위해 위치 정보가 꼭 필요합니다.")
            val context = LocalContext.current
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            Button(onClick = { context.startActivity(intent) }) {
                Text("직접 권한 설정하기")
            }
        }
        else {
            Button(onClick = { permissionState.launchMultiplePermissionRequest() }) {
                Text("권한 설정하기")
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
            PermissionRequestScreen()
        }
    }
}