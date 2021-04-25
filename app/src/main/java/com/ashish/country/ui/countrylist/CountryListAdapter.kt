package com.ashish.country.ui.countrylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.country.R
import com.ashish.country.databinding.CountryItemViewBinding
import com.ashish.country.ui.countrydetails.CountryPresentation
import com.bumptech.glide.Glide

class CountryListAdapter(
    private val onItemClick: (country: CountryPresentation) -> Unit
) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {
    private var items = ArrayList<CountryPresentation>()

    fun addItem(items: ArrayList<CountryPresentation>) {
        this.items = items
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var countryObject: CountryPresentation
        private val binding = CountryItemViewBinding.bind(itemView)

        fun bind(country: CountryPresentation) = with(itemView) {
            countryObject = country
            countryObject.apply {
                binding.countryNameText.text = (countryObject.Name)
            }
            Glide
                .with(this)
                .load(countryObject.flagUrl)
                .centerCrop()
                .placeholder(R.drawable.world)
                .into(binding.countryImage)
            setOnClickListener {
                onItemClick(countryObject)
            }
        }
    }
}