package edu.fcu.tw.finalproject;


import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalDistance {
	
	
	
	
	
	
	public static void main(String[] args) throws JSONException {

		
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location"
				+ "=24.1792931,120.6499977&radius=2000&type=parking&language=zh-TW"
				+ "&key=AIzaSyAOSRwepSct2hKceSp_1kBUC_FaMAcpRCw";
		
		JSONObject jsonObject = new JSONObject(new PostServer().init(url));
		
		System.out.println(url);
		
		String lat;
		String lng;
		String name;
		String name2;
		for(int i=0;i < jsonObject.getJSONArray("results").length();i++){
			lat = ""+jsonObject.getJSONArray("results").getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lat");
			lng = ""+jsonObject.getJSONArray("results").getJSONObject(i).getJSONObject("geometry").getJSONObject("location").get("lng");
			name = ""+jsonObject.getJSONArray("results").getJSONObject(i).get("name");
			//name2  = Charset.forName("UTF-8").encode(name);
			
			System.out.println(name+" "+lat+","+lng);
		}	
		
		String address = "逢甲文华停车场";
		String url2 = "http://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=true&region=tw&language=zh-TW";
		JSONObject jsonObject2 = new JSONObject(new PostServer().init(url2));
		String lat1 = String.valueOf(jsonObject2.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat"));
		String lng1 = String.valueOf(jsonObject2.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng"));
		System.out.println(String.valueOf(jsonObject2.getJSONArray("results").getJSONObject(0).getJSONArray("address_components").getJSONObject(1).get("long_name")));
		System.out.println(lat1 +","+lng1);
	}
	
	

}
