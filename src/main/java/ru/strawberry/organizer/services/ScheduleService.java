package ru.strawberry.organizer.services;

import ru.strawberry.organizer.helpers.GetResponseHelper;
import ru.strawberry.organizer.helpers.TimeHelper;

public class ScheduleService {

    public String getTime(String stationCode){
        GetResponseHelper getResponseHelper = new GetResponseHelper(stationCode);
        TimeHelper timeHelper = new TimeHelper(getResponseHelper);
        return timeHelper.getClosestTimeFromResponse();
    }

}
