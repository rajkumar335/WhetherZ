package com.example.whetherz;

import androidx.core.content.ContextCompat;


import androidx.lifecycle.ViewModelProvider;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.whetherz.databinding.MainScreenFragmentBinding;

import java.util.List;

public class MainScreen extends Fragment {
    private WhetherDataViewModel whetherDataViewModel;
    WhetherDataAdapter mWhetherDataAdapter;

    private MainScreenFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainScreenFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        whetherDataViewModel = new ViewModelProvider(this).get(WhetherDataViewModel.class);

        mWhetherDataAdapter = new WhetherDataAdapter(requireActivity().getApplicationContext());
        mWhetherDataAdapter.setItemClickListener(requiredDayData -> {
            MainScreenDirections.ShowDayWhetherData action = MainScreenDirections.showDayWhetherData(requiredDayData);
            Navigation.findNavController(v).navigate(action);
        });

        binding.weekWhetherRecyclerView.setAdapter(mWhetherDataAdapter);
        binding.weekWhetherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        whetherDataViewModel.getWhetherdata().observe(getViewLifecycleOwner(), whetherData -> {
            binding.TodaysWhether.setBackgroundColor(ContextCompat.getColor(requireActivity().getApplicationContext(), IconAndBackgroundColorPicker.getBackgroudWarmthColor(whetherData.get(0).getMaxTemp(),whetherData.get(0).getMinTemp())));
            binding.TodayWhetherState.setImageResource(IconAndBackgroundColorPicker.getCurrentWhetherIconLarge(whetherData.get(0).getWhetherStateAbbr()));
            binding.TodayWhetherStateData.setText(whetherData.get(0).getWhetherStateName());
            binding.TodayMaxTemp.setText(getString(R.string.max_temp,whetherData.get(0).getMaxTemp()));
            binding.TodayMinTemp.setText(getString(R.string.min_temp,whetherData.get(0).getMinTemp()));
            binding.TodaysDate.setText(getString(R.string.todaysDate,whetherData.get(0).getFormattedDate()));

            List<WhetherData> RequiredList = whetherData.subList(1,whetherData.size());
            mWhetherDataAdapter.submitList(RequiredList);
        });


        return v;
    }

    public interface onItemClickListener{
        void onClick(WhetherData requiredDayData);
    }
}