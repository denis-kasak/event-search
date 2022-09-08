package eventsearch;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        s = cutTag(response.body(), "<bod", "</body>", "autocomplete1");
		
		
		return;
	}
	
	private static String cutTag(String doc, String tag1, String tag2, String... mode) {
		
		if(mode[0]=="autocomplete1") {//autocompletes <tag1 to <tag1...>
			Pattern tag = Pattern.compile(tag1+"[^>]*>");
			Matcher match = tag.matcher(doc);
			boolean lol = match.find();
			int lol1 = match.start();
			return "lol";
			
		}
		
		String newDoc = doc.substring(doc.indexOf(tag1)+tag1.length(), doc.indexOf(tag2));
		return newDoc;
	}

}
