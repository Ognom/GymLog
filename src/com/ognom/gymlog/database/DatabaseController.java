package com.ognom.gymlog.database;

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
	
	
	
	/**Fetches all the highscores, and returns the result in a String matrix.
	public String[][] readHighscore(){
		db = dbHelp.getReadableDatabase();
		Cursor cursor = rhs.read(db);
		cursor.moveToFirst();
		String[][] highscore = new String[cursor.getCount()][cursor.getColumnCount()];
		for (int i = 0; i < cursor.getCount(); i++) {
			highscore[i][0] = cursor.getString(0);
			highscore[i][1] = cursor.getString(1);
			cursor.moveToNext();
		}
		cursor.close();
		db.close();
		return highscore;
	}
	**/

	public void test(){
		System.out.println("I can access methods in dbC");
	}
	
	//Stores all the highscores in the database.
	public boolean storeExercise(String text){
		db = dbHelp.getWritableDatabase();
		boolean store = se.storeExercise(text, db);
		db.close();
		return store;
	}




}

