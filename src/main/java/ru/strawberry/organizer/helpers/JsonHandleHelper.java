package ru.strawberry.organizer.helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandleHelper {

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
