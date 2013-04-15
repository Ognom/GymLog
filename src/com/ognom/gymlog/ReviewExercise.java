package com.ognom.gymlog;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;

import com.ognom.gymlog.database.DatabaseController;

//Shows a list over all exercises which is filtered in the EditText.
public class ReviewExercise extends Activity implements OnItemClickListener, OnClickListener
{
	DatabaseController dbC;
	EditText exerciseSearch;
	ListView filteredExercises;
	Cursor cursor;
	SimpleCursorAdapter adapter;
	Button addExercise, delete;

	float historicX = Float.NaN, historicY = Float.NaN;
	static final int DELTA = 50;
	enum Direction {LEFT, RIGHT;}

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_exercise);
		initialize();
	}

	private void initialize(){
		exerciseSearch = (EditText) findViewById(R.id.eTExerciseSearch);
		delete = (Button) findViewById(R.id.bDelete);

		addExercise = (Button) findViewById(R.id.bAddExercise);
		addExercise.setClickable(true);
		addExercise.setOnClickListener(this);

		filteredExercises = (ListView) findViewById(R.id.lVFilteredExercises);
		filteredExercises.setOnItemClickListener(this);
		filteredExercises.setTextFilterEnabled(true);

		filteredExercises.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) 
			{

				switch (event.getAction()) 
				{
				case MotionEvent.ACTION_DOWN:
					historicX = event.getX();
					historicY = event.getY();
					break;

				case MotionEvent.ACTION_UP:
					if (event.getX() - historicX < -DELTA) 
					{
						delete = (Button) findViewById(R.id.bDelete);
						if(delete.getVisibility() == 0)						
							delete.setVisibility(4); //Invisible
						
						System.out.println("Drar åt vänster");		            	
						return true;
					}
					else if (event.getX() - historicX > DELTA)  
					{
						delete = (Button) findViewById(R.id.bDelete);
						if(delete.getVisibility() == 4)
							delete.setVisibility(0); //Visible
						
						System.out.println("Drar åt höger");
						return true;
					} break;
				default: return false;
				}
				return false;
			}
		});




	}

	//TODO: Low priority, fancy graph method for fancy users.
	private ProgressionGraph getGraph()
	{

		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

		Cursor cursor = (Cursor) adapter.getItemAtPosition(position);
		String exerciseName = cursor.getString(1);

		//TODO: Fetch a more detailed description of the selected exercise. Perhaps a new class and activity?
	}

	@Override
	public void onClick(View arg0) {
		//Only one button linked here, add exercise.
		try{
			Class<?> tempClass = Class.forName("com.ognom.gymlog.NewExercise");
			Intent tempIntent = new Intent(this, tempClass);
			startActivity(tempIntent);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause(){
		System.out.println("Inne i onPause");
		dbC.close();
		super.onPause();
	}

	@Override
	protected void onResume(){
		System.out.println("Inne i onResume");
		super.onResume();
	}

	@Override
	protected void onStart(){
		dbC = DatabaseController.initialize(this);
		cursor = dbC.getExerciseCursor();
		startManagingCursor(cursor); //TODO: Switch to cursorLoader

		String[] from = new String[] {"Name", "Description", "Category"};
		int[] to = new int[] {R.id.row_name, R.id.row_description, R.id.row_category};

		adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to, 0);
		filteredExercises.setAdapter(adapter);
		adapter.setFilterQueryProvider(new FilterQueryProvider() {

			@Override
			public Cursor runQuery(CharSequence s) {
				return dbC.getExerciseCursor(s);
			}
		});

		exerciseSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				Cursor c = adapter.runQueryOnBackgroundThread(s);
				c.moveToFirst();
				adapter.changeCursor(c);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		System.out.println("Inne i onStart");
		super.onStart();
	}

	@Override
	protected void onStop(){
		System.out.println("Inne i onStop");
		super.onStop();
	}


}
