package com.ognom.gymlog.util;

import com.ognom.gymlog.R;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


//A custom class that handles swipes on a list view. Displays or removes a delete button depending on previous visibility.
public class SwipeForDelete extends SimpleOnGestureListener{
	
    private static final float SWIPE_MIN_DISTANCE = 50; //50 pixels.
	private static final float SWIPE_THRESHOLD_VELOCITY =50; //Minimum of 50 pixels / second.
	private ListView list; //List view to remove/display button.

    public SwipeForDelete(ListView list) {
        this.list = list;
    }
    
    //Overrides onFling().
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
        		&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
            if (showDeleteButton(e1))
                return true;
        return super.onFling(e1, e2, velocityX, velocityY);
    }
    
    //Calculates the position of the swipe and returns showDeleteButton(int pos).
    private boolean showDeleteButton(MotionEvent e1) {
        int pos = list.pointToPosition((int)e1.getX(), (int)e1.getY());
        return showDeleteButton(pos);
    }
    
    //Show/remove the delete button depending on previous visibility.
    private boolean showDeleteButton(int pos) {
        View child = list.getChildAt(pos - list.getFirstVisiblePosition());
        if (child != null){
            Button delete = (Button) child.findViewById(R.id.bDelete);
            if (delete != null)
                if (delete.getVisibility() == View.INVISIBLE)
                    delete.setVisibility(View.VISIBLE);
                else
                    delete.setVisibility(View.INVISIBLE);
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