package com.ashish.country.repository

import com.ashish.country.model.Country
import com.ashish.country.model.CountryProvince


interface Repository {
    suspend fun getCountry(): List<Country>
    suspend fun getCountryProvince(countryId: Int): List<CountryProvince>
}