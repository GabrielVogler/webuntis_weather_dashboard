package com.voglic.backend;

import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static com.voglic.backend.Time.getTimeInt;

public class Weather {
    static Dotenv dotenv = Dotenv.load();
    private static String key = dotenv.get("KEY");
    private static final String ICONS_TEMPLATE_PATH = "src/main/resources/icons/";
    private static String weather = "";
    private static double tempDouble = 0;
    private static int temp = (int) tempDouble;

    /**
     * interacts with the OpenWeatherAPI and gives Back the Current Weather
     * Information
     * 
     * @param city city where the weather is from
     * @return Weather
     */
    private static void update(String city) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray dataObject = (JSONArray) parser.parse(String.valueOf("[" + informationString + "]"));
                JSONObject countryData = (JSONObject) dataObject.get(0);
                JSONObject tempr = (JSONObject) countryData.get("main");
                JSONObject weatherGet = (JSONObject) ((JSONArray) countryData.get("weather")).get(0);
                weather = (weatherGet.get("main")).toString();
                tempDouble = ((double) tempr.get("temp") - 273.15);
                temp = (int) tempDouble;
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getWeather(String city) {
        update(city);
        return weather;
    }

    public static String getIcon(String city) {
        update(city);
        switch (getWeather(city)) {
            case "Clear":
                if (getTimeInt() > 2200 || getTimeInt() < 0500) {
                    return ICONS_TEMPLATE_PATH + "clear_day.png";
                } else {
                    return ICONS_TEMPLATE_PATH + "clear_night.png";
                }

            case "Clouds":
                if (getTimeInt() > 2200 || getTimeInt() < 0500) {
                    return ICONS_TEMPLATE_PATH + "partly_cloudy_day.png";
                } else {
                    return ICONS_TEMPLATE_PATH + "partly_cloudy_night.png";
                }
            case "Rain":
                return ICONS_TEMPLATE_PATH + "rain.png";
            case "Snow":
                return ICONS_TEMPLATE_PATH + "snow.png";
            case "Thunderstorm":
                return ICONS_TEMPLATE_PATH + "thunderstorm.png";
        }
        return weather;
    }

    public static int getTemp(String city) {
        update(city);
        return temp;
    }

    public static String getTempString(String city) {
        return String.valueOf(getTemp(city));
    }
}