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

    private GetResponseHelper getResponseHelper;

    public TimeHelper(GetResponseHelper getResponseHelper) {
        this.getResponseHelper = getResponseHelper;
    }

    public String getClosestTimeFromResponse() {
        JsonHandleHelper jsonHandleHelper = new JsonHandleHelper(this.getResponseHelper);
        String s = jsonHandleHelper.getGetResponseHelper().getResponse();
        JSONObject jsonObject = jsonHandleHelper.createJsonObjectFromString(s);
        String time = null;
        if (jsonObject != null) {
            List<String> timeStringList = new ArrayList<String>();
            JSONArray jsonArray = (JSONArray) jsonObject.get("segments");
            for(int i = 0; i < jsonArray.size(); ++i){
                timeStringList.add(((JSONObject)jsonArray.get(i)).get("departure").toString());
            }
            time = getClosestTimeFromList(timeStringList);
        }
        return time;
    }

    private String getClosestTimeFromList(@Nonnull List<String> timeList) {
        String closestTime = null;
        Date date1 = new Date();
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
