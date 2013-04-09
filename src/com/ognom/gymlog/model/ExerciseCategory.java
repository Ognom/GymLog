package com.ognom.gymlog.model;

public class ExerciseCategory {

	private String mId;
	private String mName;	
	
	public ExerciseCategory(String mName)
	{
		this.mName = mName;
	}

	public String getmId() {
		return mId;
	}

	public void setId(String aId) {
		this.mId = aId;
	}

	public String getName() {
		return mName;
	}

	public void setmName(String aName) {
		this.mName = aName;
	}


}
