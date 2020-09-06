package ru.strawberry.organizer.services;

import ru.strawberry.organizer.helpers.TimeHelper;

public class ScheduleService {

    public String getTime(String stationCode){
        TimeHelper timeHelper = new TimeHelper();
        return timeHelper.getClosestTimeFromResponse(stationCode);
    }

}
