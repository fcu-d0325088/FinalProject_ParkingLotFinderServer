package edu.fcu.tw.finalproject;


import java.util.ArrayList;

public class Path 
{
	int id;
	ArrayList<Double> feature;
	
	
	public Path(int id,ArrayList<Double> feature)
	{
		this.id=id;
		this.feature=feature;
	}
	
	public int getId(){
		return this.id;
	}
	
}
