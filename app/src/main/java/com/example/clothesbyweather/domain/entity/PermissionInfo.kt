package com.example.clothesbyweather.domain.entity

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class PermissionInfo(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    val imageVector: ImageVector
)