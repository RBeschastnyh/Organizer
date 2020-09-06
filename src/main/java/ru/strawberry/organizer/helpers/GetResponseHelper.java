package ru.strawberry.organizer.helpers;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetResponseHelper {

    public JSONObject getResponse(String urlString, String param) {
        SafeURLBuilder safeURLBuilder = new SafeURLBuilder();
        JsonHandleHelper jsonHandleHelper = new JsonHandleHelper();
        URL url = safeURLBuilder.createURL(urlString, param);
        URLConnection httpsURLConnection = CreateConnectionHelper.createConnection(url);
        String response = getFullResponseString(httpsURLConnection);
        JSONObject jsonObject = jsonHandleHelper.createJsonObjectFromString(response);
        return jsonObject;
    }

    private String getFullResponseString(URLConnection urlConnection) {
        if(urlConnection != null) {
            return getResponseIfConnectionCreated(urlConnection);
        }
        return null;
    }

    private String getResponseIfConnectionCreated(URLConnection urlConnection) {
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
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
