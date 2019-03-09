package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Organizer.fxml"));
        primaryStage.setTitle("Organaizer");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("OrganizerStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            Terminal t = Controller.getTerminal();
            t.setSaveState(Controller.getState());
            SaveState s = t.getSaveState();
            s.setNamesAndConditions();
            List<String> names = s.getNames();
            List<Boolean> states = s.getStates();
            try{
                FileOutputStream fos = new FileOutputStream("SavedState.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(names);
                oos.writeObject(states);
                oos.close();
            } catch(IOException sex){
                sex.printStackTrace();
            }
            System.out.println(names);
            System.out.println(states);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
