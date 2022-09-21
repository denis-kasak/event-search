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
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {

    public static ArrayList<ArrayList<String>> getEvents() {

        ArrayList<ArrayList<String>> events = BerlinischeGalerie.getEvents();
        events.addAll(Uci.getEvents());
        events.addAll(Smb.getEvents());

        return events;
    }

    public static String getHtmlDoc(String link) {
        //gibt das HTML doc zum link zurück
        try {
            Document doc = Jsoup.connect(link).get();
            String body = doc.outerHtml();
            return body;
        } catch (IOException e) {
            System.out.println("Konnte kein HTML Dokument von Berlinische Galerie empfangen.");
            return null;
        }
    }
}
