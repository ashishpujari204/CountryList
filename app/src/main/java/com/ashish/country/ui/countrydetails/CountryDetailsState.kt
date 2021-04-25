package com.ashish.country.ui.countrydetails

import com.ashish.country.model.CountryProvince


sealed class CountryDetailsState {
    object Loading : CountryDetailsState()
    data class Success(val countryProvinceList: ArrayList<CountryProvince>) : CountryDetailsState()
    data class Error(val message: String) : CountryDetailsState()
}