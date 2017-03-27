package network.Client;

import com.sun.org.apache.bcel.internal.generic.Select;

import engine.GameObject;

/**
 * This class is just for receive test
 * 
 * @author Weifeng
 */
public class ClientResolve extends Thread {
	private Network network;
	private GameObject gameObject;

	
	/**
	 * @param gameObject
	 * @param network
	 * Initialize ClientResolve
	 */
	public ClientResolve(GameObject gameObject, Network network) {
		this.network = network;
		this.gameObject = gameObject;
	}

	/**
	 * pass the receive message to resolve method
	 */
	public void run() {
		while (true) {
			String receive = network.receive();
			resolve(receive);
		}
	}

	/**
	 * @param _readline the message to be resolved
	 * This will resolve the received messages
	 */
	private void resolve(String _readline) {

		if (_readline.length() >= 5) {
			
			if(_readline.substring(5).length() < 10){
				String keyword = _readline.substring(0,5);
				
				if (keyword.equals("LOAD:")) {
					gameObject.setReady(true);
				} else if (keyword.equals("SCOR:")) {
					int score = Integer.parseInt(_readline.substring(5));
					gameObject.setP2Score(score);
				} else if (keyword.equals("PREE:")) {
					int key = Integer.parseInt(_readline.substring(5));
					gameObject.receivedKeyPressed(key);
				} else if (keyword.equals("RELE:")) {
					int key = Integer.parseInt(_readline.substring(5));
					gameObject.receivedKeyReleased(key);
				} else if (keyword.equals("POWE:")) {
					int power = Integer.parseInt(_readline.substring(5));
					gameObject.setP2Power(power);
				} else if (keyword.equals("COMB:")) {
					int combo = Integer.parseInt(_readline.substring(5));
					gameObject.setP2Combo(combo);
				} else if (keyword.equals("TEXT:")) {
					gameObject.setP2Text(_readline.substring(5));
				} else if (keyword.equals("SONG:")) {
					int select = Integer.parseInt(_readline.substring(5));
					gameObject.setSongFile(gameObject.getSongFiles()[select]);
				}
			}
		}
	}
}