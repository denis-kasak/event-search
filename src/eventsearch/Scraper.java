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

		Scraper.cutTag(Scraper.getHtmlDoc(), "<bod", "</body>", "autocomplete1");
	}

	private static String getHtmlDoc(String link) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).GET() // GET is default
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	private static String cutTag(String doc, String tag1, String tag2, String... mode) throws Exception {

		if (mode[0] == "autocomplete1") {// autocompletes <tag1 to <tag1...>
			Pattern tag = Pattern.compile(tag1 + "[^>]*>");
			Matcher match = tag.matcher(doc);
			if (match.find()) {
				tag1 = doc.substring(match.start(), match.end());
			} else {
				throw new Exception("Couldn't autocomplete tag1");
			}
		}

		if (tag2 == null) {

			return doc.substring(doc.indexOf(tag1) + tag1.length());

		}

		return doc.substring(doc.indexOf(tag1) + tag1.length(), doc.indexOf(tag2));
	}

	private static ArrayList<String> getBg() throws IOException, InterruptedException { // returns all events from
																						// Berlinische Galerie

		ArrayList<String> events = new ArrayList<String>();
		String doc = getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

		return (events);
	}

}
