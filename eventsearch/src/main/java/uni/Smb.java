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
public class Smb {

    static public ArrayList<ArrayList<String>> getEvents() {
        String doc = Scraper.getHtmlDoc("https://www.smb.museum/ausstellungen/aktuell/");
        System.out.println(doc);
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
        
        for(int i = 0; i<segments.size(); i++){
            String segment = segments.get(i);
            
            Pattern pattern1 = Pattern.compile("<h4>");
            Matcher match1 = pattern1.matcher(segment);
            match1.find();
            Pattern pattern2 = Pattern.compile("</p>");
            Matcher match2 = pattern2.matcher(segment);
            match2.find();
            segment = segment.substring(match1.start(), match2.start());
            
            segments.set(i, segment);      
        }
        return segments;
        

    }

    static private ArrayList<ArrayList<String>> buildEvents(ArrayList<String> segments) {
// holt sich alle Werte zwischen HTML Tags aus segments und packt sie in Event

		ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();
		for (String i : segments) {
			ArrayList<String> event = new ArrayList<String>();
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
					continue;
				} else {
					event.add(i.substring(begin, end).trim());
					begin = match.end();
				}
			}
			events.add(event);
		}
		return events;

    }

    static private ArrayList<ArrayList<String>> formatEvents(ArrayList<ArrayList<String>> events) {

        return events;

    }

}
