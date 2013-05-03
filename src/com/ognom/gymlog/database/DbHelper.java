package com.ognom.gymlog.database;


import java.util.Date;
import java.util.GregorianCalendar;

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
	public static final int DB_VERSION = 1;
	public static final String TABLE = "Exercises";
	public static final String TABLE2 = "Workouts";
	public static final String TABLE3 = "WorkoutData";
	public static final String C_ID = BaseColumns._ID; // Special for id
	public static final String C_NAME = "Name";
	public static final String C_CATEGORY = "Category";
	public static final String C_DESCRIPTION  = "Description";
	public static final String C_DATE = "Date";
	public static final String C_REPETITONS = "Repetitions";
	public static final String C_WEIGHT = "Weight";

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
				TABLE, C_NAME, C_CATEGORY, C_DESCRIPTION
				);

		Log.d(TAG, "onCreate sql: \n"+sql_Exercise);
		
		String sql_Workout = String.format(
				"create table %s(_id INTEGER PRIMARY KEY, " +
								 "%s TEXT)",
				TABLE2, C_DATE								 
				);
		
		Log.d(TAG, "onCreate sql: \n"+sql_Workout);
		
		String sql_WorkoutData = String.format(
				"create table %s(_id INTEGER PRIMARY KEY, " + 
								 "%s TEXT, " +
								 "%s TEXT, " +
								 "%s TEXT, " +
								 "%s TEXT, " +
								 "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE, "  +
								 "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE)",
								 TABLE3, C_NAME, C_DATE, C_REPETITONS, C_WEIGHT,
								 C_NAME, TABLE, C_NAME, //Foreign Key 1
								 C_DATE, TABLE2, C_DATE); //Foreign Key 2
		
		Log.d(TAG, "onCreate sql: \n"+sql_WorkoutData);

		//db = this.getWritableDatabase();
		db.execSQL(sql_Exercise);
		db.execSQL(sql_Workout);
		db.execSQL(sql_WorkoutData);
		
		testExerciseStart(db);
		testWorkoutsStart(db);
	}

	//Places an initial field in the exercise database.
	public boolean testExerciseStart(SQLiteDatabase db){
		System.out.println("testExerciseStart method executed");
		
		ContentValues values = new ContentValues();
		values.put(DbHelper.C_NAME, "Bänkpress");
		values.put(DbHelper.C_CATEGORY, "Bröst");
		values.put(DbHelper.C_DESCRIPTION, "Beefcake exercise");
		db.insertOrThrow(DbHelper.TABLE, null, values);
		
		ContentValues values1 = new ContentValues();
		values1.put(DbHelper.C_NAME, "Marklyft");
		values1.put(DbHelper.C_CATEGORY, "Rygg");
		values1.put(DbHelper.C_DESCRIPTION, "Jobbigt halloj");
		db.insertOrThrow(DbHelper.TABLE, null, values1);
		
		ContentValues values2 = new ContentValues();
		values2.put(DbHelper.C_NAME, "Knäböj");
		values2.put(DbHelper.C_CATEGORY, "Ben");
		values2.put(DbHelper.C_DESCRIPTION, "Parre bangar");
		db.insertOrThrow(DbHelper.TABLE, null, values2);
		
		return true;
	}
	
	public boolean testWorkoutsStart(SQLiteDatabase db){
		System.out.println("testWorkoutsStart method executed");
		
		ContentValues values = new ContentValues();

		Date d = new Date();
		values.put(DbHelper.C_DATE, d.toString());
		db.insertOrThrow(DbHelper.TABLE2, null, values);
		
		ContentValues values1 = new ContentValues();
		Date d1 = new Date(2014, 4, 24, 12, 36);
		values1.put(DbHelper.C_DATE, d1.toString());
		db.insertOrThrow(DbHelper.TABLE2, null, values1);
		
		ContentValues values2 = new ContentValues();
		Date d2 = new Date(2015, 4, 24, 12, 36);
		values2.put(DbHelper.C_DATE, d2.toString());
		db.insertOrThrow(DbHelper.TABLE2, null, values2);
		
		return true;
	}

	@Override
	//In the case of an update, all information in the database is flushed.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Typically you do ALTER TABLE... here
		db.execSQL("drop table if exists " + TABLE);
		Log.d(TAG, "onUpdate dropped table "+TABLE);
		
		db.execSQL("drop table if exists " + TABLE2);
		Log.d(TAG, "onUpdate dropped table "+TABLE2);
		
		db.execSQL("drop table if exists " + TABLE3);
		Log.d(TAG, "onUpdate dropped table "+TABLE3);
		this.onCreate(db);
	}

}