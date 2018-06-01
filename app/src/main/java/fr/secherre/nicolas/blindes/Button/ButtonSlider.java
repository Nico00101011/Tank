package fr.secherre.nicolas.blindes.Button;

import android.graphics.Rect;

public class ButtonSlider extends Button {

    private static final int MAX_VALUE = 1;

    private boolean vertical;
    private float   value;
    private float   valOffset;
    private Rect    cursor;
    private int     weight;

    /***********************************************
     *  CONSTRUCTOR
     *  start and offset must be setted between 0 and 1
     ************************************************/

    public ButtonSlider(ButtonType type, int x, int y, int w, int h, int we, float start, float offset) {
        super(type);
        cursor = new Rect();
        valOffset = offset;
        setRectBounds(x, y, w, h);
        weight = we / 2;
        if(h > w){
            vertical = true;
            int pos = y + (int)((1 - start) * h);
            value = start - offset;
            cursor.set(x,pos - weight,x + w,pos + weight);
        }else{
            vertical = false;
            int pos = x + (int)((1 - start) * w);
            value = start - offset;
            cursor.set(pos - weight ,y ,pos + weight,y + h);
        }
    }

    /***********************************************
     *  SETTERS
     ************************************************/

    @Override
    public boolean Action(float x, float y){
        if(rectF.contains(x, y) && cursor.contains((int)x, (int)y)){
            if(vertical){
                value = ( rect.bottom - y) / rect.height() - valOffset;
                cursor.top = (int)y - weight;
                cursor.bottom = (int)y + weight;
            }else{
                value = MAX_VALUE - (x + rect.width() - rect.right) / rect.width();
                cursor.left = (int)x - weight;
                cursor.right = (int)x + weight;
            }

            if(value < 0.05 && value > -0.05){
                value = 0;
            }
            return true;
        }else{
            return false;
        }
    }

    /***********************************************
     *  GETTERS
     ************************************************/

    public boolean isVertical(){
        return vertical;
    }
    public float getValue(){
        return value;
    }
    public Rect getCursor(){
        return cursor;
    }

}
