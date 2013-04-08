package com.ognom.gymlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class NewExercise extends Activity implements OnClickListener{
	
	Button addExercise;
	EditText exerciseName, description;
	AutoCompleteTextView category;
	
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
		category = (AutoCompleteTextView) findViewById(R.id.aCTVCategory);
		
		addExercise.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
