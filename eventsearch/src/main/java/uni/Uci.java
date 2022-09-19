/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author d-kas
 */
public class Uci {

    static public ArrayList<ArrayList<String>> getEvents() {

        String doc = Scraper.getHtmlDoc("https://www.uci-kinowelt.de/kinoprogramm/berlin-mercedes-platz/82/day");

        if (doc != null) {
            ArrayList<String> segments = new ArrayList<String>();
            segments = segmentDoc(doc);

            ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
            events = buildEvents(segments);
            events = formatEvents(events);

            return events;
        } else {
            return new ArrayList<ArrayList<String>>();
        }

    }

    static private ArrayList<String> segmentDoc(String doc) {
        // schneidet relevante Segmente aus großem HTML doc aus und packt sie in
        // segments
        ArrayList<String> segments = new ArrayList<String>();

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

    static private ArrayList<ArrayList<String>> buildEvents(ArrayList<String> segments) {
        // holt sich alle Werte zwischen HTML Tags aus segments und packt sie in Event

        ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
        for (String i : segments) {
            ArrayList<String> event = new ArrayList<String>();
            event.add("UCI Kino Berlin - Mercedes Platz | Luxe");

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

    static private ArrayList<ArrayList<String>> formatEvents(ArrayList<ArrayList<String>> events) {

        for (int i = 0; i < events.size(); i++) {
            String title = events.get(i).get(1);
            
            for (int j = i+1; j < events.size(); j++) {
                if(events.get(j).get(1).equals(title)){
                    for(int k = 2; k<events.get(j).size();k++){
                        events.get(i).add(events.get(j).get(k));
                    }
                    events.remove(j);
                    break;
                }
                
            }
        }

        return events;

    }

}
