package com.example.schedule.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedule.databinding.FragmentHomeBinding
import com.example.schedule.domain.model.StationInfo
import com.example.schedule.ui.home.RouteAdapter
import com.example.schedule.ui.home.SearchState
import com.example.schedule.ui.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModel<HomeViewModel>()

    private var routes = ArrayList<StationInfo>()

    private val routesAdapter = RouteAdapter()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        routesAdapter.routes = routes
        binding.routeList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.routeList.adapter = routesAdapter

        viewModel.observeState().observe(viewLifecycleOwner) {
            render(it)
        }

        binding.btnFound.setOnClickListener {
            viewModel.requestToServer("s9879631", "s9600771", "2024-09-30")
        }
    }


    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> showLoading()
            is SearchState.Content -> showContent(state.stationAndData)
            is SearchState.Error -> showError()
            is SearchState.Empty -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.listGroup.isVisible = false
        binding.progressBar.isVisible = true
        binding.routeList.isVisible = false
    }

    private fun showContent(routes: List<StationInfo>) {
        binding.progressBar.isVisible = false
        binding.routeList.isVisible = true
        routesAdapter.routes.clear()
        routesAdapter.routes.addAll(routes)
        routesAdapter.notifyDataSetChanged()
    }

    private fun showError() {

    }

    private fun showEmpty() {

    }
}