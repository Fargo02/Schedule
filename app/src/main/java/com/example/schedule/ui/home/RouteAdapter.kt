package com.example.schedule.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schedule.databinding.ResultItemBinding
import com.example.schedule.domain.model.StationInfo

class RouteAdapter(): RecyclerView.Adapter<RouteViewHolder>() {

    var routes = ArrayList<StationInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteViewHolder {
        val layoutInspector = LayoutInflater.from(parent.context)
        return RouteViewHolder(ResultItemBinding.inflate(layoutInspector, parent, false))
    }

    override fun onBindViewHolder(holder: RouteViewHolder, position: Int) =holder.bind(routes[position])

    override fun getItemCount(): Int = routes.size
}