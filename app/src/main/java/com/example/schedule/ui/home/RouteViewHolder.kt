package com.example.schedule.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.R
import com.example.schedule.databinding.ResultItemBinding
import com.example.schedule.domain.model.StationInfo

class RouteViewHolder(
    private val binding: ResultItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: StationInfo) {
        when (model.transportType) {
            "plane" -> {
                binding.transportType.setImageResource(R.drawable.plane_ic)
            }
            "train" -> {
                binding.transportType.setImageResource(R.drawable.suburban_ic)
            }
            "suburban" -> {
                binding.transportType.setImageResource(R.drawable.suburban_ic)
            }
            "bus" -> {
                binding.transportType.setImageResource(R.drawable.bus_ic)
            }
        }
        binding.fromToTitle.text = model.shortTitle
        binding.companyName.text = model.companyName
        binding.transportName.text = model.vehicle
        binding.startDate.text = model.departure
        binding.startTime.text = model.departure
        binding.startPlace.text = model.titleFrom
        binding.duration.text = model.duration
        binding.endDate.text = model.arrival
        binding.endTime.text = model.arrival
        binding.endPlace.text = model.titleTo
    }
}