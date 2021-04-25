package com.ashish.country.ui.countrylist

import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.ashish.country.R
import com.ashish.country.databinding.ActivityCountryListBinding
import com.ashish.country.ui.BaseActivity
import com.ashish.country.ui.countrydetails.CountryDetails
import com.ashish.country.ui.countrydetails.CountryDetails.Companion.COUNTRY_ID
import com.ashish.country.ui.countrydetails.CountryListPresenter
import com.ashish.country.ui.countrydetails.CountryPresentation
import com.ashish.country.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryList : BaseActivity<ActivityCountryListBinding, CountryListViewModel>() {

    override val viewModel: CountryListViewModel by viewModels()
    private lateinit var countryListAdapter: CountryListAdapter

    override fun onObserve() {
        viewModel.uiState().observe(this, {
            showCountryList(it)
        })
        getCountryList()
    }

    private fun getCountryList() {
        if (Constants.isInternetAvailable(this)) {
            viewModel.getCountry()
        } else {
            showErrorDialog(getString(R.string.internet_error))
        }
    }

    private fun showCountryList(countryState: CountryState) {
        when (countryState) {
            is CountryState.Loading -> {
                binding.progressBar.show()
            }
            is CountryState.Success -> {
                binding.progressBar.hide()
                if (countryState.countryList.size > 0) {
                    countryListAdapter = CountryListAdapter { countryItem: CountryPresentation ->
                        onItemClickEvent(countryItem)
                    }
                    countryListAdapter.addItem(
                        CountryListPresenter(
                            countryList = countryState.countryList
                        ).getCountryList() as ArrayList<CountryPresentation>
                    )
                    binding.countryList.adapter = countryListAdapter
                    binding.countryList.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                } else {
                    showEmptyDialog()
                }
            }
            is Error -> {
                binding.progressBar.hide()
                countryState.message?.let { showRetryDialog(it) }
            }
            else -> binding.progressBar.hide()
        }
    }

    private fun showEmptyDialog() {
        showAlertDialog {
            setTitle(R.string.app_name)
            setMessage(R.string.country_empty_list)
            positiveButton(getString(R.string.okay_text)) {
                finish()
            }
            negativeButton(getString(R.string.cancel_text)) {
            }
        }
    }

    private fun showRetryDialog(message: String) {
        showAlertDialog {
            setTitle(R.string.app_name)
            setMessage(message)
            positiveButton(getString(R.string.okay_text)) {
                finish()
            }
            negativeButton {
                getCountryList()
            }
        }
    }

    private fun onItemClickEvent(country: CountryPresentation) {
        startActivity(
            Intent(this, CountryDetails::class.java).apply {
                putExtra(COUNTRY_ID, country.Id)
            }
        )
    }

    override fun getViewBinding(): ActivityCountryListBinding =
        ActivityCountryListBinding.inflate(layoutInflater)
}