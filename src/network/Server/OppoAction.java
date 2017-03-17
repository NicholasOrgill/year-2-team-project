package network.Server;

import java.io.*;

import engine.GameObject;


public class OppoAction {
	private Player opponent;
	private Player me;
	private PrintStream toOppo;
	private GameObject gameObject;
	
	
	public OppoAction(GameObject _gameObject, Player _opponent,Player _me){
		this.gameObject = _gameObject;
		this.opponent = _opponent;//opponent is client player
		this.me = _me;//me is server player
		
		try {
			toOppo = new PrintStream(_opponent.getSocket().getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setName(String _name){
		opponent.setName(_name);
		gameObject.setP2Name(_name);
		toOppo.println("Your name is " + opponent.getName() + " Your opponent name is " + me.getName());
		System.out.println("Your name is " + me.getName() + " Your opponent name is " + opponent.getName());
	}
		
	public void setReady(){
		opponent.setReady(true);
		opponent.setScore(0);
		if (me.isReady() && opponent.isReady()){
			toOppo.println("All players are ready, Loding Game...");
			System.out.println("All players are ready, Loding Game...");
			setStart();
		}else if (me.isReady() && !opponent.isReady()){
			System.out.println("You are ready now, wait your oppoent...");
			toOppo.println("Your opponent is ready, wait you...");
		}else if (!me.isReady() && opponent.isReady()){
			System.out.println("Your opponent is ready, wait you...");
			toOppo.println("You are ready now, wait your oppoent...");
		}
	}
	
	public void setStart(){
		opponent.setStarted(true);
		System.out.println("Game Start");
		toOppo.println("Game Start");
		toOppo.println("LOAD:");//send key word to client to start game 
		gameObject.setReady(true);

	}
	
	public void updateScore(String _score){
		int score = Integer.parseInt(_score);
		opponent.addScore(score);
		System.out.println("Your score is " +me.getScore() + " Your oppo score is " + opponent.getScore());
		toOppo.println("Your score is " +opponent.getScore() + " Your oppo score is " + me.getScore());
		gameObject.setP2Score(opponent.getScore());
	}
	
	public void gameOver(){
		me.setStarted(false);
		me.setReady(false);
		opponent.setReady(false);
		opponent.setStarted(false);
		toOppo.println("Game Over, your opponent win");
		System.out.println("Your opponent game over, you win");
		toOppo.println("OVER:");//send key word to client to end game
	}
	
	public void setPower(String _key){
		int key = Integer.parseInt(_key);
		gameObject.receivedPower(key);
	}
	
	public void invalidMsg(){
		String msg = "Invalid Message";
		toOppo.println(msg);
	}
	
	
}
