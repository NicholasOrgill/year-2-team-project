package engine;

import input.InputHandler;
import network.Client.Network;
import screens.Overlay;

/**
 * The game object is given around to all the different scenes
 * @author bobbydilley
 *
 */
public class GameObject {
	
	public final int PERFECT = 50;
	public final int EXCELLENT = 100;
	public final int GOOD = 150;
	public final int OKAY = 200;
	
	private int width;
	private int height;
	private Overlay overlay;
	private InputHandler inputHandler;
	private int p1Score;
	private int p2Score;
	private boolean isServer = false;
	private String playerName = "E2";
	private String hostname = "localhost";
	private Network network;
	private Screen mode;
	

	public GameObject(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public InputHandler getInputHandler() {
		return this.inputHandler;
	}
	
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setOverlay(Overlay overlay) {
		this.overlay = overlay;
	}
	
	public Overlay getOverlay() {
		return this.overlay;
	}

	public int getP1Score() {
		return p1Score;
	}

	public void setP1Score(int score) {
		this.p1Score = score;
	}

	public int getP2Score() {
		return p2Score;
	}

	public void setP2Score(int p2Score) {
		this.p2Score = p2Score;
	}
	
	public void setName(String name){
		this.playerName = name;
	}
	
	public String getName(){
		return playerName;
	}
	
	public void setHostname(String hostname){
		this.hostname = hostname;
	}
	
	public String getHostname(){
		return this.hostname;
	}
	
	public void setNetwork(Network network){
		this.network = network;
	}
	
	public Network getNetwork(){
		return this.network;
	}

	public Screen getMode() {
		return mode;
	}

	public void setMode(Screen mode) {
		this.mode = mode;
	}
}
