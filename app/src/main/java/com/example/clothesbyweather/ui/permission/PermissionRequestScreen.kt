package com.example.clothesbyweather.ui.permission

import android.Manifest
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.net.Uri
import android.provider.Settings
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun PermissionRequestScreen(modifier: Modifier) {
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

    Surface(
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(vertical = 24.dp, horizontal = 16.dp),
        ) {
            Text(
                text = "정확한 날씨 정보를 위해\n아래의 권한을 허용해주세요.",
                modifier = modifier.padding(vertical = 20.dp, horizontal = 20.dp),
                style = MaterialTheme.typography.titleLarge
            )
            LazyColumn(
                modifier = modifier
                    .weight(1F)
            ) {
                items(permissionList) { permissionInfo ->
                    PermissionItem(
                        permissionInfo = permissionInfo
                    )
                }
            }

            if (permissionState.allPermissionsGranted) {
                Row(modifier = modifier.padding(16.dp)) {
                    Text(text = "설정 완료", modifier = modifier.background(Color.Transparent))
                }
            } else if (permissionState.shouldShowRationale) {
                Text(
                    text = "정확한 날씨 정보를 얻기 위해 위치 정보가 꼭 필요합니다.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
                val context = LocalContext.current
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context.packageName, null)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }

                Button(
                    modifier = modifier
                        .padding(vertical = 20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    onClick = { context.startActivity(intent) }
                ) {
                    Text(
                        text = "직접 권한 설정하기",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            } else {
                Button(
                    modifier = modifier
                        .padding(vertical = 20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    ),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    onClick = { permissionState.launchMultiplePermissionRequest() }
                ) {
                    Text(
                        text = "권한 설정하기",
                        style = MaterialTheme.typography.titleLarge,
                    )
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
            PermissionRequestScreen(modifier = Modifier)
        }
    }
}