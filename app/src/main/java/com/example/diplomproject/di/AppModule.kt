package com.example.diplomproject.di

import com.example.diplomproject.data.network.KPApi
import com.example.diplomproject.data.network.KPApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): KPApi = KPApiService.getClient()
}