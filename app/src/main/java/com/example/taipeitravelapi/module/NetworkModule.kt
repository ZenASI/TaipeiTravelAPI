package com.example.taipeitravelapi.module

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    // api 所需的 module
    @Singleton
    @Provides
    fun provideHttpLog(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("HttpLog debug: ", it)
        }).setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideApiServices(retrofit: Retrofit): AppApiModule =
        retrofit.create(AppApiModule::class.java)

    @Singleton
    @Provides
    fun provideGsonConvert(): GsonConverterFactory = GsonConverterFactory.create()


    @Named("API")
    @Singleton
    @Provides
    fun provideAPIOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(5, TimeUnit.SECONDS)
//            .retryOnConnectionFailure(true)
        .readTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .build()


    // ref https://www.travel.taipei/open-api
    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @Named("API") okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://www.travel.taipei/open-api/")
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}