package com.ashish.country.ui.countrylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.ashish.country.model.Country
import com.ashish.country.repository.CountryRepository
import com.ashish.country.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class CountryListViewModel @ViewModelInject constructor(
    private val postsRepository: CountryRepository
) : BaseViewModel<CountryState>() {

    fun getCountry() {
        uiState.value = CountryState.Loading
        viewModelScope.launch {
            try {
                uiState.value = CountryState.Success(
                    postsRepository.getCountry() as ArrayList<Country>
                )
            } catch (exception: Exception) {
                uiState.value = CountryState.Error("Error retrieving data")
            }
        }
    }
}