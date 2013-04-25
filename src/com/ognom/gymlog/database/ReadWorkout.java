package com.ognom.gymlog.database;

import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ognom.gymlog.model.Exercise;
import com.ognom.gymlog.model.Workout;

public class ReadWorkout {

	
	protected ReadWorkout()
	{
		
	}
	
	public Cursor getWorkoutCursor(SQLiteDatabase aDatabase, CharSequence s){
		String constraint = s.toString();
		String sql = "SELECT _id, Date FROM " + DbHelper.TABLE2 + " WHERE (Date LIKE '%" + constraint + "%') ORDER BY " + DbHelper.C_DATE;
		Log.d("Försöker köra kommando", sql);
		Cursor cursor = aDatabase.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursor;
	}
	
	//Returns a cursor over all exercises, sorted by name and pointing at the first entry.
	public Cursor getWorkoutCursor(SQLiteDatabase aDatabase){
		String sql = "SELECT _id, Date FROM " + DbHelper.TABLE2 + " ORDER BY " + DbHelper.C_DATE;
		Log.d("Försöker köra kommando", sql);
		Cursor cursor = aDatabase.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursor;
	}
}
