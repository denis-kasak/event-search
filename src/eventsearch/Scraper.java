package eventsearch;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class Scraper {

	public static void debug() throws Exception {

		BerlinischeGalerie.getEvents();
	}

	public static String getHtmlDoc(String link) throws IOException, InterruptedException {
		//gibt das HTML doc zum link zurück

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		return response.body();
	}
}