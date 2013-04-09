package com.ognom.gymlog.model;

public class Exercise {
	
	private Integer mId;
	private String mName;
	private String mDescription;
	private ExerciseCategory mCategory;
	
	public Exercise(String ... args)
	{
		
	}

	public Integer getId() {
		return mId;
	}

	public void setId(Integer aId) {
		this.mId = aId;
	}

	public String getName() {
		return mName;
	}

	public void setName(String aName) {
		this.mName = aName;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String aDescription) {
		mDescription = aDescription;
	}

	public ExerciseCategory getCategory() {
		return mCategory;
	}

	public void setmCategory(ExerciseCategory aCategory) {
		this.mCategory = aCategory;
	}
}
