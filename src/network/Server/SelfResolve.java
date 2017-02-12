package network.Server;

public class SelfResolve extends Thread{
	private MessageQueue serverInput;
	private Player opponent;
	private Player me;
	private SelfAction sact;
	
	public SelfResolve(MessageQueue _serverInput,Player _opponent,Player me){
		this.serverInput = _serverInput;
		this.opponent = _opponent;
		this.me = me;
		this.sact = new SelfAction(serverInput,opponent, me);
	}

	public void run(){
		while (true){
			Message sMsg = serverInput.take();
			String input = sMsg.getMessage();
			this.resolve(input);
		}
	}	
	
	private void resolve(String _readline){
		if(_readline.length() >= 5)	{
			String keyword = _readline.substring(0,5);

			if       (keyword.equals("NAME:")){
				sact.setName(_readline.substring(5));
			}else if (keyword.equals("READ:")){
				sact.setReady();
			}else if (keyword.equals("POTS:")){
				sact.updatePoints(_readline.substring(5));
			}else if (keyword.equals("OVER:")){
				sact.gameOver();
			}
			
			else {
				sact.invalidMsg();
			}
		}
	}
	
}
