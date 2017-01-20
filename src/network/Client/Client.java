package network.Client;

import java.io.*;
import java.net.*;

public class Client {
	Socket server;
	DataOutputStream toServer;
	DataInputStream fromServer;
	BufferedReader fromUser;

	public Client(String serverName) {
		try {
			server = new Socket(serverName, 4444);
			toServer = new DataOutputStream(server.getOutputStream());
			fromServer = new DataInputStream(server.getInputStream());
			System.out.println("connected");
		} catch (UnknownHostException e) {
			System.err.println("Unknow host" + serverName);
		} catch (Exception e) {
			System.err.println("Could not get I/O for the connection" + serverName);
			System.exit(-1);
		}
		fromUser = new BufferedReader(new InputStreamReader(System.in));
	}

	public void run() {
		try {
			String userInput ;
			Receive receive = new Receive(server);
			
			receive.start();
			
			while ((userInput = fromUser.readLine()) != null) {
			
				toServer.writeUTF(userInput);
				System.out.println("Sent" + userInput + "to Server");
				

				
			}
		} catch (IOException e) {

		}
	}

	public static void main(String[] args) {
		assert (args.length == 1);
		Client c = new Client(args[0]);
		c.run();
	}
}
