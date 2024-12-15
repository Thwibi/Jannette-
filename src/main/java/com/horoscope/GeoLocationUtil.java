package com.horoscope;

import java.io.IOException;
import org.json.JSONObject;

public class GeoLocationUtil {

    private static final String API_KEY = "5a35326fa5a144959b7a985de8427faa";

    public static double[] getCoordinates(String city) throws IOException {
        String url = "https://api.opencagedata.com/geocode/v1/json?q=" + city + "&key=" + API_KEY;
        String response = HttpRequestUtil.sendGet(url);
        JSONObject jsonResponse = new JSONObject(response);

        if (jsonResponse.getJSONArray("results").isEmpty()) {
            throw new IllegalArgumentException("City not found.");
        }

        JSONObject location = jsonResponse.getJSONArray("results").getJSONObject(0).getJSONObject("geometry");
        return new double[]{location.getDouble("lat"), location.getDouble("lng")};
    }
}
