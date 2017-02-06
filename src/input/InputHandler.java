package input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;




public class InputHandler extends JPanel implements KeyListener, ActionListener{
	
	private Timer timer;
	private int delay = 8; //milliseconds
	private ArrayList<Integer> record; //record the key was pressed
	private ArrayList<Integer> release; //record the key was released
	private ArrayList<Integer> playKey; //store the keys for playing
	private ArrayList<Integer> powerKey; //store the keys for powers/abilities
	
	
	/**
	 * Initialise InputHandler
	 */
	public InputHandler(){
		record = new ArrayList<Integer>();
		release = new ArrayList<Integer>();
		playKey = new ArrayList<Integer>();
		powerKey = new ArrayList<Integer>();

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this); //repeat the action every 8 milliseconds
		timer.start();
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		
		//check whether the keys are pressed and add them to record arraylist
		switch(e.getKeyCode()) {
			case KeyEvent.VK_ENTER:
				record.add(KeyEvent.VK_ENTER); //10
				break;
			case KeyEvent.VK_LEFT:
				record.add(KeyEvent.VK_LEFT); //37
				break;
			case KeyEvent.VK_RIGHT:
				record.add(KeyEvent.VK_RIGHT); //39
				break;
			case KeyEvent.VK_UP:
				record.add(KeyEvent.VK_UP); //38
				break;
			case KeyEvent.VK_DOWN:
				record.add(KeyEvent.VK_DOWN); //40
				break;
				
			default:
				;
		}
		
		if(playKey.size()== 4){
			if(e.getKeyCode() == playKey.get(0)){
				record.add(playKey.get(0));
			}
			if(e.getKeyCode() == playKey.get(1)){
				record.add(playKey.get(1));
			}
			if(e.getKeyCode() == playKey.get(2)){
				record.add(playKey.get(2));
			}
			if(e.getKeyCode() == playKey.get(3)){
				record.add(playKey.get(3));
			}
			
			
		}
		
		//remove the power key that is used
		if(!powerKey.isEmpty()){
			for(int i=0; i<powerKey.size(); i++){
				if(e.getKeyCode() == powerKey.get(i)){
					record.add(powerKey.get(i));
					powerKey.remove(i);
				}
			}
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		//check whether the keys are pressed and add them to release arraylist
		if(playKey.size()== 4){
			if(e.getKeyCode() == playKey.get(0)){
				release.add(-playKey.get(0));
			}
			if(e.getKeyCode() == playKey.get(1)){
				release.add(-playKey.get(1));
			}
			if(e.getKeyCode() == playKey.get(2)){
				release.add(-playKey.get(2));
			}
			if(e.getKeyCode() == playKey.get(3)){
				release.add(-playKey.get(3));
			}
			
			
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		//print out the element in record arraylist for testing
		if (!record.isEmpty()){
			System.out.println(record.get(0));
			record.remove(0);
		}
		
		//print out the element in release arraylist for testing
		if (!release.isEmpty()){
			System.out.println(release.get(0));
			release.remove(0);
		}
		

		
	}
	
	/**
	 * @param key the input playing key to be stored
	 */
	public void storePlayKey(char key){
		int i = (int) key;
		if(i>=97 && i<=122){
			i = i - 32;
		}
		if(i!=58 && i!=60 && i!=62 && i!=63 && i!=64){
			if(i>=48 && i<=93){
				if(playKey.size() < 4 && !playKey.contains(i) && !powerKey.contains(i)){
					playKey.add(i);
				}else
					System.out.println("Invalid key");
			}else
				System.out.println("Invalid key");
		}else
			System.out.println("Invalid key");
		
		

	}
	
	/**
	 * @param key the input power key to be stored
	 */
	public void storePowerKey(char key){
		int i = (int) key;
		if(i>=97 && i<=122){
			i = i - 32;
		}
		if(i!=58 && i!=60 && i!=62 && i!=63 && i!=64){
			if(i>=48 && i<=93){
				if(powerKey.size() < 3 && !powerKey.contains(i) && !playKey.contains(i)){
					powerKey.add(i);
				}else
					System.out.println("Invalid key");
			}else
				System.out.println("Invalid key");
		}else
			System.out.println("Invalid key");
	}
	
	/**
	 * @return the arraylist of playing keys
	 */
	public ArrayList<Integer> getPlayKey(){
		return playKey;
	}
	
	/**
	 * @return the arraylist of power keys
	 */
	public ArrayList<Integer> getPowerKey(){
		return powerKey;
	}
	
	/**
	 * @return is all the keys are stored and ready to start a game
	 */
	public boolean containAllKey(){
		return (playKey.size() == 4 && powerKey.size() == 3);
	}
	
	/**
	 * remove all the keys that have been stored
	 */
	public void removeAllKey(){
		playKey.clear();
		powerKey.clear();
	}
	
	/**
	 * @param key the input power key to be removed
	 */
	public void removePowerKey(char key){
		int i = (int) key;
		if(i>=97 && i<=122){
			i = i - 32;
		}
		if(powerKey.contains(i)){
			powerKey.remove(powerKey.indexOf(i));
		}else
			System.out.println("No such power key.");
		
	}
	
	/**
	 * @return is the powerKey arraylist empty (all the powers/abilities are used)
	 */
	public boolean emptyPowerKey(){
		return powerKey.isEmpty();
	}

}