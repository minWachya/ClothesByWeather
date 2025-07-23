package com.example.clothesbyweather.di

import com.example.clothesbyweather.BuildConfig
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoRetrofitModule {
    private const val HEADER_AUTHORIZATION = "Authorization"
    private const val KAKAOAK = "KakaoAK "

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class KakaoType

    @Provides
    @Singleton
    @KakaoType
    fun providesKakaoInterceptor(): Interceptor =
        Interceptor { chain ->
            val request = chain.request()
            var response = chain.proceed(
                request
                    .newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, KAKAOAK + BuildConfig.KAKAO_REST_API_KEY)
                    .addHeader("content-type", "application/json;charset=UTF-8")
                    .build()
            )
            response
        }

    @Provides
    @Singleton
    @KakaoType
    fun providesKakaoOkHttpClient(@KakaoType interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    @KakaoType
    fun providesKakaoRetrofit(@KakaoType okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/v2/")
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            .build()
}