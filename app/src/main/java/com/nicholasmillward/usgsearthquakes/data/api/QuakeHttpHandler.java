package com.nicholasmillward.usgsearthquakes.data.api;

import com.nicholasmillward.usgsearthquakes.data.model.Quake;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 6/22/18.
 */

public class QuakeHttpHandler {

    private static final String QUAKE_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=4.5&limit=100";

    public static List<Quake> fetchQuakeData() {

        String jsonResponse = null;

        try {

            URL url = new URL(QUAKE_URL);
            jsonResponse = requestQuakeData(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseJson(jsonResponse);
    }

    private static String requestQuakeData(URL url) throws IOException {

        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000); // 10 sec
            urlConnection.setConnectTimeout(15000); //15 sec
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = convertStreamToString(inputStream);
            }

        } catch (IOException e) {

            e.printStackTrace();

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

    private static String convertStreamToString(InputStream inputStream) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private static List<Quake> parseJson(String jsonResponse) {

        try {

            JSONObject quakeJsonResponse = new JSONObject(jsonResponse);
            JSONArray quakesData = quakeJsonResponse.getJSONArray("features");

            List<Quake> quakes = new ArrayList<>();

            try {
                for (int i = 0; i < quakesData.length(); i++) {

                    // Get one quake item from array
                    JSONObject item = quakesData.getJSONObject(i);

                    // Get the 'properties' object from item
                    JSONObject properties = item.getJSONObject("properties");

                    // Get attributes of interest from each 'properties' object
                    double mag = properties.getDouble("mag");
                    String location = properties.getString("place");
                    long time = properties.getLong("time");
                    String url = properties.getString("url");

                    // Put extracted attributes in model object
                    Quake quake = new Quake(mag, location, time, url);

                    // Add objects to array
                    quakes.add(quake);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return quakes;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
