package network.Client;

public class Message {
	private final String text;
	
	public Message(String _text){
		this.text = _text;
	}
	
	public String getMessage(){
		return this.text;
	}
}
