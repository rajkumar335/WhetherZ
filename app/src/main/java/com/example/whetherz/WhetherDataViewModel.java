package com.example.whetherz;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;

import com.example.whetherz.Networking.ReturnObtainedData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WhetherDataViewModel extends AndroidViewModel implements ReturnObtainedData {


    private WhetherDataRepository whetherDataRepository;
    private final Context mContext;

    private MutableLiveData<ArrayList<WhetherData>> whetherDataArrayList =null;

    public MutableLiveData<ArrayList<WhetherData>> getWhetherdata(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String cityName = sharedPreferences.getString("current_location", mContext.getString(R.string.no_location_selected));
        if(whetherDataArrayList==null){
            Log.e("Captain7","initiateGetCityInfo called!");
            initiateGetCityInfo(cityName);
        }
        return whetherDataArrayList;
    }
    public WhetherDataViewModel(@NonNull  Application application) {
        super(application);
        whetherDataRepository = new WhetherDataRepository(application,this);
        mContext=application;
    }


    private void initiateGetCityInfo(String cityName){
        if(whetherDataArrayList==null){
            whetherDataArrayList= new MutableLiveData<ArrayList<WhetherData>>();
        }
        whetherDataRepository.initiateGetCityInfo(cityName);
    }
    private void initiateWhetherInfo(String cityID){
        whetherDataRepository.initiateGetWhetherInfo(cityID);
    }
    @Override
    public void processObtainedCityData(JSONArray data) {
        //Toast.makeText(mContext.getApplicationContext(),"SUCCESS! "+data.toString(),Toast.LENGTH_SHORT).show();
        try {
            JSONObject jsonObject = data.getJSONObject(0);
            String cityID = jsonObject.getString("woeid");
            initiateWhetherInfo(cityID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processObtainedWhetherData(JSONObject whetherData) {
        try {
            JSONArray consolidatedWhetherList = whetherData.getJSONArray("consolidated_weather");
            ArrayList<WhetherData> mWhetherData= new ArrayList<>();
//            if(!whetherDataArrayList.getValue().isEmpty()){
//                whetherDataArrayList.getValue().clear();
//            }
            for(int i=1;i<=6;++i){
                JSONObject daydata= consolidatedWhetherList.getJSONObject(i-1);
                WhetherData w= new WhetherData();
                w.setId(daydata.getLong("id"));
                w.setWhetherStateName(daydata.getString("weather_state_name"));
                w.setWhetherStateAbbr(daydata.getString("weather_state_abbr"));
                w.setWindDirectionCompass(daydata.getString("wind_direction_compass"));
                w.setCreated(daydata.getString("created"));
                w.setApplicableDate(daydata.getString("applicable_date"));
                w.setMaxTemp(daydata.getInt("max_temp"));
                w.setMinTemp(daydata.getInt( "min_temp"));
                w.setWindSpeed(daydata.getDouble("wind_speed"));
                w.setWindDirection(daydata.getDouble("wind_direction"));
                w.setAirPressure(daydata.getDouble("air_pressure"));
                w.setHumidity(daydata.getInt("humidity"));
                w.setVisibility(daydata.getDouble("visibility"));
                w.setPredictability(daydata.getInt("predictability"));

                mWhetherData.add(w);
            }
            whetherDataArrayList.setValue(mWhetherData);
            Toast.makeText(mContext.getApplicationContext(),"WOHOOOOO! SUCCESS!",Toast.LENGTH_SHORT).show();
            Log.e("CAPTION7:","WOHOOOOO! SUCCESS!");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleFailed() {
        Toast.makeText(mContext.getApplicationContext(),"OOPS! error occured!",Toast.LENGTH_SHORT).show();
        Log.e("CAPTION7:","OOPS! error occured!");
    }


}
