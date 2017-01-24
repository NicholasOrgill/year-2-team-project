package songmanager;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

public class EofRepacker {

	public EofRepacker() {
		
	}
	
	public void ParseDocument(String inputPath, String outputPath) {
		try {
			// Create document from file
			SAXBuilder saxBuilder = new SAXBuilder();
			File inputFile = new File(inputPath);
			Document doc = saxBuilder.build(inputFile);
			
			// Take necessary data from document
			String title, artist;
			int songLength, startBeat, averageTempo;
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
