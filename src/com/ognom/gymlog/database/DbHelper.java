package com.ognom.gymlog.database;


import java.sql.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

class DbHelper extends SQLiteOpenHelper {
	private static final String TAG = DbHelper.class.getSimpleName();

	// Exercise variables
	public static final String DB_NAME = "gymlog.db";
	public static final int DB_VERSION = 21;
	public static final String TABLE_1 = "Exercises";
	public static final String TABLE_2 = "Workouts";
	public static final String TABLE_3 = "ExerciseData";
	public static final String C_ID = BaseColumns._ID; // Special for id
	public static final String C_NAME = "Name";
	public static final String C_CATEGORY = "Category";
	public static final String C_DESCRIPTION  = "Description";
	public static final String C_DATE = "Date";
	public static final String C_WEIGHT = "Weight";
	public static final String C_REPETITIONS = "Repetitions";

	Context context;

	protected DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL query to create Exercise table
		String sql_Exercise = String.format(
				"create table %s(id INTEGER PRIMARY KEY, " +
								"%s TEXT, " +
								"%s TEXT, " +
								"%s TEXT)",
				TABLE_1, C_NAME, C_CATEGORY, C_DESCRIPTION
				);
		
		//SQL query to create Workout table
		String sql_Workout = String.format(
				"create table %s(_id INTEGER PRIMARY KEY, " +
						"%s TEXT)",
		TABLE_2, C_DATE
		);
		
		String sql_ExerciseData = String.format(
				"create table %s(_id INTEGER PRIMARY KEY, " +
						"%s TEXT, " +
						"%s TEXT, " +
						"%d INTEGER, " +
						"%d INTEGER, " +
						"FOREIGN KEY(%s) REFERENCES Exercises(Name), " +
						"FOREIGN KEY(%s) REFERENCES Workouts(Date)",
		TABLE_3, C_NAME, C_DATE, C_WEIGHT, C_REPETITIONS, C_NAME, C_DATE 
		);

		Log.d(TAG, "onCreate sql: \n"+sql_Exercise);

		db.execSQL(sql_Exercise);

		
		Log.d(TAG, "onCreate sql: \n"+sql_Workout);

		db.execSQL(sql_Workout);

		
		Log.d(TAG, "onCreate sql: \n"+sql_ExerciseData);

		db.execSQL(sql_ExerciseData);
	
		testExerciseStart(db);
	}

	//Places an initial field in the exercise database.
	public boolean testExerciseStart(SQLiteDatabase db){
		System.out.println("testExerciseStart method executed");
		ContentValues values = new ContentValues();
		values.put("id", "null");
		values.put(DbHelper.C_NAME, "Bänkpress");
		values.put(DbHelper.C_CATEGORY, "Bröst");
		values.put(DbHelper.C_DESCRIPTION, "Beefcake exercise");
		db.insertOrThrow(DbHelper.TABLE_1, null, values);
		
		ContentValues values_1  = new ContentValues();
		values_1.put("_id", "null");
		Date d = new Date(2013, 04, 22);
		values_1.put("Date", d.toString());
		db.insertOrThrow(DbHelper.TABLE_2, null, values_1);
		
		ContentValues values_2 = new ContentValues();
		values_2.put("_id", "null");
		values_2.put()
		return true;
	}

	@Override
	//In the case of an update, all information in the database is flushed.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Typically you do ALTER TABLE... here
		db.execSQL("drop table if exists " + TABLE_1);
		Log.d(TAG, "onUpdate dropped table "+TABLE_1);
		this.onCreate(db);
	}

}
