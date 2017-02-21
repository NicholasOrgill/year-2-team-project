package songmanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.imageio.ImageIO;

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
	 * Converts a SongObject into an XML file.
	 * @param Obj SongObject
	 * @param outputPath Path to write file to
	 */
	public void writeSongObjectToXML(SongObject obj, String outputPath) {
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
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(outputPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads a game-ready XML file and produces a SongObject.
	 * @param inputPath
	 */
	public SongObject readSongObjectFromXML(String inputPath) {
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
					buttons[j] = buttonsList.get(j);
				}
				
				notes[i] = new Note(time, sustain, buttons);
			}
			notes = sortNotes(notes);
			
			// Create and return a SongObject
			SongObject obj = new SongObject(title, artist, songLength, averageTempo, startBeat, notes, beats);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates an object which represents the contents of a song file.
	 * @param songObj A song object for the song
	 * @param audioPath The path to the audio file for the song
	 * @param coverPath The path to the covert art of the song
	 * @return
	 */
	public SongFile createSongFile(SongObject songObj, String audioPath, String coverPath) {
		try {
			BufferedImage coverArt = ImageIO.read(new FileInputStream(coverPath));
			SongFile fileObj = new SongFile(coverArt, audioPath, songObj);
			return fileObj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Writes a SongFile object into a file.
	 * @param songObj A song object for the song
	 */
	public void writeSongFile(SongFile songObj) {
		
	}
	
	/**
	 * Reads a song file into a SongFile object.
	 * @param inputPath Path to the song file
	 * @return A song file object representing the contents of the song file
	 */
	public SongFile readSongFile(String inputPath) {
		return null;
	}
	
	/**
	 * Sorts a Note array into time order.
	 * @param notes Unsorted Note array
	 * @return Sorted Note array
	 */
	private Note[] sortNotes(Note[] notes) {
		// Add each element of array to a TreeMap
		TreeMap<Integer, Note> notesMap = new TreeMap<Integer, Note>();
		int noteArrayLength = notes.length;
		for (int i = 0; i < noteArrayLength; i++) {
			notesMap.put(notes[i].getTime(), notes[i]);
		}
		
		// Extract from the TreeMap in order into a new array
		Note[] newNotes = new Note[noteArrayLength];
		Integer[] notesMapKeys = (Integer[])notesMap.keySet().toArray();
		for (int i = 0; i < noteArrayLength; i++) {
			newNotes[i] = notesMap.get(notesMapKeys[i]);
		}
		return newNotes;
	}
	
	public static void main(String[] args) {
		EofRepacker repacker = new EofRepacker();
		SongObject obj = repacker.getSongObjectFromBassFile("src/songmanager/PART REAL_BASS_RS2.xml");
		SongFileProcessor processor = new SongFileProcessor();
		processor.writeSongObjectToXML(obj, "src/songmanager/songfile.xml");
	}
}









