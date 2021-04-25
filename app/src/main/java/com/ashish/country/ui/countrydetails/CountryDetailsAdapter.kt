package com.ashish.country.ui.countrydetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.country.R
import com.ashish.country.databinding.CountryProvinceItemViewBinding
import com.ashish.country.model.CountryProvince

class CountryDetailsAdapter(
    private val onItemClick: (countryProvince: CountryProvince) -> Unit
) : RecyclerView.Adapter<CountryDetailsAdapter.ViewHolder>() {
    private var items = ArrayList<CountryProvince>()

    fun addItem(items: ArrayList<CountryProvince>) {
        this.items = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.country_province_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var countryObject: CountryProvince
        private val binding = CountryProvinceItemViewBinding.bind(itemView)

        fun bind(countryProvince: CountryProvince) = with(itemView) {
            countryObject = countryProvince
            countryObject.apply {
                binding.countryProvinceNameText.text = (countryObject.Name)
            }
            setOnClickListener {
                onItemClick(countryObject)
            }
        }
    }
}