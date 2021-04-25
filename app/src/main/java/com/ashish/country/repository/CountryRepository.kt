package com.ashish.country.repository

import com.ashish.country.datasource.DataSource
import com.ashish.country.model.Country
import com.ashish.country.model.CountryProvince
import javax.inject.Inject

class CountryRepository @Inject constructor(private val dataSource: DataSource) : Repository {
    override suspend fun getCountry(): List<Country> = dataSource.getCountry()
    override suspend fun getCountryProvince(countryId: Int): List<CountryProvince> {
        return dataSource.getCountryProvince(countryId)
    }
}