package ru.strawberry.organizer.helpers;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class CreateConnectionHelper {

    public static HttpsURLConnection createConnection(URL url){
        HttpsURLConnection httpsURLConnection = null;
        try {
            httpsURLConnection = (HttpsURLConnection)url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setConnectTimeout(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpsURLConnection;
    }

}
