package network.Server;


public class OppoResolve {
	
	private OppoAction action;
	
	public OppoResolve(MessageQueue _serverInput,Player _opponent,Player _me){
		this.action = new OppoAction(_serverInput,_opponent,_me);
	}
	
	
	public void resolve(String _readline){
	
		if(_readline.length() >= 5)	{
			String keyword = _readline.substring(0,5);

			if       (keyword.equals("NAME:")){
				action.setName(_readline.substring(5));
			}else if (keyword.equals("READ:")){
				action.setReady();
			}else if (keyword.equals("POTS:")){
				action.updatePoints(_readline.substring(5));
			}else if (keyword.equals("OVER:")){
				action.gameOver();
			}
			
			else {
				action.invalidMsg();
			}
		}
		
	}
}
