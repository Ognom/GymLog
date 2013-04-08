package com.ognom.gymlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class NewExercise extends Activity implements OnClickListener{
	
	Button addExercise;
	EditText exerciseName, description;
	AutoCompleteTextView category;
	
	//TODO: Dummy, replace with actual exercises from the database
	String[] exerciseSuggestions = {""};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_exercise);
		initialize();

	}
	
	private void initialize(){
		addExercise = (Button) findViewById(R.id.bAddExercise);
		exerciseName = (EditText) findViewById(R.id.eTExerciseName);
		description = (EditText) findViewById(R.id.eTDescription);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, exerciseSuggestions);
		category = (AutoCompleteTextView) findViewById(R.id.aCTVCategory);
		category.setAdapter(adapter);
		
		addExercise.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		// TODO: addExercise is the only element able to call this function. Add the exercise in the database with corresponding elements.
		
	}

}
