package com.ognom.gymlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	
    private boolean mIsBackButtonPressed; //Used to know if the back button was pressed in the splash screen activity and avoid opening the next activity
    private static final int SPLASH_DURATION = 5000; //5 seconds


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        Handler handler = new Handler();

        //Run a thread after 2 seconds to start the home screen
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                //Make sure we close the splash screen so the user won't come back when it presses back key

                finish();
                
                if (!mIsBackButtonPressed) {
                    //Start the home screen if the back button wasn't pressed already 
                    Intent intent = new Intent(SplashScreen.this, MainMenu.class);
                    SplashScreen.this.startActivity(intent);
               }
                
            }

        }, SPLASH_DURATION); // time in milliseconds (1 second = 1000 milliseconds) until the run() method will be called

    }

    @Override
   public void onBackPressed() {

        //Set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}
