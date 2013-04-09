package com.ognom.gymlog.database;

import java.util.List;
import java.util.Map;

import android.database.sqlite.SQLiteDatabase;

import com.ognom.gymlog.model.Exercise;

public class ReadExercise {

	
	protected ReadExercise()
	{
		
	}
	
	// Or you can return the Cursor and do the retrieving from the DatabaseController 
	public List<Exercise> getAllExercises(SQLiteDatabase aDatabase) 
	{
		
		return null;
	}
	
	public Exercise getExercise(Integer aId)
	{
		
		return null;
	}
	
	
	//Get the exercise, the dates, weight and reps to show in the graph.
	public Map<String, Object> getExerciseProgression(Integer aId)
	{
		
		return null;
	}
}
