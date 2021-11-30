package com.example.whetherz;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        EditTextPreference location = findPreference("current_location");
        BindPreferenceSummary(location);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String summary= newValue.toString();
        preference.setSummary(summary);
        if(preference.getKey().equals("current_location")){
            WhetherDataRepository.reGetCityInfo(newValue.toString());
        }
        return true;
    }

    public void BindPreferenceSummary(Preference preference){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preference.setOnPreferenceChangeListener(this);
        String summary=sharedPreferences.getString(preference.getKey(),"");
        onPreferenceChange(preference,summary);
    }
}