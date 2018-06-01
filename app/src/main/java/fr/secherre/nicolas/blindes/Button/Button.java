package fr.secherre.nicolas.blindes.Button;

import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

public abstract class Button {

    protected static ArrayList<Button> buttons = new ArrayList<Button>();
    protected static boolean  enabled = false;
    protected static Button focused;

    /***********************************************
     *  CONSTRUCTOR
     *  Button disabled by default
     ************************************************/

    protected boolean activated;
    protected Rect    rect;
    protected RectF   rectF;

    protected Button(ButtonType type){
        this.rect = new Rect();
        this.rectF = new RectF();
        this.activated = false;
        buttons.add(this);
        type.addButton(this);
    }

    /***********************************************
     *  DESTRUCTOR
     ************************************************/

    public static void Clear(){
        buttons.clear();
    }

    /***********************************************
     *  EVENTS
     ************************************************/

    abstract boolean Action(float x, float y);
    public abstract float getValue();

    public static void onPress(float x, float y){
        for(Button b : buttons) {
            if (b.activated) {
                if(b.Action(x, y)){ break; }
            }
        }
    }

    /***********************************************
     *  SETTERS
     ************************************************/

    public static void reset(){
        focused = null;
    }

    public static void setAllActive(boolean mod){
        for(Button b : buttons){
            b.activated = mod;
        }
    }

    public static void setEnabled(boolean mod){
        enabled = mod;
    }

    public void setActive(boolean mod){
        activated = mod;
    }

    public void setRectBounds(int x, int y, int w, int h){
        rect.set(x,y,x+w,y+h);
        rectF.set(x,y,x+w,y+h);
    }

    /***********************************************
     *  GETTERS
     ************************************************/


    public static boolean isEnabled(){
        return enabled;
    }
    public boolean isActive(){
        return activated;
    }
    public Rect getRect(){
        return rect;
    }
    public RectF getRectF(){
        return rectF;
    }
    public static ArrayList<Button> getButtons(){
        return buttons;
    }
}
