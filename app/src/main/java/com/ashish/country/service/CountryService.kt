package com.ashish.country.service

import com.ashish.country.model.Country
import com.ashish.country.model.CountryProvince
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("country")
    suspend fun getCountry(): List<Country>

    @GET("country/{ID}/province")
    suspend fun getCountryProvince(
        @Path("ID") ID: Int?
    ): List<CountryProvince>
}