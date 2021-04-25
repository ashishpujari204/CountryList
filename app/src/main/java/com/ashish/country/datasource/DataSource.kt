package com.ashish.country.datasource

import com.ashish.country.model.Country
import com.ashish.country.model.CountryProvince


interface DataSource {
    suspend fun getCountry(): List<Country>
    suspend fun getCountryProvince(countryId : Int): List<CountryProvince>
}