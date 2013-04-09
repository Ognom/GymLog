package com.ognom.gymlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ognom.gymlog.database.DatabaseController;
import com.ognom.gymlog.model.Exercise;
import com.ognom.gymlog.model.ExerciseCategory;

public class NewExercise extends Activity implements OnClickListener{

	Button addExercise;
	EditText exerciseName, description;
	TextView  exerciseAddedInfo;
	AutoCompleteTextView category;
	DatabaseController dbC;

	//TODO: Dummy, replace with actual exercises from the database
	String[] exerciseSuggestions = {"test"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_exercise);
		initialize();

	}

	private void initialize(){
		addExercise = (Button) findViewById(R.id.bAddExercise);
		exerciseName = (EditText) findViewById(R.id.eTExerciseName);
		description = (EditText) findViewById(R.id.eTDescription);
		exerciseAddedInfo = (TextView) findViewById(R.id.tVExerciseAdded);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, exerciseSuggestions);
		category = (AutoCompleteTextView) findViewById(R.id.aCTVCategory);
		category.setAdapter(adapter);

		addExercise.setOnClickListener(this);
		dbC = DatabaseController.initialize(this);
	}

	@Override
	public void onClick(View v) {
		Exercise exercise = new Exercise(exerciseName.getText().toString(), new ExerciseCategory(category.getText().toString()), description.getText().toString());
		//TODO: Right now a new exercise category is created regardless if it already exists. Need to implement a check.
		if(dbC.storeExercise(exercise)){
			exerciseAddedInfo.setText(new String(exercise.getName() + " added!"));
			System.out.println("Successfully added exercise");
		}
		else{
			exerciseAddedInfo.setText(new String("Exercise exists!"));
			System.out.println("Failed adding exercise");
		}
	}

}
