package network.Client;

/**
 * This class is just for receive test
 * @author Weifeng
 */
public class ClientResolve extends Thread {
	private Network network;
	
	public ClientResolve(Network network){
		this.network = network;
	}
	
	//print any messages get from server
	public void run(){
		while (true){
			String receive = network.receive();
			resolve(receive);
		}
	}
	
	
	private void resolve(String _readline){
		if(_readline.equals("LOAD:")){
			network.setPlaying(true);
			GamingThread gameThread = new GamingThread(network);
			gameThread.start();
		}else if(_readline.equals("OVER:")){
			network.setPlaying(false);
		}else{
			System.out.println(_readline);
		}
	}
}