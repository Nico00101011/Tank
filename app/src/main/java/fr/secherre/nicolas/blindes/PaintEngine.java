package fr.secherre.nicolas.blindes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import fr.secherre.nicolas.blindes.Button.Button;
import fr.secherre.nicolas.blindes.Button.ButtonPress;
import fr.secherre.nicolas.blindes.Button.ButtonRotary;
import fr.secherre.nicolas.blindes.Button.ButtonSlider;
import fr.secherre.nicolas.blindes.Entity.Entity;
import fr.secherre.nicolas.blindes.Entity.EntityType;

public class PaintEngine extends View {

    public DisplayMetrics screen;
    private Canvas c;
    private Paint p;
    private Path entPath;

    private static int frame = 0;

    /***********************************************
     *  CONSTRUCTOR STATIC INSTANCE
     ************************************************/
    private static PaintEngine pe;
    private PaintEngine(Context context) {
        super(context);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        entPath = new Path();
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_MOVE:
                        for (int size = event.getPointerCount(), id = 0; id < size; id++) {
                            GamePad.ExecOnTarget(event.getX(id),event.getY(id));
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    /***********************************************
     *  DESTRUCTOR
     ************************************************/

    public static void Destroy(){
        pe.c = null;
        pe.p = null;
        pe.entPath.reset();
        pe.entPath = null;
        pe = null;
        pe.p.setTextSize(75);
    }

    /***********************************************
     *  GETTERS
     ************************************************/

    public static PaintEngine getInstance(Context c, DisplayMetrics screen) throws IllegalAccessException {
        if(pe==null){
            pe = new PaintEngine(c);
            pe.screen = screen;
        }else{
            throw new IllegalAccessException();
        }
        return pe;
    }
    public static PaintEngine getInstance(){
        return pe;
    }

    /***********************************************
     *  EVENT ONDRAW
     ************************************************/
    @Override
    protected void onDraw(Canvas canvas){
        c = canvas;
        c.drawColor(Color.BLACK);
        p.setColor(Color.WHITE);

        c.drawText(""+frame++,100,100,p);
        DrawEntities();
        DrawButtons();
        Entity.MoveAll();
        invalidate();
    }

    /***********************************************
     *  DRAW FUNCTIONS
     ************************************************/

    public void DrawEntities(){
        entPath.reset();
        for(EntityType et : EntityType.values()){
            for(Entity e : et.getEntities()){
                e.getPath(entPath);
                entPath.close();
            }
        }
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(Color.RED);
        c.drawPath(entPath,p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);
        p.setColor(Color.YELLOW);
        c.drawPath(entPath,p);
        entPath.reset();
    }

    public void DrawButtons(){
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        for(Button b : Button.getButtons()){
            p.setStrokeWidth(1);
            if(b.isActive()){
                if(b instanceof ButtonSlider){
                    p.setColor(Color.DKGRAY);
                    c.drawRect(b.getRect(),p);
                    p.setColor(Color.YELLOW);
                    if(b.getValue() == 0){
                        p.setColor(Color.LTGRAY);
                    }else if(b.getValue() > 0){
                        p.setColor(Color.GREEN);
                    }else{
                        p.setColor(Color.BLUE);
                    }
                    c.drawRect(((ButtonSlider)b).getCursor(),p);
                }else if(b instanceof ButtonRotary){
                    p.setColor(Color.DKGRAY);
                    c.drawOval(b.getRectF(),p);
                    p.setColor(Color.LTGRAY);
                    Point middle = ((ButtonRotary)b).getMiddle();
                    Point offset = ((ButtonRotary)b).getOffset();
                    p.setStrokeWidth(10);
                    c.drawLine(middle.x,middle.y,middle.x+offset.x,middle.y+offset.y,p);
                    c.drawCircle(middle.x, middle.y, 20, p);
                    c.drawCircle(middle.x + offset.x, middle.y + offset.y, 50, p);
                }else if(b instanceof ButtonPress){
                    if(((ButtonPress)b).isPressed()){
                        p.setColor(Color.BLUE);
                        c.drawOval(b.getRectF(),p);
                        p.setColor(Color.RED);
                        c.drawText(""+frame++,b.getRectF().left,b.getRectF().top,p);
                    }else{
                        p.setColor(Color.RED);
                        c.drawOval(b.getRectF(),p);
                        p.setColor(Color.BLUE);
                        c.drawText(""+frame++,b.getRectF().left,b.getRectF().top,p);
                    }
                }
            }
        }
    }

}
