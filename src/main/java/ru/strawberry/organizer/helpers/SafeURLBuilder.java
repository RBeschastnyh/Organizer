package ru.strawberry.organizer.helpers;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeURLBuilder {

    public URL createURL(String urlTemplate, String stationName){
        String fullPath;
        if(stationName != null){
            fullPath = String.format(urlTemplate, getDateInString(), getStationCode(stationName));
        }else {
            fullPath = urlTemplate;
        }
        try {
            return new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public URL createURL(String urlTemplate){
//        String fullPath = String.format(urlTemplate);
//        try {
//            return new URL(fullPath);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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
            case "setun" : {
                stationCode = "s9600941";
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
