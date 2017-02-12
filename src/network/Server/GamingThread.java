package network.Server;

import java.util.Random;

/**
 * This class is keeping sending points or other informations
 * 
 * 
 * @author Weifeng
 *
 */
public class GamingThread extends Thread {

	private MessageQueue serverInput;
	private Player opponent;
	private Player me;

	public GamingThread(MessageQueue _serverInput, Player _opponent, Player _me) {
		this.serverInput = _serverInput;
		this.opponent = _opponent;
		this.me = _me;
	}

	public void run() {
		while (true) {
			
			//when they are all ready keep update the points
			if (me.isReady() && opponent.isReady()) {
				
				try {
					sleep(500);
				} catch (Exception e) {

				}

				int points = new Random().nextInt(100);
				String keyword = "POTS:";
				Message pointmsg = new Message(keyword + points);
				serverInput.offer(pointmsg);

			}else {
				//else stop update
				this.stop();
			}
		}
	}
}
