package com.ognom.gymlog.database;

import java.util.Date;
import java.util.List;


import com.ognom.gymlog.model.Exercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseController{
	
	
	private static DatabaseController dc = null;
	private DbHelper dbHelp;
	private SQLiteDatabase db;
	private StoreExercise se;
	private ReadExercise re;
	private ReadWorkout rw;
	
	
	//Creates instances of all the other classes in this packet.
	private DatabaseController(Context context){
		this.dbHelp = new DbHelper(context);
		this.se = new StoreExercise();
		this.re = new ReadExercise();
		this.rw = new ReadWorkout();
	}
	
	//Starts a new instance of DatabaseController if no earlier instance exists.
	public static DatabaseController initialize(Context context){
		if(dc == null){
			dc = new DatabaseController(context);
		}
		return dc;
	}
	
	public static DatabaseController initialize(){
		if(dc == null){
			return null;
		}
		return dc;
	}
	
	//Stores an exercise in the database.
	public boolean storeExercise(Exercise exercise){
		db = dbHelp.getWritableDatabase();
		boolean store = se.storeExercise(exercise, db);
		db.close();
		return store;
	}
	
	//Fetches all exercises stored in the database.
	//TODO: Some form of check to make sure a correct list is returned.
	
	public List<Exercise> readExercises(){
		db = dbHelp.getReadableDatabase();
		List<Exercise> exercises = re.getAllExercises(db);
		db.close();
		return exercises;
	}
	
	public Cursor getExerciseCursor(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = re.getExercisesCursor(db);
		db.close();
		return cursor;
	}
	
	public Cursor getExerciseCursor(CharSequence s){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = re.getExercisesCursor(db, s);
		db.close();
		return cursor;
	}
	
	public boolean addWorkouts(){ //Dummy method used for testing
		db = dbHelp.getReadableDatabase();
		dbHelp.testWorkoutsStart(db);
		db.close();
		return true;
		
	}
	
	public Cursor getWorkoutCursor(CharSequence s){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rw.getWorkoutCursor(db, s);
		db.close();
		return cursor;
	}
	
	public Cursor getWorkoutCursor(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rw.getWorkoutCursor(db);
		db.close();
		return cursor;
	}
	
	//Closes the database handled by this dbC.
	public void close(){
		dbHelp.close();
	}
	
	//Opens the database handled by this dBC.
	public void open(){
		dbHelp.getReadableDatabase();		
	}
	
	public Cursor removeExercise(String exercise){
		db = dbHelp.getWritableDatabase();
		db.delete(DbHelper.TABLE, DbHelper.C_NAME + "=?", new String[] {exercise}); //Remove row where Name = exercise.
		db.close();
		return getExerciseCursor();
	}
	
	public Cursor removeWorkout(String date){
		db = dbHelp.getWritableDatabase();
		db.delete(DbHelper.TABLE2, DbHelper.C_DATE + "=?", new String[] {date}); //Remove row where Name = exercise.
		db.close();
		return getWorkoutCursor();
	}

}

