package ru.strawberry.organizer.helpers;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetResponseHelper {

    public String getResponse(String urlString, String param) {
        SafeURLBuilder safeURLBuilder = new SafeURLBuilder();
        URL url = safeURLBuilder.createURL(urlString, param);
        HttpsURLConnection httpsURLConnection = CreateConnectionHelper.createConnection(url);
        String response = getFullResponseString(httpsURLConnection);
        return response;

    }

    private String getFullResponseString(HttpsURLConnection httpsURLConnection) {
        if(httpsURLConnection != null) {
            return getResponseIfHTTPConnectionCreated(httpsURLConnection);
        }
        return null;
    }

    private String getResponseIfHTTPConnectionCreated(HttpsURLConnection httpsURLConnection) {
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

}
