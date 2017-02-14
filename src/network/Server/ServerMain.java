package network.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerMain {
	
	public static void main(String[] args){
		
		MessageQueue serverInput = new MessageQueue();
		String name = "Admin";
		
		Server server = new Server(serverInput,name);
		server.start();
		System.out.println("start");
		
		try{
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			while ((userInput = fromUser.readLine()) != null){
				Message msg = new Message(userInput);
				serverInput.offer(msg);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
}
