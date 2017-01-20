package network.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {

	private ArrayList<Player> players;
	private Player player;
	private Socket socket;

	public ServerThread(ArrayList<Player> players, Player _p) {
		super("ServerThread");
		this.players = players;
		this.player = _p;
		this.socket = _p.getSocket();
	}

	public void run() {
		DataOutputStream toClient;
		DataInputStream fromClient;

		try {
			toClient = new DataOutputStream(socket.getOutputStream());
			fromClient = new DataInputStream(socket.getInputStream());

			String readLine;

			while ((readLine = fromClient.readUTF()) != null) {

				if (readLine.substring(0, 5).equals("READ:")) {
					player.setReady(true);
					toClient.writeUTF("U R ready");
					System.out.println(allReady());
					if (allReady()) {
						for (int i = 0; i < players.size(); i++) {
							DataOutputStream broadcast = new DataOutputStream(
									players.get(i).getSocket().getOutputStream());
							broadcast.writeUTF("PLAY:");
						}
					}
				} else {
					System.out.println("Got Message " + readLine);
					toClient.writeUTF("I got you");
				}

			}
		} catch (IOException e) {

		}
	}

	private boolean allReady() {
		int m = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isReady()) {
				m = m + 1;
			}
		}

		if (m == players.size()) {
			return true;
		} else {
			return false;
		}
	}

}
