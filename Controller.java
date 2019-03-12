package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
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

    @FXML
    private Button clear_all_button;

    private List<CheckBox> list_of_duties = new ArrayList<>();

    private static Terminal terminal = new Terminal();

    private static SaveState saveState = new SaveState();

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
        saveState.addCheckBox(button);
        duties_box.getChildren().add(button);
        duty_setText_textLabel.clear();
        for(CheckBox cb : list_of_duties){
            if(cb.isSelected()){
                cb.setAllowIndeterminate(false);
            }
        }

        add_duty_button.setDisable(true);
    }

    @FXML
    void clearDuties(ActionEvent event) {
        if(!list_of_duties.isEmpty()){
            duties_box.getChildren().clear();
            list_of_duties.clear();
        }
    }

    public static Terminal getTerminal(){
        return terminal;
    }

    public static SaveState getState(){
        return saveState;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        add_duty_button.setDisable(true);
        Parsable train_parser = new TrainParser();
        Parsable weather_parser = new WeathreParser();
        List<String> duties = null;
        List<Boolean> states = null;
        try {
            try {
                FileInputStream fis = new FileInputStream("SavedState.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                duties = (List<String>) ois.readObject();
                states = (List<Boolean>) ois.readObject();
                saveState.setLists(duties, states);
                Platform.runLater(() -> {
                    duties_box.getChildren().addAll(saveState.getCheckList());
                });
                list_of_duties = saveState.getCheckList();
                ois.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }catch (ClassNotFoundException cex) {
            cex.printStackTrace();
        }


        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()-> {
                    weather_circumstance_label.setText(weather_parser.getWeather(weather_url).get(0).text());
                    current_temperature_label.setText(weather_parser.getWeather(weather_url).get(1).text() + "°C");
                });
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    begovaya_time_out.setText(train_parser.getInformation(begovaya_out_suburban_trains_url));
                    fili_time_out.setText(train_parser.getInformation(fili_out_suburban_trains_url));
                    kuntsevo_time_out.setText(train_parser.getInformation(kuntsevo_out_suburban_trins_url));
                    belorusskaya_time_out.setText(train_parser.getInformation(belorusskaya_out_suburban_trains_url));
                });
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Platform.runLater(()->{
                    kuntsevo_time_in.setText(train_parser.getInformation(kuntsevo_in_suburban_trains_url));
                    fili_time_in.setText(train_parser.getInformation(fili_and_begovaya_in_subruban_trains_url));
                    begovaya_time_in.setText(train_parser.getInformation(fili_and_begovaya_in_subruban_trains_url));
                    belorusskaya_time_in.setText(train_parser.getInformation(belorusskaya_in_suburban_trains_url));
                });
            }
        }.start();
    }
}
