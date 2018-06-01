package fr.secherre.nicolas.blindes.Entity;

import android.graphics.PointF;
import android.util.DisplayMetrics;

import fr.secherre.nicolas.blindes.Button.Button;
import fr.secherre.nicolas.blindes.Button.ButtonType;
import fr.secherre.nicolas.blindes.GamePad;
import fr.secherre.nicolas.blindes.Util.Angle;

public class Player extends Entity {

    private static Player INSTANCE;

    private Player(EntityType type, int meta, int health, int nbAnchors, DisplayMetrics dm, int angle) {
        super(type, meta, health, nbAnchors, new PointF(dm.widthPixels/2, dm.heightPixels/4), angle);
        GamePad.Create(type, dm);
    }

    public static void Create(EntityType type, int meta, int health, int nbAnchors, DisplayMetrics dm, int angle){
        if(INSTANCE == null){
            INSTANCE = new Player(type, meta, health, nbAnchors, dm, angle);
        }
    }

    @Override
    void Move() {
        angle += ((Engine)anchors.get(0)).getSpeedMax(
                ButtonType.BUTTON_MOVE.getButtons().get(0).getValue() -
                        ButtonType.BUTTON_MOVE.getButtons().get(1).getValue());
    }
}
