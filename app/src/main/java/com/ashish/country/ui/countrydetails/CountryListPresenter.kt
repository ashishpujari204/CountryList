package com.ashish.country.ui.countrydetails

import com.ashish.country.BuildConfig
import com.ashish.country.model.Country
import java.util.*
import kotlin.collections.ArrayList

class CountryListPresenter(
    private val countryList : ArrayList<Country>
) {
    fun getCountryList(): List<CountryPresentation>? {
        return countryList.map { it.toCountryPresentation() }
    }
}
fun Country.toCountryPresentation(): CountryPresentation =
    CountryPresentation(
        Name = Name,
        Id = ID,
        code = Code,
        flagUrl = "${BuildConfig.FLAG_URL}${Code.toLowerCase(Locale.ENGLISH)}-flag.gif"
    )

data class CountryPresentation(
    val Name: String,
    val Id: Int,
    var code: String,
    val flagUrl: String
)