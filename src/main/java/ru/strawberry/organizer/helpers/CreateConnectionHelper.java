package ru.strawberry.organizer.helpers;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CreateConnectionHelper {

    public static URLConnection createConnection(URL url){
        if(url.toString().contains("https://")){
            return createHTTPSConnection(url);
        }else if(url.toString().contains("http://")){
            return createHTTPConnection(url);
        }
        return null;
    }

    private static URLConnection createHTTPSConnection(URL url){
        try {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(5000);
            return httpsURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static URLConnection createHTTPConnection(URL url){
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(5000);
            return httpURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
