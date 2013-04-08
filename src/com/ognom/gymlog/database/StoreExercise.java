package com.ognom.gymlog.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

class StoreExercise {

	private static final String TAG = "com.ognom.gymlog.database.DatabaseController.storeExercise()";
	//private DbHelper helper;
	//private SQLiteDatabase db;

	public StoreExercise(){
		//this.helper = helper;

	}
	/*
	Method storeExercise opens the database from a writable perspective, and writes down all
	the exercises into the database.
	 */
	public boolean storeExercise(String text, SQLiteDatabase db){

		String[] data = text.split(",");
		String sql = "SELECT * FROM "+DbHelper.TABLE +" order by " + DbHelper.C_NAME;
		try{
			Cursor cursor = db.rawQuery(sql, new String []{}); //cursor is set to point before the first entry of the query.
			cursor.moveToLast(); //cursor points at the last last row unless empty.
			int t = cursor.getColumnIndex(DbHelper.C_NAME); //t = index of name column (0-based).

			//System.out.println(cursor.getCount());

			while(cursor.getString(t).compareTo(data[0]) < 0 ){ //if < 0 the word is alphabetically lower than where the cursor is pointing at.
				if(cursor.getString(t).compareTo(data[0]) == 0){ //if == 0, we have a duplicate, break.
					Log.d(TAG, "Duplicate found");
					break;
				}
				else
					cursor.moveToPrevious(); //No duplicate, move the cursor one row up.
			}
			
			ContentValues values = new ContentValues();
			System.out.println(data[0] + ", " + data[1] + ", " + data[2]);
			values.put(DbHelper.C_NAME, data[0]);
			values.put(DbHelper.C_CATEGORY, data[1]);
			values.put(DbHelper.C_DESCRIPTION, data[2]);

			db.insertWithOnConflict(DbHelper.TABLE, null, values,
					SQLiteDatabase.CONFLICT_REPLACE);

			return true;
		}
		catch(Exception e){

			e.printStackTrace();
		}
		return false;

	}

}
