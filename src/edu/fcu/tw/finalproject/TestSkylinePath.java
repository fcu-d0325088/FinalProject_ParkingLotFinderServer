package edu.fcu.tw.finalproject;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

public class TestSkylinePath{
	private ArrayList<Path> pathList;
	private ArrayList<Double> tempFeature;
	private GenerateData gd = null;
	private ArrayList<Path> resultList;
	private ArrayList<ParkingLot> pl;
	
	public TestSkylinePath(double lat, double lon){
		pathList = new ArrayList<Path>();
		
		Path tempPath;
		
		try {
			gd = new GenerateData();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		pl = gd.retArray();
		
		
//		24.1792931,120.6499977
		double distance;
		for(int i = 0; i < pl.size();i++){
			tempFeature=new ArrayList<Double>();
			distance = Math.sqrt(Math.pow((lat-pl.get(i).getLattitude()), 2) + Math.pow((lon-pl.get(i).getLng()), 2));
			distance = distance *111;
			pl.get(i).setDistance(distance);
			
			if(distance > 10.0){
				continue;
			}
			//System.out.println(pl.get(i).getPrice());
			
			tempFeature.add(pl.get(i).getPrice());
			tempFeature.add(distance);
			tempFeature.add(pl.get(i).getAvailable());
			tempPath=new Path(i,tempFeature);
			pathList.add(tempPath);
		}
		
		int count = 0;
		
		SkylineSearch skylineSearch = new SkylineSearch(pathList);
		this.resultList = skylineSearch.skylineSearch();
		for(int i=0;i<resultList.size();i++)
		{
			
		//	System.out.println("path id:"+resultList.get(i).id);
		//	for()
			for(int j=0;j<resultList.get(i).feature.size();j++)
			{
		//		System.out.println("feature"+j+":"+resultList.get(i).feature.get(j));		
				
				
			}
		}
	}
//	attitude;
//	private double lng;
//	private String name;
//	private String address;
//	private double price;
//	private double available;
//	private String opR;
//	private double distance;
	
	
	public String getResult() throws JSONException{
			
		JSONArray jsonNotificationData = new JSONArray();
		
			
		ArrayList<ParkingLot> resultPL = new ArrayList<ParkingLot>();
		int index;
		for(int i = 0; i < resultList.size(); i ++){
			Map<String, String> mw = new Hashtable<String, String>();
			index = resultList.get(i).getId();
			resultPL.add(pl.get(index));
			
			mw.put("name", pl.get(index).getName()+"");
			mw.put("lattitude", pl.get(index).getLattitude()+"");
			mw.put("lng", pl.get(index).getLng()+"");
			mw.put("address", pl.get(index).getAddress());
			mw.put("price", pl.get(index).getPrice()+"");
			mw.put("available", pl.get(index).getAvailable()+"");
			mw.put("opR", pl.get(index).getOpR()+"");
			mw.put("distance", pl.get(index).getDistance()+"");
			
			
			jsonNotificationData.put(mw);
			
			
		}
		System.out.println(jsonNotificationData.toString());
		
		
		
		return jsonNotificationData.toString();
	}
	
	public static void main(String[] args)
	{
		
	
		
	}

}
