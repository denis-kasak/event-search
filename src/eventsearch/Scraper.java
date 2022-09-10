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

		BerlinischeGalerie.getEvents();
	}

	public static String getHtmlDoc(String link) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}

	public static String cutToInfo(String doc, ArrayList<String> anchors) throws Exception {

		for (String i : anchors) {
			Pattern patt1 = Pattern.compile(i);
			Matcher match1 = patt1.matcher(doc);
			match1.find();
			doc = doc.substring(match1.end());
		}

		return doc;
	}

	public static String cutEnd(String doc, String anchor) {

		Pattern patt1 = Pattern.compile(anchor);
		Matcher match1 = patt1.matcher(doc);
		match1.find();
		String info = doc.substring(0, match1.start());

		return info.trim();
	}
}