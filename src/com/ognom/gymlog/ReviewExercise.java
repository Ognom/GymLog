package com.ognom.gymlog;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ognom.gymlog.model.Exercise;

//Shows a list of all the Exercises
public class ReviewExercise extends Activity implements OnClickListener
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
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
	
	private ProgressionGraph getGraph()
	{
		
		return null;
	}
}
