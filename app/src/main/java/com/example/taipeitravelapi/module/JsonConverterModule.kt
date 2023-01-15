package com.example.taipeitravelapi.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class JsonConverterModule {

    @Singleton
    @Provides
    fun providerGson(): Gson = GsonBuilder().setLenient().create()
}