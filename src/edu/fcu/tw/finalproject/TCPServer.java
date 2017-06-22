package edu.fcu.tw.finalproject;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class TCPServer {
	public final static int PORT = 60132;
	private BufferedReader reader = null;
	//public final static String ADDRESS = "127.0.0.1";
	
/*
 *  https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=
 *  24.1792931,120.6499977&radius=500&type=parking&key=AIzaSyAOSRwepSct2hKceSp_1kBUC_FaMAcpRCw
 * 
 * 
 */

	public static void main(String[] args) throws IOException {
		ServerSocket socket = null;
		
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location"
					+ "=24.1792931,120.6499977&radius=500&type=parking"
					+ "&key=AIzaSyAOSRwepSct2hKceSp_1kBUC_FaMAcpRCw";
		System.out.println(new PostServer().init(url));
		
		
		try {
			socket = new ServerSocket(PORT);
			Socket client = null;
			System.out.println("Server open!");
			
			while (true) {
				client = socket.accept();
				System.out.println("connect success");
//				ServerThread thread = new ServerThread(client);
//				thread.start();
				new Thread(new ServerThread(client)).start();
				
				
				
				
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			
			if(socket != null)
				socket.close();
		}
		
	}
	
	
	

}
