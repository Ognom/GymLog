package com.ognom.gymlog.database;

import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ognom.gymlog.model.Exercise;

public class ReadExercise {

	List<Exercise> exercises;
	
	protected ReadExercise()
	{
		
	}
	
	//Returns a cursor over the exercises that fits the charsequence.
	public Cursor getExercisesCursor(SQLiteDatabase aDatabase, CharSequence s){
		String constraint = s.toString();
		String sql = "SELECT id as _id, Name, Description, Category FROM " + DbHelper.TABLE_1 + " WHERE (Name LIKE '%" + constraint + "%') ORDER BY " + DbHelper.C_NAME;
		Log.d("Försöker köra kommando", sql);
		Cursor cursor = aDatabase.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursor;
	}
	
	//Returns a cursor over all exercises, sorted by name and pointing at the first entry.
	public Cursor getExercisesCursor(SQLiteDatabase aDatabase){
		String sql = "SELECT id as _id, Name, Description, Category FROM " + DbHelper.TABLE_1 + " ORDER BY " + DbHelper.C_NAME;
		Log.d("Försöker köra kommando", sql);
		Cursor cursor = aDatabase.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursor;
	}
	
	//Returns a List of all the Exercises, sorted alphabetically.
	public List<Exercise> getAllExercises(SQLiteDatabase aDatabase) 
	{
		String sql = "SELECT * FROM " + DbHelper.TABLE_1 + " ORDER BY " + DbHelper.C_NAME;
		System.out.println("About to execute sql-command: " + sql);
		
		try{
			Cursor cursor = aDatabase.rawQuery(sql, new String []{});
			cursor.moveToFirst();
			while(cursor.moveToNext()){
				Exercise e = new Exercise(cursor.getString(1), cursor.getString(2),  cursor.getString(3)); 
				exercises.add(e); //Add the new exercise object		
				}
			return exercises;
		}
		catch(Exception e){
			
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
