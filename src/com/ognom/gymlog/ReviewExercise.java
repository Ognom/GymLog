package com.ognom.gymlog;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
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
import com.ognom.gymlog.util.ExpandedCursorAdapter;
import com.ognom.gymlog.util.SwipeForDelete;

//Shows a list over all exercises which is filtered in the EditText.
public class ReviewExercise extends Activity implements OnItemClickListener, OnClickListener
{

	//Instantiate variables.
	private DatabaseController dbC;
	private EditText exerciseSearch;
	private ListView filteredExercises;
	private Cursor cursor;
	private SimpleCursorAdapter adapter;
	private Button addExercise;
	private GestureDetector gd;
	private static boolean isWorkout; //Determines if the activity is called by Review Exercise or New Workout.


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		isWorkout = getIntent().getExtras().getBoolean("Workout"); //
		setContentView(R.layout.general_listview);
		initialize();
	}

	private void initialize(){

		//Begin declaring variables.
		exerciseSearch = (EditText) findViewById(R.id.eTExerciseSearch);

		addExercise = (Button) findViewById(R.id.bAddExercise);
		addExercise.setClickable(true);
		addExercise.setOnClickListener(this);

		filteredExercises = (ListView) findViewById(R.id.lVFilteredExercises);
		filteredExercises.setFocusableInTouchMode(true);
		filteredExercises.setOnItemClickListener(this);
		filteredExercises.setTextFilterEnabled(true);

		//Add a custom GestureDetector located in the util package. Used when handling flings (swipes).
		if(!isWorkout){ //Not necessary to add gestureDetector if parent activity is New Workout.
		gd = new GestureDetector(this, new SwipeForDelete(filteredExercises));

			filteredExercises.setOnTouchListener(new OnTouchListener() {

				//Override onTouch to make sure all known gestures are handled by the custom GestureDetector.
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return gd.onTouchEvent(event);
				}
			});
		}
	}

	//TODO: Low priority. Fancy graph method for fancy users.
	private ProgressionGraph getGraph()
	{

		return null;
	}


	//Called when an item in the listView is clicked.
	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
		if(isWorkout){ //If New workout is the parent activity.
			System.out.println("Clicked on an exercise from new workout");
		}
		else{ //If Review Exercise is the parent activity.
			Cursor cursor = (Cursor) adapter.getItemAtPosition(position);
			String exerciseName = cursor.getString(1);
			System.out.println(exerciseName);
			//TODO: Fetch a more detailed description of the selected exercise. Perhaps a new class and activity?
		}
	}


	//Handles all the onClicks. Currently only one element is clickable, thus no logic.
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
		int[] to = new int[] {R.id.row_2, R.id.row_2, R.id.row_3};

		adapter = new ExpandedCursorAdapter(this, R.layout.row, cursor, from, to, 0, filteredExercises, true);
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
