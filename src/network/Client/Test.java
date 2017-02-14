package network.Client;

import java.io.*;
/**
 * This class is just for test 
 * @author Administrator
 */
public class Test {
	public static void main(String[] args){
		
		String hostname = "localhost";
		String name = "Client";
		
		Network n = new Network(hostname,name);
		
		//start receive test
		(new ClientResolve(n)).start();
				
		//send ant input from user to server
		try{
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			while ((userInput = fromUser.readLine()) != null){
				n.send(userInput);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
}