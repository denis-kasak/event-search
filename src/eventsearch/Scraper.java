package eventsearch;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scraper {

	public static void debug() throws Exception {
		
		getBg();

	}

	private static String getHtmlDoc(String link) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).GET() // GET is default
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	private static String cutToInfo(String doc, ArrayList<String> anchors) throws Exception {

		for (String i : anchors) {
			Pattern patt1 = Pattern.compile(i);
			Matcher match1 = patt1.matcher(doc);
			match1.find();
			doc = doc.substring(match1.end());
		}

		return doc;
	}
	
	private static String cutEnd(String doc, String anchor) {
		
		Pattern patt1 = Pattern.compile(anchor);
		Matcher match1 = patt1.matcher(doc);
		match1.find();
		String info = doc.substring(0,match1.start());
		
		return info.trim();
	}

	private static List<String[]> getBg() throws Exception { // returns all events from
																// Berlinische Galerie
		List<String[]> events = new ArrayList<String[]>();
		String doc = getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");
		
		//Author
		ArrayList<String> pattern = new ArrayList<String>();
		pattern.add("<div class=\"o-grid-floaty__text\">");
		pattern.add("<span>");
		doc = cutToInfo(doc, pattern);
		String author = cutEnd(doc, "</span>");
		
		//Titel
		pattern.clear();
		pattern.add("<p>");
		doc = cutToInfo(doc, pattern);
		String title = cutEnd(doc, "<br>");
		
		//Untertitel
		pattern.clear();
		pattern.add("<br>");
		doc = cutToInfo(doc, pattern);
		String subtitle = cutEnd(doc, "</p>");
		
		//Zeitangabe
		pattern.clear();
		pattern.add("<p>");
		doc = cutToInfo(doc, pattern);
		String time = cutEnd(doc, "<time");
		pattern.clear();
		pattern.add("<time datetime=");
		pattern.add(">");
		doc = cutToInfo(doc, pattern);
		time = time + " " + cutEnd(doc, "</time>");
		
		String[] event = {author, title, subtitle, time};
		events.add(event);
		
		return events;
	}

}
