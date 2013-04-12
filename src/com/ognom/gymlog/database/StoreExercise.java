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

		String sql = ("SELECT * FROM " + DbHelper.TABLE + " WHERE " + DbHelper.C_NAME + " = '" + exercise.getName() + "'"); //If there's already an exercise with the same name.
		Cursor c = db.rawQuery(sql, null); //Get cursor over all exercises with the name we're trying to add.
		if(!c.moveToFirst()){ //Move to first entry, if true there's a duplicate meaning we don't wish to enter the if-statement. If false, it's a unique exercise and we insert into the database.

			ContentValues values = new ContentValues();

			values.put(DbHelper.C_NAME, exercise.getName());
			values.put(DbHelper.C_CATEGORY, exercise.getCategory());
			values.put(DbHelper.C_DESCRIPTION, exercise.getDescription());

			try{
				db.insertOrThrow(DbHelper.TABLE, null, values);
				return true;
			}
			catch(SQLException e){
				Log.e(TAG, e.toString());
				return false;
			}
		}
		return false;
	}
}
