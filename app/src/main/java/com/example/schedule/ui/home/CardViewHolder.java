package com.example.schedule.ui.home;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.databinding.FragmentHomeBinding;
import com.example.schedule.domain.model.ResultItems;

public class CardViewHolder extends RecyclerView.ViewHolder {

    private final FragmentHomeBinding binding;

    public CardViewHolder(FragmentHomeBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(ResultItems model) {

    }
}
