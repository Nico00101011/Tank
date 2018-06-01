package fr.secherre.nicolas.blindes;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class LaunchActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics screen = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screen);
        Game g = Game.getInstance(this,screen);
        setContentView(g.getPaintEngine());
    }
}
