package com.ashish.country.datasource

import com.ashish.country.model.Country
import com.ashish.country.model.CountryProvince
import com.ashish.country.service.CountryService
import javax.inject.Inject

class CountryDataSource @Inject constructor(
    private val countryService: CountryService
) : DataSource {
    override suspend fun getCountry(): List<Country> = countryService.getCountry()

    override suspend fun getCountryProvince(countryId: Int): List<CountryProvince> {
        return countryService.getCountryProvince(countryId)
    }
}