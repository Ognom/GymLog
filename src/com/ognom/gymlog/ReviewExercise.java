package com.ognom.gymlog;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;

import com.ognom.gymlog.database.DatabaseController;
import com.ognom.gymlog.model.Exercise;

//Shows a list of all the Exercises
public class ReviewExercise extends Activity implements OnKeyListener, OnItemClickListener
{
	DatabaseController dbC;
	EditText exerciseSearch;
	ListView filteredExercises;
	Cursor cursor;
	SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_exercise);
		initialize();
	}

	private void initialize(){
		exerciseSearch = (EditText) findViewById(R.id.eTExerciseSearch);


		filteredExercises = (ListView) findViewById(R.id.lVFilteredExercises);
		filteredExercises.setOnItemClickListener(this);
		filteredExercises.setTextFilterEnabled(true);

		dbC = DatabaseController.initialize(this);
		cursor = dbC.getExerciseCursor();
		//startManagingCursor(cursor); //TODO: Switch to cursorLoader

		String[] from = new String[] {"Name", "Description"};
		int[] to = new int[] {android.R.id.text1, android.R.id.text2};

		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
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
	}


	private List<Exercise> getExercises()
	{

		return null;
	}

	//TODO: Low priority, fancy graph method for fancy users.
	private ProgressionGraph getGraph()
	{

		return null;
	}

	@Override
	public boolean onKey(View v, int arg1, KeyEvent arg2) {

		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
		Cursor cursor = (Cursor) adapter.getItemAtPosition(position);
		String exerciseName = cursor.getString(1);
		
		//TODO: Fetch a more detailed description of the selected exercise. Perhaps a new class and activity?
	}
}
