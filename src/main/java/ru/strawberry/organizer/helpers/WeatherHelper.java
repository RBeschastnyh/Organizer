package ru.strawberry.organizer.helpers;

import org.json.simple.JSONObject;

public class WeatherHelper {

    private final String WEATHER_URL = "http://api.weatherstack.com/current?access_key=b200d7833ece2f0b3c7000cb09d98296&query=Moscow";
    private JSONObject responseObject;

    public String getCurrentTemperature() {
        GetResponseHelper getResponseHelper = new GetResponseHelper();
        if(responseObject == null){
            responseObject = getResponseHelper.getResponse(WEATHER_URL);
        }
        return ((JSONObject)responseObject.get("current")).get("temperature").toString();
    }

    public String getCurrentTemperatureAsFeels() {
        GetResponseHelper getResponseHelper = new GetResponseHelper();
        if(responseObject == null){
            responseObject = getResponseHelper.getResponse(WEATHER_URL);
        }
        return ((JSONObject)responseObject.get("current")).get("feelslike").toString();
    }
}
