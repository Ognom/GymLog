package com.ognom.gymlog.model;

public class Exercise {
	
	private Integer mId;
	private String mName;
	private String mDescription;
	private String mCategory;
	
	public Exercise(String mName, String mCategory, String mDescription)
	{
		//TODO: Make sure the name, category and description contains allowed characters.
		this.mName = mName;
		this.mCategory = mCategory;
		this.mDescription = mDescription; 
	}

	public Integer getId() {
		return mId;
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

	public String getCategory() {
		return mCategory;
	}

	public void setmCategory(String aCategory) {
		this.mCategory = aCategory;
	}
	
	public String getInfo(){
		return new String("Name: " + this.mName + ", category: " + this.mCategory + " and description: " + this.mDescription);
	}
}
