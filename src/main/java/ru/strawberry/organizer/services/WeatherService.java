package ru.strawberry.organizer.services;

import ru.strawberry.organizer.helpers.WeatherHelper;

public class WeatherService {

    private WeatherHelper weatherHelper;

    public WeatherService(WeatherHelper weatherHelper) {
        this.weatherHelper = weatherHelper;
    }

    public String getCurrentTemperature(){
        return weatherHelper.getCurrentTemperature();
    }

    public String getCurrentTemperatureAsFeels(){
        return weatherHelper.getCurrentTemperatureAsFeels();
    }

}
