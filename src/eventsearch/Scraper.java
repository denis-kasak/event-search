package eventsearch;

import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

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

	private static String cutTag(String doc, String pattern1) throws Exception {

		Pattern patt1 = Pattern.compile(pattern1);
		Matcher match1 = patt1.matcher(doc);
		boolean test = match1.find();

		return doc.substring(match1.end());
	}

	private static ArrayList<String> getBg() throws Exception { // returns all events from
																// Berlinische Galerie
		ArrayList<String> events = new ArrayList<String>();
		String doc = getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

		doc = cutTag(doc, "<div class=\"o-grid-floaty__text\"> ");
		doc = cutTag(doc, "<span>");

		return (events);
	}

}
