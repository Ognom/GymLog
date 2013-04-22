package com.ognom.gymlog.util;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ognom.gymlog.R;


/**
 * A custom SimpleOnGestureListener expanded to handle swipes on a list view. Displays or removes a delete button depending on previous visibility.
 * @author Ognom
 *
 */
public class SwipeForDelete extends SimpleOnGestureListener{

	private static final float SWIPE_MIN_DISTANCE = 50; //50 pixels.
	private static final float SWIPE_THRESHOLD_VELOCITY =50; //Minimum of 50 pixels / second.
	private static final float SWIPE_MAX_Y_DISTANCE = 30; //Maximum of 30 pixels in the Y-axis.
	private ListView list; //List view to remove/display
	private static boolean isShown = false; //Reflects if a delete button is shown or not.
	private static Button delete; //The button currently being shown. Only one button at a time can be shown.

	public SwipeForDelete(ListView list) {
		this.list = list;
	}

	//Overrides onFling().
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		System.out.println("Inside onFling");
		if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
				&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY && Math.abs(e2.getY() - e1.getY()) < SWIPE_MAX_Y_DISTANCE )
			System.out.println("Inside onFling, conditions OK");
		if (showDeleteButton(e1))
			return true;
		return super.onFling(e1, e2, velocityX, velocityY);
	}

	//Calculates the position of the swipe and returns showDeleteButton(int pos).
	private boolean showDeleteButton(MotionEvent e1) {
		if(isShown){ //If a button already is shown, a fling event will remove the visibility and allow other buttons to be shown.
			delete.setVisibility(View.INVISIBLE);
			isShown = false;
			return true;
		}
		int pos = list.pointToPosition((int)e1.getX(), (int)e1.getY());
		System.out.println("Position: " + pos);
		return showDeleteButton(pos);
	}

	
	//Called by ExpandedCursorAdapter when an exercise is deleted.
	protected static void deletedExercise(){
		isShown = false;
		delete.setVisibility(View.INVISIBLE);
	}
	
	//Show/remove the delete button depending on previous visibility.
	private boolean showDeleteButton(int pos) {
		View child = list.getChildAt(pos - list.getFirstVisiblePosition());
		if (child != null){
			delete = (Button) child.findViewById(R.id.bDelete);
			delete.setVisibility(View.VISIBLE);
			isShown = true;
			return true;
		}
		return false;
	}

	//Overriding to allow onFling to execute due to onDown() being called before onFling() is executed.
	@Override
	public boolean onDown(MotionEvent e) {
		return true;        
	}

}