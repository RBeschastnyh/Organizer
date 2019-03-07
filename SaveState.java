package sample;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveState implements Serializable {

    private static volatile SaveState uniqueInstance;

    private List<CheckBox> list_of_duties = new ArrayList<>();

    private SaveState(){

    }

    public synchronized static SaveState getUniqueInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new SaveState();
        }
        return uniqueInstance;
    }

    public void addCheckBox(CheckBox ch){
        list_of_duties.add(ch);
    }

    public void setCheckList(List<CheckBox> ls){
        list_of_duties = ls;
    }

    public List<CheckBox> getCheckList(){
        return list_of_duties;
    }



}
