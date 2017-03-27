package network.Server;

import engine.GameObject;
import network.Message;
import network.MessageQueue;

/**
 * This class is used to determine what action should be done after received the command
 * @author Weifeng
 */
public class SelfResolve extends Thread{
	private MessageQueue serverInput;
	private SelfAction sact;
	private GameObject gameObject;
	
	/**
	 * Constructor 
	 * @param _gameObject
	 * @param _serverInput the input message from server
	 * @param _opponent the data of the opponent
	 * @param me the data of myself 
	 */
	public SelfResolve(GameObject _gameObject,MessageQueue _serverInput,Player _opponent,Player me){
		this.serverInput = _serverInput;
		this.sact = new SelfAction(_gameObject, _opponent, me);
	}

	public void run(){
		while (true){
			Message sMsg = serverInput.take();
			String input = sMsg.getMessage();
			this.resolve(input);
		}
	}	
	
	
	/**
	 * Resolve the String from itself
	 * @param _readline
	 */
	private void resolve(String _readline){
		if(_readline.length() >= 5)	{
			String keyword = _readline.substring(0,5);

			if       (keyword.equals("NAME:")){
				sact.setName(_readline.substring(5));
			}else if (keyword.equals("READ:")){
				sact.setReady();
			}else if (keyword.equals("SCOR:")){
				sact.updateScore(_readline.substring(5));
			}else if (keyword.equals("PREE:")){
				sact.sendPressedKey(_readline.substring(5));
			}else if (keyword.equals("RELE:")){
				sact.sendReleasedKey(_readline.substring(5));
			}else if (keyword.equals("POWE:")){
				sact.sendPower(_readline.substring(5));
			}else if (keyword.equals("COMB:")){
				sact.sendCombo(_readline.substring(5));
			}else if (keyword.equals("TEXT:")){
				sact.sendText(_readline.substring(5));
			}else if (keyword.equals("SONG:")){
				sact.sendSelect(_readline.substring(5));
			}
			
			else {
				sact.invalidMsg();
			}
		}
	}
	
}
