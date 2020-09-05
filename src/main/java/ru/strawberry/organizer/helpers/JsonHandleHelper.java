package ru.strawberry.organizer.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandleHelper {

    private GetResponseHelper getResponseHelper;

    public GetResponseHelper getGetResponseHelper() {
        return getResponseHelper;
    }

    public void setGetResponseHelper(GetResponseHelper getResponseHelper) {
        this.getResponseHelper = getResponseHelper;
    }

    public JsonHandleHelper(GetResponseHelper getResponseHelper) {
        this.getResponseHelper = getResponseHelper;
    }

    public JSONObject createJsonObjectFromString (String s) {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject)jsonParser.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
