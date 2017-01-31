package input;

import java.util.Scanner;
import javax.swing.JFrame;

//a class for testing
public class Test {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		InputHandler inputHandler = new InputHandler();
		
		frame.add(inputHandler);
		
		frame.setSize(500, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Scanner in = new Scanner(System.in);
		char input = ' ';
		
		//make sure input 4 keys for playing and 3 keys for powers
		while(!inputHandler.containAllKey()){
			inputHandler.removeAllKey();
			System.out.println("Input Key.");
			for(int i = 0; i < 4; i++){
				System.out.print("Input play Key" + i + ": ");
				input = in.next().charAt(0);
				inputHandler.storePlayKey(input);
			}
			for(int i = 0; i < 3; i++){
				System.out.print("Input power Key" + i + ": ");
				input = in.next().charAt(0);
				inputHandler.storePowerKey(input);
			}
		}
		
		//remove the power key that is used
		while(!inputHandler.emptyPowerKey()){
			System.out.println("Used a power key: ");
			input = in.next().charAt(0);
			inputHandler.removePowerKey(input);
		}
		
		
	}

}

