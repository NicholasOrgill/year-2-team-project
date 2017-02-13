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
			if(receive.equals("LOAD:")){
				network.setPlaying(true);
				GamingThread gameThread = new GamingThread(network);
				gameThread.start();
			}else if(receive.equals("OVER:")){
				network.setPlaying(false);
			}else
			resolve(network.receive());
		}
	}
	
	
	private void resolve(String _readline){
		System.out.println(_readline);
	}
}