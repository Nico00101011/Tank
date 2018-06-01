package fr.secherre.nicolas.blindes.Entity;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

import java.util.ArrayList;

public class Shape {

    private static ArrayList<Shape>[]  shapes = new ArrayList[8];

    private ArrayList<Point> p;

    public Shape(EntityType et, int size){
        p = new ArrayList<>(size);
        if(shapes[et.getId()] == null){
            shapes[et.getId()] = new ArrayList<>();
        }
        shapes[et.getId()].add(this);
    }

    private void addPoint(int multiplier, float x, float y){
        p.add(new Point((int)(x*multiplier), (int)(y*multiplier)));
    }

    public Path toPath(Path path, PointF location, PointF rotation){
        Point point = p.get(0);
        path.moveTo(point.x*rotation.x + point.y*rotation.y + location.x,
                point.x*rotation.y - point.y*rotation.x + location.y);
        for(int id = 1, size = p.size(); id < size; id++){
            point = p.get(id);
            path.lineTo(point.x*rotation.x + point.y*rotation.y + location.x,
                    point.x*rotation.y - point.y*rotation.x + location.y);
        }
        return path;
    }

    public static Shape getShape(EntityType type, int meta){
        return shapes[type.getId()].get(meta);
    }

    public static void setShapes(int multiplier){
        Shape s;
        s = new Shape(EntityType.ENTITY_TANK, 4);
        s.addPoint(multiplier, -1, -1.5f);
        s.addPoint(multiplier, -1, 1.5f);
        s.addPoint(multiplier, 1, 1.5f);
        s.addPoint(multiplier, 1, -1.5f);

        multiplier = (int) (multiplier / 1.5f);
        s = new Shape(EntityType.ENTITY_TURRET, 6);
        s.addPoint(multiplier, -0.75f, -1);
        s.addPoint(multiplier, -1.25f, 0);
        s.addPoint(multiplier, -0.75f, 1.25f);
        s.addPoint(multiplier, 0.75f, 1.25f);
        s.addPoint(multiplier, 1.25f, 0);
        s.addPoint(multiplier, 0.75f, -1);

        s = new Shape(EntityType.ENTITY_ENGINE, 6);
        s.addPoint(multiplier, -0.75f, 1.25f);
        s.addPoint(multiplier, 0.75f, 1.25f);
    }
}
