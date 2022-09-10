package eventsearch;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinischeGalerie extends EventOwner{

	private static ArrayList<ArrayList<String>> getBg() throws Exception { // returns all events from
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

	public static void getEvents() {
		// TODO Auto-generated method stub
		
	}

}
