package com.ognom.gymlog.util;

import com.ognom.gymlog.R;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class SwipeForDelete extends SimpleOnGestureListener{
	
    private static final float SWIPE_MIN_DISTANCE = 50; //50 pixels.
	private static final float SWIPE_THRESHOLD_VELOCITY =50; //Minimum of 50 pixels / second.
	private ListView list;

    public SwipeForDelete(ListView list) {
        this.list = list;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    	System.out.println("Swipe detected in SwipeForDelete");
        if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
        		&& Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
            if (showDeleteButton(e1))
                return true;
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    private boolean showDeleteButton(MotionEvent e1) {
        int pos = list.pointToPosition((int)e1.getX(), (int)e1.getY());
        return showDeleteButton(pos);
    }

    private boolean showDeleteButton(int pos) {
        View child = list.getChildAt(pos);
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
    
    //Overriding to allow onFling to execute.
    @Override
    public boolean onDown(MotionEvent e) {
    	System.out.println("onDown detected in SwipeForDelete");
        return true;        
    }
}