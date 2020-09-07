package ru.strawberry.organizer.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

    private static final String SCHEDULE_URL = "https://api.rasp.yandex.net/v3.0/search/?apikey=66bda60c-dcb8-48af-bea3-abe765c74129&format=json&lang=ru_RU&date=%s&to=s2000006&limit=200&from=c10743";
    private JSONObject responseObject;

    public String getClosestTimeFromResponse(String stationCode) {
        GetResponseHelper getResponseHelper = new GetResponseHelper();
        if(responseObject == null){
            responseObject = getResponseHelper.getResponse(SCHEDULE_URL);
        }
        if (responseObject != null) {
            JSONArray jsonArray = (JSONArray) responseObject.get("segments");
            for(int i = 0; i < jsonArray.size(); ++i){
                String responseTime = ((JSONObject)jsonArray.get(i)).get("departure").toString();
                String stops = ((JSONObject)jsonArray.get(i)).get("stops").toString();
                if(isClosestTime(responseTime)){
                    if(!"везде".equalsIgnoreCase(stops) && !"sb".equalsIgnoreCase(stationCode)){
                        continue;
                    }
                    return responseTime.substring(11, 16);
                }
            }
        }
        return null;
    }

    private boolean isClosestTime(String dateTime){
        long currentTimeInMilliseconds = System.currentTimeMillis() / 1000L;
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX").parse(dateTime);
            calendar.setTime(date);
            long candidateTimeInMilliseconds = calendar.getTimeInMillis() / 1000L;
            if(candidateTimeInMilliseconds > currentTimeInMilliseconds){
                return true;
            }
        }
        catch (ParseException pex) {
            pex.printStackTrace();
        }
        return false;
    }

}
