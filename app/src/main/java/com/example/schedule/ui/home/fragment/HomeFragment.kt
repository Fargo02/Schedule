package com.example.schedule.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.schedule.databinding.FragmentHomeBinding
import com.example.schedule.ui.home.view_model.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}