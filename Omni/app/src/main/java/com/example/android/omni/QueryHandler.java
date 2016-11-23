package com.example.android.omni;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.omni.ShopsListActivity.LOG_TAG;

/**
 * Created by vamsi on 05-11-2016.
 */

public class QueryHandler {

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", " application/json");
//            urlConnection.setRequestProperty("user-key", " "+API_KEY);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<StoreModel> extractFeatureFromJson(String StoresJSON) {

        if (TextUtils.isEmpty(StoresJSON)) {
            return null;
        }

        List<StoreModel> Store = new ArrayList<>();

        try {

            JSONObject baseJsonResponse = new JSONObject(StoresJSON);
//            JSONArray brands = baseJsonResponse.getJSONArray("brands");
//            JSONObject brandData = brands.getJSONObject(0);
//            String brandName = brandData.getString("brand_name");
            String storeName = baseJsonResponse.getString("store_name");
            String storeAddress = baseJsonResponse.getString("address");
            String openingTime = baseJsonResponse.getString("opening_hours");
            String closingTime = baseJsonResponse.getString("closing_hours");
            String area = baseJsonResponse.getString("store_locality");
            boolean men = baseJsonResponse.getBoolean("for_men");
            boolean women = baseJsonResponse.getBoolean("for_women");
            boolean kids = baseJsonResponse.getBoolean("for_kids");
            String time = openingTime + " to " + closingTime;
            StoreModel store = new StoreModel(R.drawable.woodland, storeName, area, time, 1.25, 100, men, women, kids);
            Store.add(store);
            Log.e("QueryUtils", storeAddress + men + women + kids);

        } catch (
                JSONException e
                )

        {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        return Store;
    }

    public static List<StoreModel> fetchStoreData(String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractFeatureFromJson(jsonResponse);
    }


}
