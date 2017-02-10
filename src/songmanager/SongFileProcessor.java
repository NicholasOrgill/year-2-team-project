package songmanager;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class SongFileProcessor {

	public SongFileProcessor() {
		
	}

	/**
	 * Converts a SongObject into an XML file
	 * @param Obj SongObject
	 * @param outputPath Path to write file to
	 */
	public void WriteSongObjectToXML(SongObject obj, String outputPath) {
		try {
			// Root element
			Element songElement = new Element("song");
			Document doc = new Document(songElement);
			
			// Singular elements
			Element titleElement = new Element("title");
			titleElement.setText(obj.getTitle());
			songElement.addContent(titleElement);
			
			Element artistElement = new Element("artist");
			artistElement.setText(obj.getArtist());
			songElement.addContent(artistElement);
			
			Element songLengthElement = new Element("songLength");
			songLengthElement.setText(String.valueOf(obj.getSongLength()));
			songElement.addContent(songLengthElement);
			
			Element averageTempoElement = new Element("averageTempo");
			averageTempoElement.setText(String.valueOf(obj.getAverageTempo()));
			songElement.addContent(averageTempoElement);
			
			Element startBeatElement = new Element("startBeat");
			startBeatElement.setText(String.valueOf(obj.getStartBeat()));
			songElement.addContent(startBeatElement);
			
			// Beat elements
			Element beatsElement = new Element("beats");
			Beat[] beats = obj.getBeats();
			for (int i = 0; i < beats.length; i++) {
				Element beatElement = new Element("beat");
				beatElement.setAttribute(new Attribute("time", String.valueOf(beats[i].getTime())));
				beatElement.setAttribute(new Attribute("measure", String.valueOf(beats[i].getMeasure())));
				beatsElement.addContent(beatElement);
			}
			songElement.addContent(beatsElement);
			
			// Note elements
			Element notesElement = new Element("notes");
			Note[] notes = obj.getNotes();
			for (int i = 0; i < notes.length; i++) {
				Element noteElement = new Element("note");
				noteElement.setAttribute(new Attribute("time", String.valueOf(notes[i].getTime())));
				noteElement.setAttribute(new Attribute("sustain", String.valueOf(notes[i].getSustain())));
				
				int[] buttons = notes[i].getButtons();
				for (int j = 0; j < 4; j++) {
					boolean inList = false;
					for (int k = 0; k < buttons.length; k++) {
						if (buttons[k] == j) {
							inList = true;
						}
					}
					
					if (inList) {
						noteElement.setAttribute(new Attribute("button" + j, "1"));
					} else {
						noteElement.setAttribute(new Attribute("button" + j, "0"));
					}
				}
				
				notesElement.addContent(noteElement);
			}
			songElement.addContent(notesElement);
			
			// Output XML
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getCompactFormat());
			xmlOutput.output(doc, new FileWriter(outputPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a game-ready XML file and produces a SongObject
	 * @param inputPath
	 */
	public SongObject ReadSongObjectFromXML(String inputPath) {
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
			artist = songElement.getChild("artist").getText();
			averageTempo = Integer.valueOf(songElement.getChild("averageTempo").getText());
			songLength = Integer.valueOf(songElement.getChild("songLength").getText());
			startBeat = Integer.valueOf(songElement.getChild("startBeat").getText());
			
			// Retrieve beats
			Element beatsElement = songElement.getChild("beats");
			List<Element> beatElements = beatsElement.getChildren();
			int beatCount = beatElements.size();
			beats = new Beat[beatCount];
			
			for (int i = 0; i < beatElements.size(); i++) {
				int time = beatElements.get(i).getAttribute("time").getIntValue();
				int measure = beatElements.get(i).getAttribute("measure").getIntValue();
				beats[i] = new Beat(time, measure);
			}
			
			// Retrieve notes
			Element notesElement = songElement.getChild("notes");
			List<Element> noteElements = notesElement.getChildren();
			int noteCount = noteElements.size();
			notes = new Note[noteCount];
			
			for (int i = 0; i < noteElements.size(); i++) {
				int time = noteElements.get(i).getAttribute("time").getIntValue();
				int sustain = noteElements.get(i).getAttribute("sustain").getIntValue();
				
				ArrayList<Integer> buttonsList = new ArrayList<Integer>();
				for (int j = 0; j < 4; j++) {
					int buttonActive = noteElements.get(i).getAttribute("button" + j).getIntValue();
					if (buttonActive == 1) {
						buttonsList.add(j);
					}
				}
				
				int[] buttons = new int[buttonsList.size()];
				for (int j = 0; j < buttonsList.size(); j++) {
					buttons[i] = buttonsList.get(i);
				}
				
				notes[i] = new Note(time, sustain, buttons);
			}
			
			// Create and return a SongObject
			SongObject obj = new SongObject(title, artist, songLength, averageTempo, startBeat, notes, beats);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}