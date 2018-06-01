package fr.secherre.nicolas.blindes.Button;

import android.graphics.Point;

public class ButtonRotary extends Button{

    private Point offset;
    private Point middle;
    private int length;

    public ButtonRotary(ButtonType type, int x, int y, int r, double rad) {
        super(type);
        length = r/2;
        offset = new Point((int)(Math.cos(rad)*length), (int)(Math.sin(rad)*length));
        middle = new Point(x,y+length);
        setRectBounds(x-length, y, r, r);
    }

    public Point getOffset(){
        return offset;
    }

    public Point getMiddle(){
        return middle;
    }

    @Override
    boolean Action(float x, float y) {
        x = (x<0?-x:x) - middle.x;
        y = (y<0?-y:y) - middle.y;
        double d = Math.sqrt(x*x + y*y);
        if(d <= length + 50){
            d = length / d;
            offset.x = (int)(x * d);
            offset.y = (int)(y * d);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public float getValue() {
        return 0;
    }
}
