package com.example.whetherz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.PreferenceManager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ConnectivityManager cm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cm=(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        performNetworkConnectionCheck();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
            Intent i= new Intent(this,SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void performNetworkConnectionCheck(){
        if(!isNetworkConnected()){
            Dialog noInternetDialog=formErrorDialog();
            noInternetDialog.setContentView(R.layout.no_internet_dialog);
            noInternetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            noInternetDialog.show();
            noInternetDialog.findViewById(R.id.retry).setOnClickListener(v -> {
                if(isNetworkConnected()) {
                    noInternetDialog.dismiss();
                    displayCityInfoOnNetworkEstablish();
                }
            });
        }
    }

    private void performCitySelectionCheck(){
        if(!isCitySelected()){
            Dialog noCitySelectedDialog=formErrorDialog();
            noCitySelectedDialog.setContentView(R.layout.no_city_selected);
            noCitySelectedDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            noCitySelectedDialog.show();
            noCitySelectedDialog.findViewById(R.id.retry).setOnClickListener(v -> {
                if(isCitySelected())
                    noCitySelectedDialog.dismiss();
            });
        }
    }

    private Dialog formErrorDialog(){
        Dialog d = new Dialog(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            d.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(this,R.drawable.rect_round));
        }
        d.setCancelable(false);
        return d;
    }

    private boolean isNetworkConnected(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network nw = cm.getActiveNetwork();
            if (nw == null) return false;
            NetworkCapabilities actNw = cm.getNetworkCapabilities(nw);
            return actNw != null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
        } else {
            NetworkInfo nwInfo = cm.getActiveNetworkInfo();
            return nwInfo != null && nwInfo.isConnected();
        }
    }

    private boolean isCitySelected(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String cityName = sharedPreferences.getString("current_location",getString(R.string.no_location_selected));
        return !cityName.equals(getString(R.string.no_location_selected));
    }

    private void displayCityInfoOnNetworkEstablish(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String cityName = sharedPreferences.getString("current_location",getString(R.string.no_location_selected));
        if(!cityName.equals(getString(R.string.no_location_selected)))
            WhetherDataRepository.reGetCityInfo(cityName);
        else
            performCitySelectionCheck();
    }
}