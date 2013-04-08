package com.ognom.gymlog;

import java.util.ArrayList;

public class ExerciseHandler {

	ArrayList<Exercise> exercises;

	public ExerciseHandler(){
		exercises = new ArrayList<Exercise>();
	}

	public void addExercise(Exercise exercise){

		//Check for duplicate exercise, else add new exercise
		if(exercises.contains(exercise))
			System.out.println("Exercise already exists");
		else{
			exercises.add(exercise);
			System.out.println("Exercise successfully added!");
		}		
	}

	public Exercise getExercise(String name){

		//Check exercises for a matching name. Return if a match is found, else return null.
		for(Exercise e: exercises){
			if(e.toString().matches(name))
				return e;
		}
		System.out.println("Exercise not found");
		return null;

	}

}
