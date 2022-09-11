package eventsearch;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinischeGalerie {

	static public ArrayList<ArrayList<String>> getEvents() {
		// gibt eine Liste aktueller Events zurück

		String doc = Scraper.getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

		if (doc != null) {
			ArrayList<String> segments = new ArrayList<String>();
			segments = segmentDoc(doc);

			ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
			events = buildEvents(segments);
			events = formatEvents(events);

			return events;
		} else {
			return new ArrayList<ArrayList<String>>();
		}
	}

	static private ArrayList<String> segmentDoc(String doc) {
		// schneidet relevante Segmente aus großem HTML doc aus und packt sie in
		// segments

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

	static private ArrayList<ArrayList<String>> buildEvents(ArrayList<String> segments) {
		// holt sich alle Werte zwischen HTML Tags aus segments und packt sie in Event

		ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
		for (String i : segments) {
			ArrayList<String> event = new ArrayList<String>();
			event.add("Berlinische Galerie");
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

	static private ArrayList<ArrayList<String>> formatEvents(ArrayList<ArrayList<String>> events) {
		// formattiert "bis <neue Zeile> <Datum>" zu "bis <Datum>"

		for (int i = 0; i < events.size(); i++) {
			for (int j = 0; j < events.get(i).size(); j++) {
				if (events.get(i).get(j).equals("bis")) {
					events.get(i).set(j, "bis " + events.get(i).get(j + 1));
					events.get(i).remove(j + 1);
				}
			}
		}
		return events;
	}

}
