package com.snowflowerthon.snowman.ui.archive

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.snowflowerthon.snowman.R
import com.snowflowerthon.snowman.data.dto.response.ArchiveDetailiResponseDto
import com.snowflowerthon.snowman.data.dto.response.VoteHistory
import com.snowflowerthon.snowman.data.enums.Clothes
import com.snowflowerthon.snowman.data.enums.Weather
import com.snowflowerthon.snowman.databinding.ItemCardBinding



class GridAdapter(private val dataList: List<VoteHistory>) :
    RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {

            binding.tvTime.text = dataList[position].voteTime

            when (dataList[position].weatherStatus) {
                Weather.CLEAR.toString() -> { binding.ivWeather.setImageResource(R.drawable.img_sunny) }
                Weather.RAIN.toString() -> { binding.ivWeather.setImageResource(R.drawable.ic_rainy) }
                Weather.SNOW.toString() -> { binding.ivWeather.setImageResource(R.drawable.img_snowy) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = dataList.size
}