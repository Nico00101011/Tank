package fr.secherre.nicolas.blindes.Util;

public class Timer {

    private long previous;
    private long current;
    private int delta;

    public Timer(int delta){
        previous = System.currentTimeMillis();
        this.delta = delta;
    }

    public boolean Next(){
        current = System.currentTimeMillis();
        if(current - previous > delta){
            previous = current;
            return true;
        }else{
            return false;
        }
    }
}
