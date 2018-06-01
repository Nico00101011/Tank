package fr.secherre.nicolas.blindes;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;

import fr.secherre.nicolas.blindes.Entity.EntityType;
import fr.secherre.nicolas.blindes.Entity.Player;
import fr.secherre.nicolas.blindes.Entity.Shape;
import fr.secherre.nicolas.blindes.Util.Angle;
import fr.secherre.nicolas.blindes.Util.Timer;

public class Game {

    private static final byte GAME_PAUSE = 0;
    private static final byte GAME_CONTINUE = 1;
    private static final byte GAME_STOP = 2;

    private static Game INSTANCE;

    private Timer timer;
    private byte STATE;
    private PaintEngine pe;

    /***********************************************
     *  CONSTRUCTOR
     ************************************************/

    private Game(Context c, DisplayMetrics dm){
        try {
            Angle.Init();
            Shape.setShapes(100);
            pe = PaintEngine.getInstance(c, dm);

            Player.Create(EntityType.ENTITY_TANK, 0, 500, 2, dm, 90 * Angle.MULTIPLIER);

            STATE = GAME_PAUSE;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public boolean NextFrame(){
        return timer.Next();
    }

    /***********************************************
     *  SETTERS
     ************************************************/

    public void start(){
        STATE = GAME_CONTINUE;
    }

    public void pause(){
        STATE = GAME_PAUSE;
    }

    public void stop(){
        STATE = GAME_STOP;
    }

    /***********************************************
     *  GETTERS
     ************************************************/

    public static Game getInstance(Context c, DisplayMetrics dm){
        if(INSTANCE==null){
            INSTANCE = new Game(c, dm);
        }
        return INSTANCE;
    }

    public static Game getInstance(){
        return INSTANCE;
    }

    public PaintEngine getPaintEngine(){
        return pe;
    }

}
