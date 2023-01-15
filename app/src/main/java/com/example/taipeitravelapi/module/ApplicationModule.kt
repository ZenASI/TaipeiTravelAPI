package com.example.taipeitravelapi.module

import android.content.Context
import com.example.taipeitravelapi.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideApplication(@ApplicationContext context: Context) = context as App
}