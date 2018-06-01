package fr.secherre.nicolas.blindes.Entity;

import android.graphics.PointF;

public class Engine extends Entity{

    private int speedMax;
    private float power;

    protected Engine(int meta, int health, int speedMax, float power, PointF location, int angle) {
        super(EntityType.ENTITY_ENGINE, meta, health, 0, location, angle);
        this.speedMax = speedMax;
        this.power = power;
    }

    public float getSpeedMax(float value){
        return value * speedMax;
    }

    @Override
    void Move() {

    }
}
