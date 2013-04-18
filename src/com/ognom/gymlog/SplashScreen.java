package com.ognom.gymlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {
	
    private long ms = 0;
    private long splashTime = 2000;
    private boolean splashActive = true;
    private boolean paused = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hides the title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);

        Thread mythread = new Thread() {
            public void run() {
                try {
                    while (splashActive && ms < splashTime) {
                        if(!paused)
                            ms=ms+100;
                        sleep(100);
                    }
                } 
                catch(Exception e) {}
                finally {
                    Intent intent = new Intent(SplashScreen.this, MainMenu.class);
                    startActivity(intent);
                }
            }
        };
        mythread.start();
    }
}