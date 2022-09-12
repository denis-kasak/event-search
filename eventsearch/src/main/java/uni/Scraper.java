/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

/**
 *
 * @author d-kas
 */
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class Scraper {

	public static void debug(){
        }

	public static String getHtmlDoc(String link){
		//gibt das HTML doc zum link zurück
		
		try {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
		
		} catch (IOException | InterruptedException e) {
			System.out.println("Konnte kein HTML Dokument von Berlinische Galerie empfangen.");
			return null;
		}

		
	}
}
