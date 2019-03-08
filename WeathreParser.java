package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeathreParser implements Parsable {
    @Override
    public String getInformation(String url) {
        return null;
    }

    @Override
    public List<Elements> getWeather(String url) {
        List<Elements> weather_states = new ArrayList<>();
        try {
            Document weather_document = Jsoup.parse(new URL(url), 30000);
            Elements weather_circumstance = weather_document.select("div[class=content__main]").
                    select("div[class=fact__temp-wrap]").select("div[class=link__feelings fact__feelings]").
                    select("div[class=link__condition day-anchor i-bem]");
            Elements tables = weather_document.select("div[class=content__main]").
                    select("div[class=fact__temp-wrap]").select("dl[class=term term_orient_h fact__feels-like]").
                    select("dd[class=term__value]").select("span[class=temp__value]");
            weather_states.add(weather_circumstance);
            weather_states.add(tables);
        }catch (IOException ioex){
            ioex.printStackTrace();
        }
        return weather_states;
    }
}
