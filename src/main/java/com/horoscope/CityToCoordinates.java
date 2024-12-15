package com.horoscope;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CityToCoordinates {
    private static final String GEOCODING_API = "https://api.opencagedata.com/geocode/v1/json";
    private static final String API_KEY = "5a35326fa5a144959b7a985de8427faa";

    public static double[] getCoordinates(String cityName) throws Exception {
        HttpResponse<String> response = Unirest.get(GEOCODING_API)
            .queryString("q", cityName)
            .queryString("key", API_KEY)
            .asString();

        if (response.getStatus() == 200) {
            return parseCoordinates(response.getBody());
        } else {
            throw new Exception("Error fetching coordinates: " + response.getBody());
        }
    }

    private static double[] parseCoordinates(String responseBody) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        JSONObject geometry = (JSONObject) ((JSONObject) ((JSONObject) json.get("results")).get("geometry"));
        double latitude = (double) geometry.get("lat");
        double longitude = (double) geometry.get("lng");
        return new double[]{latitude, longitude};
    }
}
