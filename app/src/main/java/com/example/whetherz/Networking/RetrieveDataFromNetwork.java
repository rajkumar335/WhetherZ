package com.example.whetherz.Networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class RetrieveDataFromNetwork {

    private final VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ReturnObtainedData mReturnedObtainedData;


    //THE BASE URL TEMPLATE TO PERFORM THE QUERY
    private String url="https://www.metaweather.com/api/location/search/?query=";

    private String whetherDataRetrieveURL = "https://www.metaweather.com/api/location/";

    private static RetrieveDataFromNetwork mInstance=null;

    private RetrieveDataFromNetwork(Context context,ReturnObtainedData returnObtainedData){
        volleySingleton = VolleySingleton.getInstance(context);
        requestQueue=volleySingleton.getRequestQueue();
        mReturnedObtainedData=returnObtainedData;
    }

    public static synchronized RetrieveDataFromNetwork getInstance(Context context,ReturnObtainedData returnObtainedData){
        if(mInstance==null){
            mInstance= new RetrieveDataFromNetwork(context,returnObtainedData);
        }
        return mInstance;
    }
    /**
     * The below method performs a JSONArray Request by using the city name
     * According to the MetaWhether API, it returns information specific to the city
     * like cityName, cityId, etc
     *
     * @param name : The name of the city*/
    public void getCityInfo( String name){
        //Completing the url by adding the city name
        String requiredQueryURL = url+name;

        //Performing the json array request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, requiredQueryURL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                mReturnedObtainedData.processObtainedCityData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR:",error.toString());
                mReturnedObtainedData.handleFailed();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    
    public void getWhetherInfo(String id){
        String requiredQueryURL = whetherDataRetrieveURL+id+"/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requiredQueryURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mReturnedObtainedData.processObtainedWhetherData(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY ERROR:",error.toString());
                mReturnedObtainedData.handleFailed();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
