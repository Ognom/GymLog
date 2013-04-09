package com.ognom.gymlog.database;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.ognom.gymlog.model.Exercise;
import com.ognom.gymlog.model.Workout;

public class ReadWorkout {

	
	protected ReadWorkout()
	{
		
	}
	
	// Or you can return the Cursor and do the retrieving from the DatabaseController 
	public List<Workout> getAllWorkouts(SQLiteDatabase aDatabase) 
	{
		
		return null;
	}
	
	public Exercise getWorkout(Integer aId)
	{
		
		return null;
	}
}
