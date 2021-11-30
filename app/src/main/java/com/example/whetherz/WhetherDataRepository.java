package com.example.whetherz;

import android.content.Context;

import com.example.whetherz.Networking.RetrieveDataFromNetwork;
import com.example.whetherz.Networking.ReturnObtainedData;

public class WhetherDataRepository {

    private static RetrieveDataFromNetwork retrieveDataFromNetwork;
    public WhetherDataRepository(Context context, ReturnObtainedData returnObtainedData){
        retrieveDataFromNetwork = RetrieveDataFromNetwork.getInstance(context, returnObtainedData);
    }

    public void initiateGetCityInfo(String Name){
        retrieveDataFromNetwork.getCityInfo(Name);
    }

    public void initiateGetWhetherInfo(String id){
        retrieveDataFromNetwork.getWhetherInfo(id);
    }

    public static void reGetCityInfo(String Name){
        retrieveDataFromNetwork.getCityInfo(Name);
    }
}
