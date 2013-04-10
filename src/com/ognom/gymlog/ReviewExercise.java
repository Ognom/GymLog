package com.ognom.gymlog;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ognom.gymlog.database.DatabaseController;
import com.ognom.gymlog.model.Exercise;

//Shows a list of all the Exercises
public class ReviewExercise extends Activity implements OnClickListener
{
	DatabaseController dbC;
	EditText exerciseSearch;
	ListView filteredExercises;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_exercise);
		initialize();
	}
	
	private void initialize(){
		exerciseSearch = (EditText) findViewById(R.id.eTExerciseSearch);
		filteredExercises = (ListView) findViewById(R.id.lVfilteredExercises);
		
		dbC = DatabaseController.initialize(this);
		cursor = dbC.getExerciseCursor();
		//startManagingCursor(cursor); //TODO: Switch to cursorLoader
		
		String[] from = new String[] {"gymlog.db.Name", "_id"};
		int[] to = new int[] {android.R.id.text1};
		
		CursorAdapter adapter = new CursorAdapter(this,
				android.R.layout.simple_list_item_1,
				cursor, from, to); 
		
		
		
		
		
		
		
		filteredExercises.setAdapter(adapter);
	}
	
	
	private List<Exercise> getExercises()
	{
		
		return null;
	}

	@Override
	public void onClick(View aView) 
	{
		switch(aView.getId())
		{
		
		//TODO add R.id.name from R-file
		case 1 :  
			//TODO Do something useful
			break;
		
		}
		
	}
	
	//TODO: Low priority, fancy graph method for fancy users.
	private ProgressionGraph getGraph()
	{
		
		return null;
	}
}
