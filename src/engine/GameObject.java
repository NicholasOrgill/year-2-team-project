package engine;

import input.InputHandler;
import network.Client.Network;
import network.Server.Server;
import screens.Overlay;
import songmanager.SongFile;

/**
 * The game object is given around to all the different scenes
 * @author bobbydilley
 *
 */
public class GameObject {
	
	public final int PERFECT = 10;
	public final int EXCELLENT = 20;
	public final int GOOD = 30;
	public final int OKAY = 50;
	
	private int width;
	private int height;
	private Overlay overlay;
	private InputHandler inputHandler;
	private int p1Score;
	private int p2Score;
	private int p2Power;
	private int p2Combo;
	private String p2Text;
	private boolean isServer = false;
	private boolean isConnected = false;
	private boolean isReady = false;
	private String p1Name = "E2";
	private String p2Name;
	private String hostname = "localhost";
	private Network network;
	private Screen mode;
	private Server server;
	private String networkError = "";
	private SongFile[] songFiles;

	private int[] scoreQuality;
	private double speed;
	
	public GameObject(int width, int height) {
		this.width = width;
		this.height = height;
		songFiles = songmanager.SongFileProcessor.readAllSongFiles();
	}
	
	public int getOffset() {
		return 25;
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
	
	public int getP2Power() {
		return p2Power;
	}

	public void setP2Power(int p2Power) {
		this.p2Power = p2Power;
	}
	
	public int getP2Combo() {
		return p2Combo;
	}

	public void setP2Combo(int p2Combo) {
		this.p2Combo = p2Combo;
	}
	
	public String getP2Text() {
		return p2Text;
	}

	public void setP2Text(String p2Text) {
		this.p2Text = p2Text;
	}
	
	public void setP1Name(String p1Name){
		this.p1Name = p1Name;
	}
	
	public String getP1Name(){
		return p1Name;
	}
	
	public void setP2Name(String p2Name){
		this.p2Name = p2Name;
	}
	
	public String getP2Name(){
		return this.p2Name;
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

	
	public void setServer(Server server){
		this.server = server;
	}
	
	public Server getServer(){
		return this.server;
	}
	
	public void setConnect(boolean isConnected){
		this.isConnected = isConnected;
	}
	
	public boolean isConnected(){
		return this.isConnected;
	}

	public void setReady(boolean isReady){
		this.isReady = isReady;
	}
	
	public boolean isReady(){
		return this.isReady;
	}
	
	public void setNetworkError(String error){
		this.networkError = error;
	}
	
	public String getNetworkError(){
		return networkError;
	}
	
	public void receivedKeyPressed(int _key){
		this.inputHandler.getScreen().oppoKeyPressed(_key);
	}
	
	public void receivedKeyReleased(int _key){
		this.inputHandler.getScreen().oppoKeyReleased(_key);
	}

	public int[] getScoreQuality() {
		return scoreQuality;
	}

	public void setScoreQuality(int[] scoreQuality) {
		this.scoreQuality = scoreQuality;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public SongFile[] getSongFiles() {
		return this.songFiles;
	}

}