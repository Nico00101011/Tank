package fr.secherre.nicolas.blindes.Entity;

import android.graphics.PointF;

public class Turret extends Entity {

    private Entity  anchor;
    private float   turnRate;
    private int     damage;

    protected Turret(int meta, int health, float turnRate, int damage, Entity anchor, PointF location, int angle) {
        super(EntityType.ENTITY_TURRET, meta, health, 0, location, angle);
        this.anchor = anchor;
        this.turnRate = turnRate;
        this.damage = damage;
    }

    @Override
    void Move() {

    }
}
