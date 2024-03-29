/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author d-kas
 */
public class Uci {

    static public List<List<String>> getEvents() {

        String doc = Scraper.getHtmlDoc("https://www.uci-kinowelt.de/kinoprogramm/berlin-mercedes-platz/82/day");

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

        Pattern pattern = Pattern.compile("<div data-slide-segment=\"current-heute");
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
            String title = i.substring(i.indexOf("<h3>") + 4, i.indexOf("</h3>"));
            event.add(title);
            Pattern pattern = Pattern.compile("<span[^>]*>");
            Matcher match = pattern.matcher(i);
            Pattern pattern2 = Pattern.compile("</span>");
            Matcher match2 = pattern2.matcher(i);

            while (match.find() && match2.find()) {
                event.add(i.substring(match.end(), match2.start())); //Kino Spielzeit
            }

            events.add(event);
        }
        return events;

    }

    static private List<List<String>> formatEvents(List<List<String>> events) {

        for (int i = 0; i < events.size(); i++) {
            String title = events.get(i).get(0);
            
            //Merge zwei Filme, wenn sie denselben Titel haben. Nötig weil ein Film manchmal mehrere Listen hat
            for (int j = i + 1; j < events.size(); j++) {
                if (events.get(j).get(0).equals(title)) {
                    for (int k = 1; k < events.get(j).size(); k++) {
                        events.get(i).add(events.get(j).get(k));
                    }
                    events.remove(j);
                    break;
                }

            }
        }

        for (int i = 0; i < events.size(); i++) {
            List<String> event = new ArrayList<>();
            event.add(events.get(i).get(0));
            events.get(i).remove(0);
            Collections.sort(events.get(i));
            
            String time = "";

            for (int j = 0; j < events.get(i).size(); j++) {
                if (j != 0) {
                    time = time + ", " + events.get(i).get(j);
                } else {
                    time = time + events.get(i).get(j);
                }
                
            }
            event.add(time);
            
            events.set(i, event);

        }

        return events;

    }

}
