package sample;

import org.jsoup.select.Elements;

import java.util.List;

public interface Parsable {

    String getInformation(String url);
    List<Elements> getWeather(String url);

}
