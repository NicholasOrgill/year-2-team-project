package network.Server;

import java.io.IOException;
import java.io.PrintStream;


public class SelfAction {
	private MessageQueue serverInput;
	private Player me;
	private Player opponent;
	private PrintStream toOppo;
	
	public SelfAction(MessageQueue _serverInput,Player _opponent, Player _me){
		this.serverInput = _serverInput;
		this.opponent = _opponent;//opponent is client player
		this.me = _me;//me is server player
		
		try {
			toOppo = new PrintStream(_opponent.getSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setName(String _name){
		me.setName(_name);
		System.out.println("your name is " + me.getName() + " Your opponent name is " + opponent.getName());
		toOppo.println("Your name is " + opponent.getName() + " Your opponent name is " + me.getName());
	}
	
	public void setReady(){
		me.setReady(true);
		me.setPoints(0);
		if (me.isReady() && opponent.isReady()){
			System.out.println("All players are ready, Loding Game...");
			toOppo.println("All players are ready, Loding Game...");
			setStart();
		}else if (me.isReady() && !opponent.isReady()){
			System.out.println("You are ready now, wait your oppoent...");
			toOppo.print("Your opponent is ready, wait you...");
		}else if (!me.isReady() && opponent.isReady()){
			System.out.println("Your opponent is ready, wait you...");
			toOppo.println("You are ready now, wait your oppoent...");
		}
	}
	
	public void setStart(){
		me.setStarted(true);
		System.out.println("Game Start");
		toOppo.println("Game Start");
		toOppo.println("LOAD:");//send key word to client to start game
		new GamingThread(serverInput,opponent,me).start();
	}
	
	public void updatePoints(String _points){
		int points = Integer.parseInt(_points);
		me.addPoints(points);
		System.out.println("Your points is " +me.getPoints() + " Your oppo points is " + opponent.getPoints());
		toOppo.println("Your points is " +opponent.getPoints() + " Your oppo points is " + me.getPoints());
	}
	
	
	public void gameOver(){
		me.setStarted(false);
		me.setReady(false);
		opponent.setReady(false);
		opponent.setStarted(false);
		System.out.println("Game Over, your opponent win");
		toOppo.println("Your opponent game over, you win");
		toOppo.println("OVER:");//send key word to client to end game
	}
	
	public void invalidMsg(){
		String msg = "Invalid Message";
		System.out.println(msg);
	}
	
	
}
