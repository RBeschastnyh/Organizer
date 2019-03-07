package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller implements Initializable {

    private final String weather_url = "https://yandex.ru/pogoda/moscow?from=serp_title";
    private final String fili_out_suburban_trains_url = "https://bit.ly/2Hg6Tuz";
    private final String begovaya_out_suburban_trains_url = "https://rasp.yandex.ru/suburban/begovaya--odincovo/today";
    private final String belorusskaya_out_suburban_trains_url = "https://rasp.yandex.ru/suburban/moscow-belorusskaya--odincovo/today";
    private final String kuntsevo_out_suburban_trins_url = "https://rasp.yandex.ru/suburban/kuntsevo-station--odincovo/today";
    private final String fili_and_begovaya_in_subruban_trains_url = "https://rasp.yandex.ru/suburban/odincovo--fili/today";
    private final String kuntsevo_in_suburban_trains_url = "https://rasp.yandex.ru/suburban/odincovo--kuntsevo-station/today";
    private final String belorusskaya_in_suburban_trains_url = "https://rasp.yandex.ru/suburban/odincovo--moscow-belorusskaya/today";

    @FXML
    private Label kuntsevo_time_in;

    @FXML
    private Label fili_time_in;

    @FXML
    private Label begovaya_time_in;

    @FXML
    private Label belorusskaya_time_in;

    @FXML
    private Label kuntsevo_time_out;

    @FXML
    private Label fili_time_out;

    @FXML
    private Label begovaya_time_out;

    @FXML
    private Label belorusskaya_time_out;

    @FXML
    private Label curriculum_label;

    @FXML
    private Label current_temperature_label;

    @FXML
    private Label weather_circumstance_label;

    @FXML
    private Button add_duty_button;

    @FXML
    private VBox duties_box;

    @FXML
    private TextField duty_setText_textLabel;

    private List<CheckBox> list_of_duties = new ArrayList<>();

    private SaveState saved = SaveState.getUniqueInstance();

    @FXML
    void check_inner_state(MouseEvent event) {
        if(duty_setText_textLabel.getText().equals("")){
            add_duty_button.setDisable(true);
        } else{
            add_duty_button.setDisable(false);
        }
    }

    @FXML
    void add_duty(ActionEvent event) {

        CheckBox button = new CheckBox(duty_setText_textLabel.getText());
        list_of_duties.add(button);
        saved.addCheckBox(button);
        duties_box.getChildren().add(button);
        duty_setText_textLabel.clear();
        for(int i = 0; i < list_of_duties.size(); ++i){
            if(list_of_duties.get(i).isSelected()){
                list_of_duties.get(i).setAllowIndeterminate(false);
            }
        }
        add_duty_button.setDisable(true);
    }

    public String getInformation(String string){
        String time = null;
        try {
            Document out_trains = Jsoup.parse(new URL(string), 30000);
            Elements out_train_time = out_trains.select("div[id=root]").select("div[class=Root Root_layout_desktop Root_pageType_search]").
                    select("main[class=Root__main]").select("div[class=SearchPage__content]").select("section[class=SearchSegments]").
                    select("div[class=SearchSegment__timeAndStations]").select("span[class=SearchSegment__time]");
            time = out_train_time.first().text();

        }catch (IOException ioex){
            fili_time_out.setText("Ошибка");
            begovaya_time_out.setText("Ошибка");
            belorusskaya_time_out.setText("Ошибка");
            kuntsevo_time_out.setText("Ошибка");
        }
        return time;
    }

    public List<Elements> getWeatherFromYandex(String url){
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add_duty_button.setDisable(true);

        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    weather_circumstance_label.setText(getWeatherFromYandex(weather_url).get(0).text());
                    current_temperature_label.setText(getWeatherFromYandex(weather_url).get(1).text() + "°C");
                });
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    begovaya_time_out.setText(getInformation(begovaya_out_suburban_trains_url));
                    fili_time_out.setText(getInformation(fili_out_suburban_trains_url));
                    kuntsevo_time_out.setText(getInformation(kuntsevo_out_suburban_trins_url));
                    belorusskaya_time_out.setText(getInformation(belorusskaya_out_suburban_trains_url));
                });
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    kuntsevo_time_in.setText(getInformation(kuntsevo_in_suburban_trains_url));
                    fili_time_in.setText(getInformation(fili_and_begovaya_in_subruban_trains_url));
                    begovaya_time_in.setText(getInformation(fili_and_begovaya_in_subruban_trains_url));
                    belorusskaya_time_in.setText(getInformation(belorusskaya_in_suburban_trains_url));
                });
            }
        }.start();
        /*new Thread(){
            @Override
            public void run(){
                Calendar gc = new GregorianCalendar();
                long difference = (-gc.get(Calendar.DAY_OF_WEEK) + 2) * 86400000;
                long to_end = 86400000*5;
                String begin_of_the_week = new SimpleDateFormat("yyyy.MM.dd").format(new Date(System.currentTimeMillis() + difference));
                String end_of_the_week = new SimpleDateFormat("yyyy.MM.dd").format(new Date(System.currentTimeMillis() + difference + to_end));
                final String curriculum_url = "https://www.hse.ru/ba/cosec/timetable?fromdate=" + begin_of_the_week + "&todate=" + end_of_the_week + "&groupoid=9203&receiverType=3&timetable-courses=2&timetable-groups=9203&timetable-view-switcher=list";
                try {
                    Document curriculum_tables = Jsoup.parse(new URL(curriculum_url), 30000);
                    Elements curriculum = curriculum_tables.select("div[class=page]").select("div[class=layout wide]").
                            select("div[class=grid grid2]").select("div[class=wide tt-timetable]").
                            select("div[class=content tt-timetable__content is-block]").select("div[class=tt tt-list]")//.
                            //select("div[class=tt-list__item]")
                            ;
                    System.out.println(curriculum.outerHtml());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();*/
    }
}
