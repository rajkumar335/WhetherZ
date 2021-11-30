package com.example.whetherz;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whetherz.databinding.FragmentDayWhetherDataBinding;

import org.jetbrains.annotations.NotNull;

public class DayWhetherData extends Fragment {

    private FragmentDayWhetherDataBinding binding;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentDayWhetherDataBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        WhetherData currentDayWhetherData = DayWhetherDataArgs.fromBundle(getArguments()).getCurrentDayWhether();

        binding.rootView.setBackgroundColor(ContextCompat.getColor(requireActivity(),IconAndBackgroundColorPicker.getBackgroudWarmthColor(currentDayWhetherData.getMaxTemp(),currentDayWhetherData.getMinTemp())));

        binding.CurrentDayDate.setText(getString(R.string.requiredDate,currentDayWhetherData.getDay(),currentDayWhetherData.getFormattedDate()));
        binding.CurrentDayWhetherState.setImageResource(IconAndBackgroundColorPicker.getCurrentWhetherIconLarge(currentDayWhetherData.getWhetherStateAbbr()));
        binding.CurrentDayMaxTemp.setText(getString(R.string.max_temp,currentDayWhetherData.getMaxTemp()));
        binding.CurrentDayMinTemp.setText(getString(R.string.min_temp,currentDayWhetherData.getMinTemp()));
        binding.CurrentDayWhetherStateData.setText(currentDayWhetherData.getWhetherStateName());

        binding.humidityValue.setText(getString(R.string.humidity_val,currentDayWhetherData.getHumidity())+"%");
        binding.airPressureValue.setText(getString(R.string.air_pressure_val,currentDayWhetherData.getAirPressure()));
        binding.visibilityValue.setText(getString(R.string.visibility_val,currentDayWhetherData.getVisibility()));
        binding.predictabilityValue.setText(getString(R.string.predictability_val,currentDayWhetherData.getPredictability())+"%");
        binding.windDirectionValue.setText(getString(R.string.wind_direction_val,currentDayWhetherData.getWindDirection()));
        binding.windSpeedValue.setText(getString(R.string.wind_speed_val,currentDayWhetherData.getWindSpeed()));

        return view;
    }
}