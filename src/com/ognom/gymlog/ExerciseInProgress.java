package com.ognom.gymlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ExerciseInProgress extends Activity implements OnClickListener{
	
	Button log;
	EditText repetitions, weight;
	TextView exerciseName;
	ListView loggedExercises;
	Spinner measurement;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exercise_in_progress);
		initialize(this.getIntent().getStringExtra("Exercise")); //Passes the exercise name to initialize.
		//TODO: Make sure that the intent used to start this activity passes the exercise name.
	}
	
	private void initialize(String Name){
		log = (Button) findViewById(R.id.bLog);
		log.setOnClickListener(this);
		
		repetitions = (EditText) findViewById(R.id.eTRepetitions);
		weight = (EditText) findViewById(R.id.eTWeight);
		
		exerciseName = (TextView) findViewById(R.id.tVCurrentExercise);
		exerciseName.setText(Name);
		
		measurement = (Spinner) findViewById(R.id.spMeasurement);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.measurements, android.R.layout.simple_spinner_dropdown_item);
		measurement.setAdapter(adapter);
		
		loggedExercises = (ListView) findViewById(R.id.lVLoggedSets);
		//TODO: Link this listview with a cursor from the database.
				
	}

	//Called then the log button is clicked. Logs a set of an exercise with corresponding reps and weights.
	@Override
	public void onClick(View v) {
		
		String measurementToAdd = measurement.getSelectedItem().toString(); //kg, lbs, level or none.
		Float weightToAdd = Float.valueOf(weight.getText().toString());	 //Weight used for this set.
		
		
	}
		
	
}
