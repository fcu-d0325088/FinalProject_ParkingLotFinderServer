package edu.fcu.tw.finalproject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class PostServer {

    
    public String init(String url){
    	URL targetUrl = null;
    	HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            targetUrl = new URL(url);
            urlConnection = (HttpURLConnection) targetUrl.openConnection();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));//do something

            String line = null;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } 
        
        catch (Exception e) {
        	//NOTHING
        } 
        finally {
            urlConnection.disconnect();
        }
        
        return stringBuilder.toString();
    	
    }
}
