package com.ashish.country.di

import com.ashish.country.datasource.DataSource
import com.ashish.country.repository.CountryRepository
import com.ashish.country.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {
    @Provides
    @ActivityRetainedScoped
    fun provideRepository(dataSource: DataSource): Repository {
        return CountryRepository(dataSource)
    }
}