package com.ognom.gymlog.database;

import com.ognom.gymlog.model.Exercise;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DatabaseController{
	
	
	private static DatabaseController dc = null;
	private DbHelper dbHelp;
	private SQLiteDatabase db;
	private StoreExercise se;
	
	
	//Creates instances of all the other classes in this packet.
	private DatabaseController(Context context){
		this.dbHelp = new DbHelper(context);
		this.se = new StoreExercise();
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




}

