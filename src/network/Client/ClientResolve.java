package network.Client;

import engine.GameObject;

/**
 * This class is just for receive test
 * 
 * @author Weifeng
 */
public class ClientResolve extends Thread {
	private Network network;
	private GameObject gameObject;

	public ClientResolve(GameObject gameObject, Network network) {
		this.network = network;
		this.gameObject = gameObject;
	}

	// print any messages get from server
	public void run() {
		while (true) {
			String receive = network.receive();
			resolve(receive);
		}
	}

	private void resolve(String _readline) {

		if (_readline.length() >= 5) {
			String keyword = _readline.substring(0,5);
			
			if (keyword.equals("LOAD:")) {
				gameObject.setReady(true);
			} else if (keyword.equals("SCOR:")) {
				int score = Integer.parseInt(_readline.substring(5));
				gameObject.setP2Score(score);
			}else{
				System.out.println(_readline);
			}
		} else {
			System.out.println(_readline);
		}
	}
}