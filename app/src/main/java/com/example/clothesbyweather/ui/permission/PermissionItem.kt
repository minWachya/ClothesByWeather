package com.example.clothesbyweather.ui.permission

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.clothesbyweather.domain.entity.PermissionInfo
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionItem(
    permissionInfo: PermissionInfo
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Icon(
            imageVector = permissionInfo.imageVector,
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = stringResource(id = permissionInfo.titleRes))
            Text(text = stringResource(id = permissionInfo.descriptionRes))
        }
    }
}