package com.ashish.country.ui.countrylist

import com.ashish.country.model.Country


sealed class CountryState {
    object Loading : CountryState()
    data class Success(val countryList: ArrayList<Country>) : CountryState()
    data class Error(val message: String) : CountryState()
}