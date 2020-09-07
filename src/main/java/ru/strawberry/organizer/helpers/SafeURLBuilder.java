package ru.strawberry.organizer.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SafeURLBuilder {

    public URL createURL(String urlTemplate){
        String fullPath = String.format(urlTemplate, getDateInString());
        try {
            return new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getDateInString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
