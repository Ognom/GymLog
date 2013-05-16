package com.ognom.gymlog;

import android.os.Bundle;
import android.app.Activity;

public class ExerciseInProgress extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_in_progress);
		initialize(this.getIntent().getStringExtra("Exercise")); //Passes the exercise name to initialize.
		//TODO: Make sure that the intent used to start this activity passes the exercise name.
	}
	
	private void initialize(String exerciseName);

	
}
