package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class TrainParser implements Parsable {
    @Override
    public String getInformation(String url) {
        String time = null;
        try {
            Document out_trains = Jsoup.parse(new URL(url), 30000);
            Elements out_train_time = out_trains.select("div[id=root]").select("div[class=Root Root_layout_desktop Root_pageType_search]").
                    select("main[class=Root__main]").select("div[class=SearchPage__content]").select("section[class=SearchSegments]").
                    select("div[class=SearchSegment__timeAndStations]").select("span[class=SearchSegment__time]");
            time = out_train_time.first().text();

        }catch (IOException ioex){
            ioex.printStackTrace();
        }
        return time;
    }

    @Override
    public List<Elements> getWeather(String url) {
        return null;
    }
}
