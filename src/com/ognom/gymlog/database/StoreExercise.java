package com.ognom.gymlog.database;

import com.ognom.gymlog.model.Exercise;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class StoreExercise {

	private static final String TAG = "com.ognom.gymlog.database.StoreExercise.storeExercise()";

	public StoreExercise(){
	
	}
	
	/*
	Method storeExercise opens the database from a writable perspective, and writes an exercises into the database on a new row.
	 */
	public boolean storeExercise(Exercise exercise, SQLiteDatabase db){

			ContentValues values = new ContentValues();

			values.put(DbHelper.C_NAME, exercise.getName());
			values.put(DbHelper.C_CATEGORY, exercise.getCategory().getName());
			values.put(DbHelper.C_DESCRIPTION, exercise.getDescription());
			
			try{
			db.insertWithOnConflict(DbHelper.TABLE, null, values,
					SQLiteDatabase.CONFLICT_REPLACE);
			}
			
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			Log.d(TAG, exercise.getInfo());
			return true;

	}

}
