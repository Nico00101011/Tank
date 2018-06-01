package fr.secherre.nicolas.blindes.Button;

import java.util.ArrayList;

public enum ButtonType {

    BUTTON_MENU,
    BUTTON_PAUSE,

    BUTTON_MOVE,
    BUTTON_PRESS,
    BUTTON_ROTATE,
    BUTTON_SWITCH,
    BUTTON_FIRE;

    ArrayList<Button> buttons;

    ButtonType(){
        buttons = new ArrayList();
    }

    public void addButton(Button b){
        buttons.add(b);
    }
    public ArrayList<Button> getButtons(){
        return buttons;
    }
}
