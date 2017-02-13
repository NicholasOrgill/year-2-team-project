package network.Client;

import java.util.Random;

public class GamingThread extends Thread {
	
	private Network network;

	public GamingThread(Network network) {
		this.network = network;
	}
	public void run() {
		while (true) {
			
			//when they are all ready keep update the points
			if (network.playing()) {
				
				try {
					sleep(500);
				} catch (Exception e) {
				}
				int points = new Random().nextInt(100);
				String pointmsg = "POTS:" + points;
				network.send(pointmsg);
			}else {
				//else stop update
				this.stop();
			}
		}
	}
}
