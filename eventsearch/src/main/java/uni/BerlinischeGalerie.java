/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BerlinischeGalerie {

    static public List<List<String>> getEvents() {
        // gibt eine Liste aktueller Events zurückgifsdghishih

        String doc = Scraper.getHtmlDoc("https://berlinischegalerie.de/ausstellungen/");

        if (doc != null) {
            List<String> segments = new ArrayList<>();
            segments = segmentDoc(doc);

            List<List<String>> events = new ArrayList<>();
            events = buildEvents(segments);
            events = formatEvents(events);

            return events;
        } else {
            return null;
        }
    }

    static private List<String> segmentDoc(String doc) {
        // schneidet relevante Segmente aus großem HTML doc aus und packt sie in
        // segments

        List<String> segments = new ArrayList<>();

        Pattern pattern = Pattern.compile("<div class=\"o-grid-floaty__text\">");
        Matcher match = pattern.matcher(doc);
        while (match.find()) {
            int divCounter = 0;
            for (int i = match.start(); i < doc.length(); i++) {
                if (doc.substring(i, i + 4).equals("<div")) {
                    divCounter++;
                } else if (doc.substring(i, i + 5).equals("</div")) {
                    divCounter--;
                }
                if (divCounter == 0) {
                    segments.add(doc.substring(match.start(), i));
                    break;
                }
            }
        }
        return segments;
    }

    static private List<List<String>> buildEvents(List<String> segments) {
        // holt sich alle Werte zwischen HTML Tags aus segments und packt sie in Event

        List<List<String>> events = new ArrayList<>();
        for (String i : segments) {
            List<String> event = new ArrayList<>();
            Pattern pattern = Pattern.compile("<[^>]*>");
            Matcher match = pattern.matcher(i);
            match.find();
            int begin = match.end();
            while (match.find()) {
                int end = match.start();
                if (match.end() == i.length()) {
                    break;
                }
                if (i.substring(begin, end).trim().equals("")) {
                    begin = match.end();
                } else {
                    event.add(i.substring(begin, end).trim());
                    begin = match.end();
                }
            }
            events.add(event);
        }
        return events;
    }

    static private List<List<String>> formatEvents(List<List<String>> events) {
        // formattiert "bis <neue Zeile> <Datum>" zu "bis <Datum>"

        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < events.get(i).size(); j++) {
                if (events.get(i).get(j).equals("bis")) {
                    events.get(i).set(j, "bis " + events.get(i).get(j + 1));
                    events.get(i).remove(j + 1);
                }
            }
        }
        return events;
    }

}
