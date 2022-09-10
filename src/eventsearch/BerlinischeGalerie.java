package eventsearch;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinischeGalerie extends EventOwner {

	public BerlinischeGalerie() throws Exception {
		String doc = Scraper.getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");
		ArrayList<String> segments = new ArrayList<String>();
		segments = segment(doc);
		getEvents(segments);

	}

	public ArrayList<ArrayList<String>> getEvents(ArrayList<String> segments) throws Exception { // returns all events
																									// from
		// Berlinische Galerie
		ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();

		for (String i : segments) {
			ArrayList<String> event = new ArrayList<String>();
			Pattern pattern = Pattern.compile("<[^>]*>");
			Matcher match = pattern.matcher(i);
			match.find();
			int begin = match.end();
			while (match.find()) {
				int end = match.start();

				if (match.end() == i.length()) {
					break;
				}
				if (i.substring(begin, end).trim().equals("")) {
					begin = match.end();
					continue;
				} else {
					event.add(i.substring(begin, end).trim());
					begin = match.end();
				}
			}
			events.add(event);

		}

		return events;
	}

	protected ArrayList<String> segment(String doc) {
		ArrayList<String> segments = new ArrayList<String>();

		Pattern pattern = Pattern.compile("<div class=\"o-grid-floaty__text\">");
		Matcher match = pattern.matcher(doc);
		while (match.find()) {
			int divCounter = 0;
			for (int i = match.start(); i < doc.length(); i++) {
				if (doc.substring(i, i + 4).equals("<div")) {
					divCounter++;
				} else if (doc.substring(i, i + 5).equals("</div")) {
					divCounter--;
				}
				if (divCounter == 0) {
					segments.add(doc.substring(match.start(), i));
					break;
				}
			}
		}
		return segments;
	}

}
