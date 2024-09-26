package com.example.schedule.ui.home.fragment;


import static org.koin.java.KoinJavaComponent.inject;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.schedule.R;
import com.example.schedule.databinding.ActivityRootBinding;
import com.example.schedule.databinding.FragmentHomeBinding;
import com.example.schedule.ui.home.view_model.HomeViewModel;

import java.util.Calendar;

import kotlin.Lazy;


public class HomeFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentHomeBinding binding;

    private DatePickerDialog datePickerDialog;

    private Lazy<HomeViewModel> viewModel = inject(HomeViewModel.class);

    public HomeFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        binding.compareArrows.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.i("1234","нажали на элемент");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        Log.i("1234",selectedDate);

    }

    public void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(
                requireContext(), this, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}