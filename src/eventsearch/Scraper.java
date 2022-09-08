package eventsearch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class Scraper {
	
	public static void getHtml() throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://berlinischegalerie.de/ausstellungen/"))
                .GET() // GET is default
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        
        String s = response.body();
        
        int i = s.indexOf("<body>");
        int k = s.lastIndexOf("</body>");
        
        s = cutTag(response.body(), "<body>", "</body>");
		
		
		return;
	}
	
	private static String cutTag(String doc, String tag1, String tag2) {
		
		String newDoc = doc.substring(doc.indexOf(tag1)+tag1.length(), doc.indexOf(tag2));
		return newDoc;
	}

}
