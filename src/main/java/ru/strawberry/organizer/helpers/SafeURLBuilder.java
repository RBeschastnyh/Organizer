package ru.strawberry.organizer.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeURLBuilder {

    private static final String SCHEDULE_URL = "https://api.rasp.yandex.net/v3.0/search/?apikey=66bda60c-dcb8-48af-bea3-abe765c74129&format=json&lang=ru_RU&date=%s&to=%s&limit=200&from=c10743";

    public URL createURL(String stationName){
        URL url = null;
        String fullPath = String.format(SCHEDULE_URL, getDateInString(), getStationCode(stationName));
        try {
            url = new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /*TODO: заменить на БД*/
    private String getStationCode(String stationName) {
        String stationCode;
        switch (stationName) {
            case "bel" : {
                stationCode = "s2000006";
                break;
            }
            case "beg" : {
                stationCode = "s9601666";
                break;
            }
            case "test" : {
                stationCode = "s9601349";
                break;
            }
            case "fili" : {
                stationCode = "s9600821";
                break;
            }
            case "sb" : {
                stationCode = "s9876336";
                break;
            }
            case "kunts" : {
                stationCode = "s9601728";
                break;
            }
            default: {
                stationCode = "";
            }
        }
        return stationCode;
    }

    private String getDateInString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
