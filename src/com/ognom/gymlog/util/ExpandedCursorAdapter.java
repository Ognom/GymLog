package com.ognom.gymlog.util;

import com.ognom.gymlog.R;
import com.ognom.gymlog.database.DatabaseController;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author Ognom
 * A custom SimpleCursorAdapter expanded to handle the deletion of an item in a listview. Is used together with a custom gesturelistener in the same package.
 */
public class ExpandedCursorAdapter extends SimpleCursorAdapter{

	protected ListView listView;
	protected static final String TAG = "ExpandedCursorAdapter";
	private DatabaseController dbC;

	public ExpandedCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags, ListView listview) {
		super(context, layout, c, from, to, flags);
		dbC = DatabaseController.initialize();
		this.listView = listview;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = View.inflate(context, R.layout.row, null);

		Button delete = (Button) view.findViewById(R.id.bDelete);
		delete.setOnClickListener(mOnButtonClickListener);
		return view;
	}

	//Called when a delete button is clicked. Removes the exercise from the DB and listview.
	private OnClickListener mOnButtonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			final int position = listView.getPositionForView((View) v.getParent());
			Cursor c = (Cursor)listView.getItemAtPosition(position);
			String exerciseName = c.getString(1);
			c = dbC.removeExercise(exerciseName); //Remove the desired exercise and retrieve a new cursor.
			Log.v(TAG, "Delete clicked, removing " + exerciseName + "from the Database");
			changeCursor(c); //Update the listView using the new cursor.
			SwipeForDelete.deletedExercise(); //Remove the delete button after deletion.
		}
	};

}
