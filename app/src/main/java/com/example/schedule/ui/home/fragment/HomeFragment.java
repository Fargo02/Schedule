package com.example.schedule.ui.home.fragment;

import static org.koin.java.KoinJavaComponent.inject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schedule.R;
import com.example.schedule.ui.home.view_model.HomeViewModel;

import kotlin.Lazy;


public class HomeFragment extends Fragment {

    //private Lazy<HomeViewModel> viewModel = inject(HomeViewModel.class);

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}