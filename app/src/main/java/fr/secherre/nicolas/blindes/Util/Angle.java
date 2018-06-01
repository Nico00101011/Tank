package fr.secherre.nicolas.blindes.Util;

import android.graphics.PointF;

public class Angle {

    public static final int NBANGLES    = 360;
    public static final int MULTIPLIER  = 2;
    public static final int ARRAY_MAX   = NBANGLES * MULTIPLIER - 1;

    private static PointF[] angles      = new PointF[NBANGLES * MULTIPLIER];

    private Angle(){

    }

    public static void Init(){
        for(int id = 0; id <= ARRAY_MAX; id++){
            angles[id] = new PointF((float)Math.cos(Math.toRadians(id/MULTIPLIER)),
                    (float)Math.sin(Math.toRadians(id/MULTIPLIER)));
        }
    }

    public static PointF getAngle(int value){
        while(value > ARRAY_MAX){
            value = value - ARRAY_MAX;
        }
        while(value < 0){
            value = value + ARRAY_MAX;
        }
        return angles[value];
    }
}
