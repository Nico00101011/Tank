package fr.secherre.nicolas.blindes;

import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import fr.secherre.nicolas.blindes.Button.Button;
import fr.secherre.nicolas.blindes.Button.ButtonPress;
import fr.secherre.nicolas.blindes.Button.ButtonRotary;
import fr.secherre.nicolas.blindes.Button.ButtonSlider;
import fr.secherre.nicolas.blindes.Button.ButtonType;
import fr.secherre.nicolas.blindes.Entity.EntityType;

public class GamePad {

    private static final int PADDING = 20;

    private static GamePad INSTANCE;

    private ArrayList<Button> dirButton;
    private Rect area;

    private GamePad(EntityType type, DisplayMetrics screen){
        dirButton = new ArrayList<>();
        area = new Rect(0, screen.heightPixels - screen.widthPixels / 2, screen.widthPixels, screen.heightPixels);
        switch(type){
            case ENTITY_CAR:
                break;
            case ENTITY_PLANE:
                break;
            case ENTITY_SHIP:
                break;
            case ENTITY_TANK:
                dirButton.add(new ButtonSlider(ButtonType.BUTTON_MOVE,area.width() * 4 / 5 - PADDING,
                        area.top - PADDING,
                        area.width() / 5,
                        area.width() / 2,
                        area.width() / 10,0.5f, 0.5f));

                dirButton.add(new ButtonSlider(ButtonType.BUTTON_MOVE, PADDING,
                        area.top - PADDING,
                        area.width() / 5,
                        area.width() / 2,
                        area.width() / 10,0.5f, 0.5f));

                int rayon = area.width() / 8;

                dirButton.add(new ButtonPress(ButtonType.BUTTON_ROTATE,0, "Reload",
                        area.width() / 3,
                        area.bottom - (int)(rayon * 1.4) - PADDING,
                        (int)(rayon * 1.4)));

                dirButton.add(new ButtonPress(ButtonType.BUTTON_PRESS,0, "MG",
                        area.width() / 2,
                        area.bottom - rayon - PADDING,
                        rayon));

                dirButton.add(new ButtonPress(ButtonType.BUTTON_PRESS,0, "Fire",
                        area.width() * 2 / 3,
                        area.bottom - (int)(rayon * 1.4) - PADDING,
                        (int)(rayon * 1.4)));

                for(Button b : dirButton){
                    b.setActive(true);
                }
                break;
            default:
                return;
        }
    }

    public static void Create(EntityType type, DisplayMetrics screen){
        if(INSTANCE == null){
            INSTANCE = new GamePad(type, screen);
        }
    }

    public static boolean ExecOnTarget(float x, float y){
        if(INSTANCE.area.contains((int)x, (int)y)){
            Button.onPress(x, y);
            return true;
        }else{
            return false;
        }
    }
}
