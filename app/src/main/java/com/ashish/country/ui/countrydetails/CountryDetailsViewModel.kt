package com.ashish.country.ui.countrydetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ashish.country.model.CountryProvince
import com.ashish.country.repository.CountryRepository
import com.ashish.country.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class CountryDetailsViewModel @ViewModelInject constructor(
    private val postsRepository: CountryRepository
) : BaseViewModel<CountryDetailsState>() {

    fun getCountryDetails(countryId: Int) {
        uiState.value = CountryDetailsState.Loading
        viewModelScope.launch {
            try {
                uiState.value = CountryDetailsState.Success(
                    postsRepository.getCountryProvince(countryId) as ArrayList<CountryProvince>
                )
            } catch (exception: Exception) {
                uiState.value = CountryDetailsState.Error(exception.toString())
            }
        }
    }
}