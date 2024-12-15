package com.horoscope;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class AstronomyAPI {
    private static final String API_ENDPOINT = "https://api.astronomyapi.com/api/v2/studio/moon-phase";
    private static final String API_AUTH = "Basic ODBlNDEyM2MtZTllMC00ZjJhLWJhOGMtMGE5ZDJjMzgwNzU5OjJmODgwNzU4MzRkNDU5NjRmOWY3ZGU3NDcyM2ZiOTI5YzYyMmJmYjgxNzc3NzM1YmViM2ZiYzY2NDk1MTFjZWY2ZjBkYzhmNTA5NmYwMmJmYTJlMGViZWJkYzVmYjdkN2M5MTUwOGZlZGU2NGQ4NTJhMDM2MTk5OWQ2MWVhZGNmMjIyMWVlMGEyMWE2YTM4MGU1YTJmZDAzMDVmYTdhYTZhNmI5ZDJlZjVkNTgyMWU2YTE5OGU2YzRlMzc3MGY2ZWFhMzVlMTVkNzliYWVkZTgzYzkxYTMyNmIxMzNjMjFj";

    public static String fetchMoonPhaseImage(double latitude, double longitude, String date) throws Exception {
        String requestBody = String.format(
            "{ \"style\": { \"moonStyle\": \"default\", \"backgroundStyle\": \"stars\", \"backgroundColor\": \"#000000\", \"headingColor\": \"#ffffff\", \"textColor\": \"#ffffff\" }, " +
            "\"observer\": { \"latitude\": %f, \"longitude\": %f, \"date\": \"%s\" }, \"view\": { \"type\": \"portrait-simple\", \"parameters\": {} } }",
            latitude, longitude, date
        );

        HttpResponse<String> response = Unirest.post(API_ENDPOINT)
            .header("Authorization", API_AUTH)
            .header("Content-Type", "application/json")
            .body(requestBody)
            .asString();

        if (response.getStatus() == 200) {
            return parseImageUrl(response.getBody());
        } else {
            throw new Exception("API Error: " + response.getStatus() + " - " + response.getBody());
        }
    }

    private static String parseImageUrl(String responseBody) throws Exception {
        int startIndex = responseBody.indexOf("\"imageUrl\":\"") + 12;
        int endIndex = responseBody.indexOf("\"", startIndex);
        if (startIndex >= 12 && endIndex > startIndex) {
            return responseBody.substring(startIndex, endIndex);
        } else {
            throw new Exception("Failed to parse image URL from API response.");
        }
    }
}
