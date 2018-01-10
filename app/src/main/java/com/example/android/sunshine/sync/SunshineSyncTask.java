package com.example.android.sunshine.sync;

//  COMPLETED (1) Create a class called SunshineSyncTask

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class SunshineSyncTask {

    private static String TAG = SunshineSyncTask.class.getSimpleName();

    //  COMPLETED (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
    public static synchronized void syncWeather(Context context) {


        //      COMPLETED (3) Within syncWeather, fetch new weather data

        URL weatherUrl = NetworkUtils.getUrl(context);
        if (weatherUrl == null) return;

        String jsonResponse = null;
        try {
            jsonResponse = NetworkUtils.getResponseFromHttpUrl(weatherUrl);
        } catch (IOException e) {
            Log.e(TAG, "Error in JSON parser");
        }

        ContentValues[] weatherContentValues = null;
        try {
            weatherContentValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonResponse);
        } catch (JSONException e) {
            Log.e(TAG, "Error in creating ContentValues[]");
        }

        //      COMPLETED (4) If we have valid results, delete the old data and insert the new
        Uri contentProviderUri = WeatherContract.WeatherEntry.CONTENT_URI;
        if (weatherContentValues != null && weatherContentValues.length != 0) {

            ContentResolver sunshineContentResolver = context.getContentResolver();

            int deletedRows = sunshineContentResolver.delete(contentProviderUri, null, null);
            int insertedRows = sunshineContentResolver.bulkInsert(contentProviderUri, weatherContentValues);

        }

    }


}
