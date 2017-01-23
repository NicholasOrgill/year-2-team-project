package main;

import engine.Engine;

public class Main {

	public static void main(String[] args) {
		System.out.println("Launching Game...");
		
		// Control fullscreen
		boolean fullScreen = false;
		
		
		if(args.length > 0) {
			if(args[0].equals("window")) {
				fullScreen = false;
			}
		}
		
		Engine engine = new Engine("Dance Game", fullScreen);
		engine.start();
	}
}
