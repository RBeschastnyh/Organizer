package ru.strawberry.organizer.helpers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.annotation.Nonnull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeHelper {

    private static final String SCHEDULE_URL = "https://api.rasp.yandex.net/v3.0/search/?apikey=66bda60c-dcb8-48af-bea3-abe765c74129&format=json&lang=ru_RU&date=%s&to=%s&limit=200&from=c10743";

    public String getClosestTimeFromResponse(String stationCode) {
        GetResponseHelper getResponseHelper = new GetResponseHelper();
        JSONObject jsonObject = getResponseHelper.getResponse(SCHEDULE_URL, stationCode);
        List<String> timeStringList = new ArrayList<String>();
        if (jsonObject != null) {
            JSONArray jsonArray = (JSONArray) jsonObject.get("segments");
            for(int i = 0; i < jsonArray.size(); ++i){
                timeStringList.add(((JSONObject)jsonArray.get(i)).get("departure").toString());
            }
        }
        return getClosestTimeFromList(timeStringList);
    }

    private String getClosestTimeFromList(@Nonnull List<String> timeList) {
        String closestTime = "";
        long currentTimeInMilliseconds = System.currentTimeMillis() / 1000L;
        try {
            Calendar calendar = Calendar.getInstance();
            for (String s : timeList) {
                Date date = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssX").parse(s);
                calendar.setTime(date);
                long candidateTimeInMilliseconds = calendar.getTimeInMillis() / 1000L;
                if(candidateTimeInMilliseconds > currentTimeInMilliseconds){
                    closestTime = s;
                    break;
                }
            }
        }
        catch (ParseException pex) {
            pex.printStackTrace();
        }
        return closestTime.substring(11, 16);
    }

}
