package network.Server;

import java.io.*;

/**
 * This class is to get messages from user
 * @author Administrator
 */
public class ServerThread extends Thread{
	private Player opponent;
	private Player me;
	private MessageQueue serverInput;
	
	public ServerThread(MessageQueue _serverInput,Player _opponent,Player _me){
		super("ServerThread");
		this.serverInput = _serverInput;
		this.opponent = _opponent;
		this.me = _me;
	}
	
	public void run(){
		
		PrintStream toClient;
		BufferedReader fromClient;
	
		try {
			toClient = new PrintStream(opponent.getSocket().getOutputStream());
			fromClient = new BufferedReader(new InputStreamReader(opponent.getSocket().getInputStream()));
			
			String readLine;

			OppoResolve solve = new OppoResolve(serverInput,opponent,me);
			new SelfResolve(serverInput,opponent,me).start();
			
			
			// for now just return any messages get from player
			// will use ServerResolve to resolve messages later
			while ( (readLine = fromClient.readLine()) !=null ){
				System.out.println("got message "+ readLine +" form " + opponent.getName());
				toClient.println("I got you message : "+ readLine);
				solve.resolve(readLine);
			}
			
		} catch (IOException e) {
			System.out.println("Player " + opponent.getName() + " disconnected");
		}
	}
	
}
