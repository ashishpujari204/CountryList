package com.ashish.country.di

import androidx.viewbinding.BuildConfig
import com.ashish.country.BuildConfig.BASE_URL
import com.ashish.country.datasource.CountryDataSource
import com.ashish.country.datasource.DataSource
import com.ashish.country.service.CountryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): CountryService = retrofit.create(
        CountryService::class.java
    )

    @Provides
    @Singleton
    fun provideDataSource(countryService: CountryService):
            DataSource = CountryDataSource(countryService)

    private val interceptor: Interceptor
        get() = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
}