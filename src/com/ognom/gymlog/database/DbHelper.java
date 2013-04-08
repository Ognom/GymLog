package com.ognom.gymlog.database;


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
	public static final int DB_VERSION = 20;
	public static final String TABLE = "Exercises";
	public static final String C_ID = BaseColumns._ID; // Special for id
	public static final String C_NAME = "Name";
	public static final String C_CATEGORY = "Category";

	Context context;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL query to create Exercise table
		String sql_Exercise = String.format(
				"create table %s (%s TEXT primary key, %s TEXT)",
				TABLE, C_NAME, C_CATEGORY
				);

		Log.d(TAG, "onCreate sql: \n"+sql_Exercise);

		//db = this.getWritableDatabase();
		db.execSQL(sql_Exercise);
		testExerciseStart(db);
	}

	//Places an initial field in the exercise database.
	public boolean testExerciseStart(SQLiteDatabase db){
		ContentValues values = new ContentValues();
		values.put(DbHelper.C_NAME, "Christian");
		values.put(DbHelper.C_CATEGORY, 2000);
		db.insertWithOnConflict(DbHelper.TABLE, null, values,
				SQLiteDatabase.CONFLICT_REPLACE);
		return true;
	}

	@Override
	//In the case of an update in the game version, all information in the database is flushed.
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Typically you do ALTER TABLE... here
		db.execSQL("drop table if exists " + TABLE);
		Log.d(TAG, "onUpdate dropped table "+TABLE);
		this.onCreate(db);
	}

}
