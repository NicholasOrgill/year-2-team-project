package songmanager;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class EofRepacker {

	public EofRepacker() {
		
	}
	
	/**
	 * Converts an RS2 Bass XML file generated by Editor on Fire into a SongObject
	 * @param inputPath Path of the input XML file (expected as "~/PART REAL_BASS_RS2.xml")
	 */
	public SongObject getSongObjectFromBassFile(String inputPath) {
		try {
			// Create document from file
			SAXBuilder saxBuilder = new SAXBuilder();
			File inputFile = new File(inputPath);
			Document doc = saxBuilder.build(inputFile);
			
			// Set up variables for song information
			String title, artist;
			int songLength, startBeat, averageTempo;
			Note[] notes;
			Beat[] beats;			
			
			// Retrieve singular elements
			Element songElement = doc.getRootElement();
			title = songElement.getChild("title").getText();
			artist = songElement.getChild("artistName").getText();
			averageTempo = Math.round(Float.valueOf(songElement.getChild("averageTempo").getText()) * 1000);
			songLength = Math.round(Float.valueOf(songElement.getChild("songLength").getText()) * 1000);
			startBeat = Math.round(Float.valueOf(songElement.getChild("startBeat").getText()) * 1000);
			
			// Retrieve beats
			Element beatsElement = songElement.getChild("ebeats");
			int beatCount = beatsElement.getAttribute("count").getIntValue();
			List<Element> beatElements = beatsElement.getChildren();
			beats = new Beat[beatCount];
			
			for (int i = 0; i < beatElements.size(); i++) {
				int time = Math.round(beatElements.get(i).getAttribute("time").getFloatValue() * 1000);
				int measure = beatElements.get(i).getAttribute("measure").getIntValue();
				beats[i] = new Beat(time, measure);
			}
			
			// Retrieve notes
			Element levelElement = songElement.getChild("levels").getChild("level");
			Element notesElement = levelElement.getChild("notes");
			Element chordsElement = levelElement.getChild("chords");
			Element handShapesElement = levelElement.getChild("handShapes");
			
			List<Element> noteElements = notesElement.getChildren();
			List<Element> chordElements = chordsElement.getChildren();
			List<Element> handShapeElements = handShapesElement.getChildren();
			
			int singleNoteCount = notesElement.getAttribute("count").getIntValue();
			int chordCount = chordsElement.getAttribute("count").getIntValue();
			int noteCount = singleNoteCount + chordCount;
			notes = new Note[noteCount];
			
			for (int i = 0; i < singleNoteCount; i++) {
				int time = Math.round(noteElements.get(i).getAttribute("time").getFloatValue() * 1000);
				int sustain = Math.round(noteElements.get(i).getAttribute("sustain").getFloatValue() * 1000);
				int string = noteElements.get(i).getAttribute("string").getIntValue();
				boolean[] buttons = {false, false, false, false};
				buttons[string] = true;
				
				Note note = new Note(time, sustain, buttons);
				notes[i] = note;
			}
			
			for (int i = 0; i < chordCount; i++) {
				Element handShapeElement = handShapeElements.get(i);
				Element chordElement = chordElements.get(i);
				List<Element> chordNoteElements = chordElement.getChildren();
				
				int startTime = Math.round(handShapeElement.getAttribute("startTime").getFloatValue() * 1000);
				int endTime = Math.round(handShapeElement.getAttribute("endTime").getFloatValue() * 1000);
				int sustain = endTime - startTime;
				
				boolean[] buttons = {false, false, false, false};
				for (int j = 0; j < chordNoteElements.size(); j++) {
					int string = chordNoteElements.get(j).getAttribute("string").getIntValue();
					buttons[string] = true;
				}
				
				Note chord = new Note(startTime, sustain, buttons);
				notes[singleNoteCount + i] = chord;
			}
			SongFileProcessor processor = new SongFileProcessor();
			notes = processor.sortNotes(notes);
			
			// Create and return a SongObject
			SongObject obj = new SongObject(title, artist, songLength, averageTempo, startBeat, notes, beats);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}