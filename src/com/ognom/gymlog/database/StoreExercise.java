package com.ognom.gymlog.database;

import com.ognom.gymlog.model.Exercise;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
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
		values.put("Row Number", DbHelper.C_ID);
		try{
			db.insertOrThrow(DbHelper.TABLE, null, values);
			return true;
		}
		catch(SQLException e){
			Log.e(TAG, e.toString());
			return false;
		}
	}
}
