package fr.secherre.nicolas.blindes.Entity;

import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;

import fr.secherre.nicolas.blindes.Util.Angle;

public abstract class Entity {

    protected ArrayList<Entity> anchors;
    protected PointF        location;
    protected Shape         shape;
    protected float         angle;
    protected int           health;

    protected Entity(EntityType type, int meta, int health, int nbAnchors, PointF location, int angle){
        if(nbAnchors>0){
            this.anchors    = new ArrayList<>(nbAnchors);
            this.anchors.add(new Engine(0, 200, 8, 0.7f, location, angle));
            for(int id = 1; id < nbAnchors; id++){
                this.anchors.add(new Turret(0, 200,
                        0.7f, 500,
                        this, new PointF(0, 2), angle));
            }
        }
        this.health     = health;
        this.location   = location;
        this.angle      = angle;
        this.shape      = Shape.getShape(type, meta);
        type.addEntity(this);
    }

    public Path getPath(Path path){
        return shape.toPath(path, location, Angle.getAngle((int)angle));
    }

    public static void MoveAll(){
        for(EntityType et : EntityType.values()){
            for(Entity e : et.getEntities()){
                e.Move();
            }
        }
    }

    abstract void Move();
}
