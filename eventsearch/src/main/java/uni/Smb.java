/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author d-kas
 */
public class Smb {

    static public List<List<String>> getEvents() {
        String doc = Scraper.getHtmlDoc("https://www.smb.museum/ausstellungen/aktuell/");
        if (doc != null) {
            List<String> segments = new ArrayList<String>();
            segments = segmentDoc(doc);

            List<List<String>> events = new ArrayList<List<String>>();
            events = buildEvents(segments);
            events = formatEvents(events);

            return events;
        } else {
            return new ArrayList<List<String>>();
        }
    }

    static private List<String> segmentDoc(String doc) {
        // schneidet relevante Segmente aus großem HTML doc aus und packt sie in
        // segments
        List<String> segments = new ArrayList<String>();

        Pattern pattern = Pattern.compile("<div class=\"image-teaser\"");
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

        for (int i = 0; i < segments.size(); i++) {
            String segment = segments.get(i);

            Pattern pattern1 = Pattern.compile("<h4>");
            Matcher match1 = pattern1.matcher(segment);
            match1.find();
            Pattern pattern2 = Pattern.compile("</p>");
            Matcher match2 = pattern2.matcher(segment);
            match2.find();
            segment = segment.substring(match1.start(), match2.end());

            segments.set(i, segment);
        }
        return segments;

    }

    static private List<List<String>> buildEvents(List<String> segments) {
        // holt sich alle Werte zwischen HTML Tags aus segments und packt sie in Event

        List<List<String>> events = new ArrayList<List<String>>();
        for (String i : segments) {
            List<String> event = new ArrayList<String>();
            Pattern pattern = Pattern.compile("<[^>]*>");
            Matcher match = pattern.matcher(i);

            match.find();
            int begin = match.end(); //Anfang von Info
            while (match.find()) {
                int end = match.start(); //Ende von Info
                String info = i.substring(begin, end).trim();
                if (info.equals("")) {
                    begin = match.end();
                    continue;
                } else {
                    event.add(info);
                    begin = match.end();
                }
            }
            events.add(event);
        }
        return events;
    }

    static private List<List<String>> formatEvents(List<List<String>> events) {

        return events;

    }

}
