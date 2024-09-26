package com.example.schedule.ui.root;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.schedule.databinding.ActivityRootBinding;

public class RootActivity extends AppCompatActivity {

    private ActivityRootBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRootBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}