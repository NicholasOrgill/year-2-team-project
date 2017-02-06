package network.Client;

import java.io.*;

public class Test {
	public static void main(String[] args){
		
		String hostname = "localhost";
		
		Network n = new Network(hostname);
		
		(new ReceiveTest(n)).start();
		
		try{
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			while ((userInput = fromUser.readLine()) != null){
				n.send(userInput);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
