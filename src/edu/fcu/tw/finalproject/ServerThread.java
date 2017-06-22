package edu.fcu.tw.finalproject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
	private Socket client = null;
	private PrintStream writer;

	public ServerThread(Socket client) {
		this.client = client;
	}

	// @Override
	public void run() {
		// super.run();
		boolean flag = true;
		try {
			PrintStream getClientInput = new PrintStream(client.getOutputStream());
			BufferedReader buffReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			while (flag) {
				String lat = buffReader.readLine();
				String lng = buffReader.readLine();
				if(lat == null && lng ==null){
					System.out.println("NULL");
					flag = false;
					client.close();
					interrupt();
				}
				else if (!lat.equals("CLOSE") && !lng.equals("CLOSE")) {
					System.out.println("receive the Coordinates: " + lat + "," + lng);
					
					TestSkylinePath sp = new TestSkylinePath(Double.parseDouble(lat),Double.parseDouble(lng));
					System.out.println("filtered items");
					
					
					
					String jsonStr = sp.getResult();
										
					System.out.println("Results acquired");
					
//					ByteArrayOutputStream bao = new ByteArrayOutputStream();
					writer = new PrintStream(client.getOutputStream(),true,"UTF-8");
					writer.println(jsonStr);
//					outToClient.close();
//
//					byte[] byteToTransfer =  outToClient.getBytes();
//					ArrayList<String> test = new ArrayList<String>();
//					test.add("asdasd");
//					test.add("asdas2123");
					
//					 ObjectOutputStream outToClient = new ObjectOutputStream(client.getOutputStream());
//					 outToClient.writeObject(test);   
					 
					 
					System.out.println("Items sent");
				}else{
					flag = false;
					client.close();
					interrupt();
				}
			}
//			getClientInput.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("EXIT");
	}

}
