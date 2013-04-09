package com.ognom.gymlog.database;

import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ognom.gymlog.model.Exercise;

public class ReadExercise {

	List<Exercise> exercises;
	
	protected ReadExercise()
	{
		
	}
	
	// Or you can return the Cursor and do the retrieving from the DatabaseController 
	public List<Exercise> getAllExercises(SQLiteDatabase aDatabase) 
	{
		String sql = "SELECT * FROM " + DbHelper.TABLE + "ORDER BY " + DbHelper.DB_NAME;
		System.out.println("About to execute sql-command: " + sql);
		
		try{
			Cursor cursor = aDatabase.rawQuery(sql, new String []{});
			while(cursor.moveToNext()){
				Exercise e = new Exercise(cursor.getString(0), null,  cursor.getString(2)); //TODO: Make sure we fetch a category. Dummy value of null currently in use.
				exercises.add(e); //Add the new exercise object		
				}
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
		return exercises;

		
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
