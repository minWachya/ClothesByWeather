package com.example.clothesbyweather.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.clothesbyweather.data.remote.repository.KakaoLocationRepositoryImpl
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KakaoLocationViewModel @Inject constructor(
    application: Application,
    private val kakaoLocationRepository: KakaoLocationRepositoryImpl,
) : AndroidViewModel(application) {
    private val _address = MutableStateFlow<String>("주소 가져오는 중...")
    val address: StateFlow<String> = _address.asStateFlow()

    init{
        getLocation()
    }

    private fun getKakaoLocations(x: String, y: String) = viewModelScope.launch {
        kakaoLocationRepository.getKakaoLocation(x, y)
            .onSuccess {
                _address.value = it.locations[0].address.name
            }.onFailure {
                Log.d("mmm KakaoLocationViewModel", it.message?: "api 연결 실패")
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(application.applicationContext)
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken = CancellationTokenSource().token
            override fun isCancellationRequested(): Boolean = false
        }).addOnSuccessListener { location ->
            location?.let {
                getKakaoLocations(x = it.longitude.toString(), y = it.latitude.toString())
            }
        }
            .addOnFailureListener { exception ->
                Log.d("mmm place", exception.message.toString())
            }
    }
}