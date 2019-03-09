package sample;

import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaveState implements Serializable {

    private List<CheckBox> list_of_duties = new ArrayList<>();

    private List<String> list_of_names = new ArrayList<>();

    private List<Boolean> list_of_states = new ArrayList<>();

    public SaveState(){

    }

    public void setNamesAndConditions(){
        for(int i = 0; i < list_of_duties.size(); ++i){
            if(i >=1 && list_of_duties.get(i).getText().equalsIgnoreCase(list_of_duties.get(i-1).getText())){
                continue;
            }
            list_of_names.add(list_of_duties.get(i).getText());
            list_of_states.add(list_of_duties.get(i).isSelected());
        }
    }

    public void setLists(List<String> l, List<Boolean> b){
        for(int i = 0; i < l.size(); ++i){
            CheckBox ch = new CheckBox(l.get(i));
            ch.setSelected(b.get(i));
            list_of_duties.add(ch);
        }
    }


    public List<String> getNames(){
        return list_of_names;
    }

    public List<Boolean> getStates(){
        return list_of_states;
    }

    public void addCheckBox(CheckBox ch){
        list_of_duties.add(ch);
    }

    public List<CheckBox> getCheckList(){
        return list_of_duties;
    }



}
