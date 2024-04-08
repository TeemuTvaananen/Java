package com.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class provides methods to interact with a weather service API.
 * It allows fetching weather information based on latitude and longitude
 * coordinates.
 * 
 */

public class WeatherService {
    // the default constructor for the weatherService

    public WeatherService() {
    }

    /**
     * Retrieves weather information from the weather service API based on latitude
     * and longitude.
     * 
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return A string containing the temperature information, or "Error" if an
     *         error occurs.
     */
    @SuppressWarnings("deprecation")
    public String getWeatherInfo(Double latitude, Double longitude) {
        try {
            // Construct XML payload containing latitude and longitude

            String xmlPayload = "<coordinates>\n" +
                    "    <latitude>" + latitude + "</latitude>\n" +
                    "    <longitude>" + longitude + "</longitude>\n" +
                    "</coordinates>";
            // URL of the weather service
            String urlString = "http://localhost:4001/weather";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configure connection for sending POST request with XML content
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");
            connection.setDoOutput(true);

            // Send XML payload to weather service
            try (OutputStream os = connection.getOutputStream()) {
                os.write(xmlPayload.getBytes());
                os.flush();
            }
            // Retrieve response from weather service
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Parse and extract temperature information from response
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    return extractTemperature(response.toString());
                }
            } else {
                return "Error"; // Error response from weather service
            }

        } catch (Exception e) {
            return "Error"; // Exception occurred
        }
    }

    /**
     * Extracts the temperature information from the weather information string.
     * 
     * @param weatherInfo The weather information string.
     * @return The temperature string, or "Error" if temperature information is not found.
     */
    public static String extractTemperature(String weatherInfo) {
        // Find the index of the <temperature> tag
        int startIndex = weatherInfo.indexOf("<temperature>");
        if (startIndex == -1) {
            return "Error"; // Temperature tag not found
        }

        // Find the index of the closing </temperature> tag
        int endIndex = weatherInfo.indexOf("</temperature>", startIndex);
        if (endIndex == -1) {
            return "Error"; // Closing temperature tag not found
        }

        // Extract the substring between the <temperature> tags
        String temperatureString = weatherInfo.substring(startIndex + "<temperature>".length(), endIndex);

        return temperatureString;
    }
}
