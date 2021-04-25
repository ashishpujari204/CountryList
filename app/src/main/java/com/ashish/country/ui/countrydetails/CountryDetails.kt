package com.ashish.country.ui.countrydetails

import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.ashish.country.R
import com.ashish.country.databinding.ActivityCountryDetailsBinding
import com.ashish.country.ui.BaseActivity
import com.ashish.country.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetails : BaseActivity<ActivityCountryDetailsBinding, CountryDetailsViewModel>() {

    override val viewModel: CountryDetailsViewModel by viewModels()
    private lateinit var countryDetailsAdapter: CountryDetailsAdapter

    companion object {
        const val COUNTRY_ID = "countryId"
        const val DEFAULT_COUNTRY_ID = 104
    }

    private var countryId: Int = DEFAULT_COUNTRY_ID

    override fun onObserve() {
        viewModel.uiState().observe(this, {
            showCountryList(it)
        })
    }

    override fun onStart() {
        super.onStart()
        countryId = intent.getIntExtra(COUNTRY_ID, DEFAULT_COUNTRY_ID)
        getCountryDetails(countryId)
    }

    private fun getCountryDetails(countryId: Int) {
        if (Constants.isInternetAvailable(this)) {
            viewModel.getCountryDetails(countryId)
        } else {
            showErrorDialog(getString(R.string.internet_error))
        }
    }

    private fun showCountryList(countryDetailsState: CountryDetailsState) {
        when (countryDetailsState) {
            is CountryDetailsState.Loading -> {
                binding.progressBar.show()
            }
            is CountryDetailsState.Success -> {

                binding.progressBar.hide()
                if (countryDetailsState.countryProvinceList.size > 0) {
                    countryDetailsAdapter = CountryDetailsAdapter {
                    }
                    countryDetailsAdapter.addItem(countryDetailsState.countryProvinceList)
                    binding.countryProvinceList.adapter = countryDetailsAdapter
                    binding.countryProvinceList.addItemDecoration(
                        DividerItemDecoration(
                            this,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                } else {
                    showEmptyDialog()
                }
            }
            is CountryDetailsState.Error -> {
                binding.progressBar.hide()
                showRetryDialog(countryDetailsState.message)
            }
        }
    }

    private fun showEmptyDialog() {
        showAlertDialog {
            setTitle(R.string.app_name)
            setMessage(R.string.country_province_empty_list)
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
                getCountryDetails(countryId)
            }
        }
    }

    override fun getViewBinding(): ActivityCountryDetailsBinding =
        ActivityCountryDetailsBinding.inflate(layoutInflater)
}