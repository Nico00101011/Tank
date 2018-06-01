package fr.secherre.nicolas.blindes.Button;

public class ButtonPress extends Button {

    private boolean isPressed;
    private int     id;
    private String  label;

    public ButtonPress(ButtonType type, int id, String label, int x, int y, int r){
        super(type);
        this.isPressed  = false;
        this.id         = id;
        this.label      = label;
        setRectBounds(x- r/2, y, r, r);
    }

    public void setPressed(boolean b){
        isPressed = b;
    }

    public boolean isPressed(){
        return isPressed;
    }

    @Override
    boolean Action(float x, float y) {

        return false;
    }

    @Override
    public float getValue() {
        return 0;
    }
}
