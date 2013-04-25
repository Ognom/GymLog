package com.ognom.gymlog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenu extends Activity implements OnClickListener{

	Button newWorkout, reviewWorkout, newExercise, reviewExercise;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		//Set to full screen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//Set the layout.
		setContentView(R.layout.main_menu);

		//Initialize buttons and add onClickListeners.
		initialize();

	}

	private void initialize(){

		//Initialize buttons.
		newWorkout = (Button) findViewById(R.id.bNewWorkout);
		reviewWorkout = (Button) findViewById(R.id.bReviewWorkout);
		newExercise = (Button) findViewById(R.id.bNewExercise);
		reviewExercise = (Button) findViewById(R.id.bReviewExercise);

		//Add onClickListeners.
		newWorkout.setOnClickListener(this);
		reviewWorkout.setOnClickListener(this);
		newExercise.setOnClickListener(this);
		reviewExercise.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bNewExercise:
			try{
				Class<?> tempClass = Class.forName("com.ognom.gymlog.NewExercise");
				Intent tempIntent = new Intent(this, tempClass);
				startActivity(tempIntent);
				break;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		case R.id.bReviewExercise:
			try{
				Class<?> tempClass = Class.forName("com.ognom.gymlog.ReviewExercise");
				Intent tempIntent = new Intent(this, tempClass);
				tempIntent.putExtra("Workout", false);
				startActivity(tempIntent);
				break;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		case R.id.bNewWorkout:
			try{
				Class<?> tempClass = Class.forName("com.ognom.gymlog.ReviewExercise");
				Intent tempIntent = new Intent(this, tempClass);
				tempIntent.putExtra("Workout", true);
				startActivity(tempIntent);
				break;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		case R.id.bReviewWorkout:
			try{
				Class<?> tempClass = Class.forName("com.ognom.gymlog.ReviewWorkout");
				Intent tempIntent = new Intent(this, tempClass);
				startActivity(tempIntent);
				break;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
