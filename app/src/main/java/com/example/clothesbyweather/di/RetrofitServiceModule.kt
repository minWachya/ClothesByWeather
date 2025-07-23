package com.example.clothesbyweather.di

import com.example.clothesbyweather.data.remote.service.HomeService
import com.example.clothesbyweather.data.remote.service.KakaoLocationService
import com.example.clothesbyweather.di.KakaoRetrofitModule.KakaoType
import com.example.clothesbyweather.di.RetrofitModule.NormalType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {
    @Provides
    fun providesHomeService(@NormalType retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    fun providesLocationService(@KakaoType retrofit: Retrofit): KakaoLocationService =
        retrofit.create(KakaoLocationService::class.java)
}