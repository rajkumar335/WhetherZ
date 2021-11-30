package com.example.whetherz.Networking;

import org.json.JSONArray;
import org.json.JSONObject;

public interface ReturnObtainedData {
    void processObtainedCityData(JSONArray data);
    void processObtainedWhetherData(JSONObject whetherData);
    void handleFailed();
}
