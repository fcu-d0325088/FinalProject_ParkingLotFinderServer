package edu.fcu.tw.finalproject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;



public class GenerateData {
	static JSONParser parser = new JSONParser();
	InputStream file1 ,file2;
	JSONObject jsonObject = null;
	JSONArray jsonArray1 = null, jsonArray2 = null;
	String lat,lng,name1,name2,available;
	String address1,address2;
	ArrayList<ParkingLot> parkingLotArray = new ArrayList<ParkingLot>();
	
	
	public GenerateData() throws JSONException  {
		try {
			file1 = new FileInputStream("C:\\Users\\sky_h\\workspace\\Sem3_FinalProject_android\\src\\park.json");
			file2 = new FileInputStream("C:\\Users\\sky_h\\workspace\\Sem3_FinalProject_android\\src\\台中停車場作標.json");
			//System.out.println(file1);
			//Reader reader = new InputStreamReader(file1, StandardCharsets.UTF_8);
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(file1, StandardCharsets.UTF_8));
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(file2, StandardCharsets.UTF_8));
			
			jsonArray1 = new JSONArray(buildJsonStr(reader1));
			jsonArray2 = new JSONArray(buildJsonStr(reader2));
		}
				
		  // read from stream
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String cName,cLat,cLon,cAdd,cPrice,cAval,cPR;
		double nLat,nLon;
		double nPrice,nAval;
		
		int count = 0;
		for(int i = 0; i < jsonArray2.length(); i++){
			name1 = ""+jsonArray2.getJSONObject(i).get("停車場");
			address1 = ""+jsonArray2.getJSONObject(i).get("位置");
			for(int j = 0; j < jsonArray1.length(); j++){
				name2 = ""+jsonArray1.getJSONObject(j).get("名稱"); 
			
				address2 = ""+jsonArray1.getJSONObject(j).get("設置地點");
		
				
//				收費費率
				String pricingMethod = ""+jsonArray1.getJSONObject(j).get("收費費率"); 
				if(name1.contains(name2)){
					//System.out.println("=================================================");
					//System.out.println(name1 +" : "+ name2 + " : " +pricingMethod);
					if(address2.contains(address1) || address1.contains(address2)){
						//System.out.println(address1 + " : " + address2);
						count +=1; 
						
						cName = name2;
						cLat  = ""+jsonArray2.getJSONObject(i).get("緯度");
						nLat  = Double.parseDouble(cLat);						
						
						cLon  = ""+jsonArray2.getJSONObject(i).get("經度");
						nLon  = Double.parseDouble(cLon);						
						
						cAval  = ""+jsonArray1.getJSONObject(j).get(" 車位數(汽車) ");
						if(cAval.equals("null")){
							nAval = 0;
						}
						else{
							nAval  = Double.parseDouble(cAval);
						}
						
						
						cPrice  = ""+jsonArray1.getJSONObject(j).get("收費費率");
						nPrice  = toIntPrice(cPrice);
						
						
						cAdd  = ""+jsonArray1.getJSONObject(j).get("設置地點");
						cPR = ""+jsonArray1.getJSONObject(j).get("收費時間");
						
						
						
						this.parkingLotArray.add(new ParkingLot(nLat,nLon,cName,cAdd,nPrice,cPR,nAval));
						
					}
					else{
						continue;
					}	
					//count += 1;
					break;
					
				}
				else{
					continue;
				}
			
			}
		}
		
		System.out.println(count);
	}
	
	public double toIntPrice(String str){
		
		double price = 0.0;
		if(str.contains("元")){
			int startPoint  = str.indexOf("元");
		//	System.out.println(str);
			price = Double.parseDouble(str.substring(startPoint-2, startPoint));
		}
		
	
		
		return price;
	}
	
	public ArrayList<ParkingLot> retArray(){
		return this.parkingLotArray;
	}
	
	public static String buildJsonStr(BufferedReader reader) throws IOException{
		StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
        }
		return sb.toString();
		
	}
	
	public static void main(String[] args) throws JSONException {

		GenerateData gd = new GenerateData();
	}

}
