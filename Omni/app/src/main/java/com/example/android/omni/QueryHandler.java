package com.example.android.omni;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.android.omni.ShopsListActivity.LOG_TAG;

/**
 * Created by vamsi on 05-11-2016.
 */

public class QueryHandler {

    private static String storeName;
    private static String storeAddress;
    private static String area;
    private static String time;
    private static boolean men;
    private static boolean women;
    private static boolean kids;


    static double userLat = HomePageActivity.currentLatitude;
    static double userLon = HomePageActivity.currentLongitude;

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

        List<StoreModel> Store = new ArrayList<>();

        if (TextUtils.isEmpty(StoresJSON)) {
            return null;
        }

        try {

//            JSONObject baseJsonResponse = new JSONObject(StoresJSON);
//            JSONArray brands = baseJsonResponse.getJSONArray("brands");
//            JSONObject brandData = brands.getJSONObject(0);
//            String brandName = brandData.getString("brand_name");

            JSONArray storelist = new JSONArray(StoresJSON);

            for (int i = 0; i < storelist.length(); i++) {
                JSONObject storedetails = storelist.getJSONObject(i);

                storeName = storedetails.getString("store_name");
                storeAddress = storedetails.getString("address");
                String openingTime = storedetails.getString("opening_hours");
                String closingTime = storedetails.getString("closing_hours");
                area = storedetails.getString("store_locality");
                men = storedetails.getBoolean("for_men");
                women = storedetails.getBoolean("for_women");
                kids = storedetails.getBoolean("for_kids");
//                time = openingTime + " to " + closingTime;
                double storeLatitude = storedetails.getDouble("latitude");
                double storeLongitude = storedetails.getDouble("longitude");
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

                String locationURL = updateLocURL(userLat, userLon, storeLatitude, storeLongitude);
                double dist = calculateDistance(locationURL);

                StoreModel store = new StoreModel(R.drawable.woodland, storeName, area, openingTime, closingTime, dist, 100, men, women, kids);
                Store.add(store);
                Log.e("QueryUtils", storeName + area + time + dist + 100 + men + women + kids + " " + currentDateTimeString);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the JSON results", e);
        }

        return Store;
    }


    public static double calculateDistance(String locationURL) {

        URL lurl = createUrl(locationURL);

        String ljsonResponse = null;
        try {
            ljsonResponse = makeHttpRequest(lurl);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the location HTTP request.", e);
        }

        return getDistanceFromJson(ljsonResponse);
    }

    static String updateLocURL(double userLat, double userLon, double storeLat, double storeLon) {

        String lURL = "http://router.project-osrm.org/route/v1/driving/" +
                userLon +
                "," +
                userLat +
                ";" +
                storeLon +
                "," +
                storeLat +
                "?overview=false";

        Log.d(LOG_TAG, "locurl updated" + lURL);
        return lURL;


    }

    private static double getDistanceFromJson(String locJson) {
        double distance = 0;

        if (TextUtils.isEmpty(locJson)) {
            return 0;
        }

        try {

            JSONObject baseJsonResponse = new JSONObject(locJson);
            JSONArray routes = baseJsonResponse.getJSONArray("routes");
            JSONObject bestroute = routes.getJSONObject(0);
            distance = bestroute.getDouble("distance");

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem getting distance", e);
        }
        return distance;

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
