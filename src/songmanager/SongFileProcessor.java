package songmanager;

import java.io.FileWriter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class SongFileProcessor {

	public SongFileProcessor() {
		// TODO Auto-generated constructor stub
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
}
