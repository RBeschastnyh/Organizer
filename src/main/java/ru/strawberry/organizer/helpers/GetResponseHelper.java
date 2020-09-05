package ru.strawberry.organizer.helpers;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetResponseHelper {

    private String stationCode;

    public GetResponseHelper(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getResponse() {
        SafeURLBuilder safeURLBuilder = new SafeURLBuilder();
        URL url = safeURLBuilder.createURL(this.stationCode);
        HttpsURLConnection httpsURLConnection = CreateConnectionHelper.createConnection(url);
        String response = getFullResponseString(httpsURLConnection);
        return response;

    }

    private String getFullResponseString(HttpsURLConnection httpsURLConnection) {
        StringBuffer stringBuffer = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()))){
            String line;
            stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();

    }

}
