package com.ognom.gymlog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Workout 
{
	private Integer mId;
	private String mName;
	private Date mDate;
	private List<Exercise> mExercises;
	
	public Workout()
	{
		mExercises = new ArrayList<Exercise>();
		mDate = new Date();
	}
	
	public Workout(Integer aId, String aName, Date aDate, List<Exercise> aExercises)
	{
		mId = aId;
		mName = aName;
		mDate = (Date) aDate.clone();
		mExercises = new ArrayList<Exercise>();
		mExercises.addAll(aExercises);
	}
	
	public void addExercise(Exercise exercise){
		mExercises.add(exercise);
	}
		
	public Integer getmId() {
		return mId;
	}
	public void setId(Integer aId) {
		this.mId = aId;
	}
	public String getName() {
		return mName;
	}
	public void setmName(String aName) {
		this.mName = aName;
	}
	public Date getDate() {
		return mDate;
	}
	public void setmDate(Date aDate) {
		this.mDate = aDate;
	}
	public List<Exercise> getmExercises() {
		return mExercises;
	}
}
