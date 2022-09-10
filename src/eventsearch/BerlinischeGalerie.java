package eventsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinischeGalerie extends EventOwner {

	public BerlinischeGalerie() throws IOException, InterruptedException {
		String doc = Scraper.getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");
		segment(doc);

	}

	public ArrayList<ArrayList<String>> getEvents() throws Exception { // returns all events from
		// Berlinische Galerie
		ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
		String doc = Scraper.getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

		ArrayList<String> event = new ArrayList<String>();
		ArrayList<String> pattern = new ArrayList<String>();

		Pattern patt1 = Pattern.compile("<div class=\"o-grid-floaty__text\">");
		Matcher match1 = patt1.matcher(doc);
		int i = 0;
		while (match1.find()) {
			System.out.println(i);
			// Author
			pattern.add("<div class=\"o-grid-floaty__text\">");
			pattern.add("<span>");
			doc = Scraper.cutToInfo(doc, pattern);
			event.add(Scraper.cutEnd(doc, "</span>"));

			// Titel
			pattern.clear();
			pattern.add("<p>");
			doc = Scraper.cutToInfo(doc, pattern);
			event.add(Scraper.cutEnd(doc, "<br>"));

			// Untertitel
			pattern.clear();
			pattern.add("<br>");
			doc = Scraper.cutToInfo(doc, pattern);
			event.add(Scraper.cutEnd(doc, "</p>"));

			// Zeitangabe
			pattern.clear();
			pattern.add("<p>");
			doc = Scraper.cutToInfo(doc, pattern);
			String time = Scraper.cutEnd(doc, "<time");
			pattern.clear();
			pattern.add("<time datetime=");
			pattern.add(">");
			doc = Scraper.cutToInfo(doc, pattern);
			event.add(time + " " + Scraper.cutEnd(doc, "</time>"));

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
				if (doc.substring(i, i+4).equals("<div")) {
					divCounter++;
				} else if (doc.substring(i, i+5).equals("</div")) {
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
