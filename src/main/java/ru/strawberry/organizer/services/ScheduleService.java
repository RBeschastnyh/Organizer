package ru.strawberry.organizer.services;

import ru.strawberry.organizer.helpers.TimeHelper;

public class ScheduleService {

    private TimeHelper timeHelper;

    public ScheduleService(TimeHelper timeHelper) {
        this.timeHelper = timeHelper;
    }

    public String getTime(String stationCode){
        return timeHelper.getClosestTimeFromResponse(stationCode);
    }

}
