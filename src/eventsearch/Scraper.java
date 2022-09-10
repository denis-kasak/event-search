package eventsearch;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
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

	private static String cutToInfo(String doc, String[] anchors) throws Exception {

		for (int i = 0; i < anchors.length; i++) {
			Pattern patt1 = Pattern.compile(anchors[i]);
			Matcher match1 = patt1.matcher(doc);
			boolean test = match1.find();
			doc = doc.substring(match1.end());
		}

		return doc;
	}
	
	private static String cutEnd(String doc, String anchor) {
		
		Pattern patt1 = Pattern.compile(anchor);
		Matcher match1 = patt1.matcher(doc);
		boolean test = match1.find();
		doc = doc.substring(0,match1.start());
		
		return doc;
	}

	private static ArrayList<String> getBg() throws Exception { // returns all events from
																// Berlinische Galerie
		ArrayList<String> events = new ArrayList<String>();
		String doc = getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

		String[] pattern = { "<div class=\"o-grid-floaty__text\">", "<span>" };
		doc = cutToInfo(doc, pattern);
		String author = cutEnd(doc, "</span>");
		
		return (events);
	}

}
