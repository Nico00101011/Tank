package fr.secherre.nicolas.blindes.Util;

public class PseudoRandom {
    private double min, delta, start;
    private double s_start;

    /***********************************************
     *  CONSTRUCTOR
     ************************************************/

    public void PseudoRandom(int min, int delta, int start) {
        this.s_start = this.start = start;
        this.min = min;
        this.delta = delta;
    }

    /***********************************************
     *  SETTERS
     ************************************************/

    public void SaveState(){
        s_start = start;
    }
    public void LoadState(){
        start = s_start;
    }

    /***********************************************
     *  GETTERS
     ************************************************/

    public int Step(int max) {
        return Math.abs((int)(Math.sin(start+=delta)*max + min));
    }
}
