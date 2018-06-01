package fr.secherre.nicolas.blindes.Entity;

import java.util.ArrayList;

public enum EntityType {

    //Primary entities
    ENTITY_TANK,
    ENTITY_CAR,
    ENTITY_PLANE,
    ENTITY_SHIP,

    //Secondary entities
    ENTITY_TURRET,
    ENTITY_ENGINE,
    ENTITY_MISSILE;

    private static int s_id = 0;

    private int id;
    private ArrayList<Entity> entities;

    EntityType(){
        id = upId();
        entities = new ArrayList<>();
    }

    public void addEntity(Entity et){
        entities.add(et);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    private static int upId(){
        return s_id++;
    }
    public int getId(){
        return id;
    }
}
