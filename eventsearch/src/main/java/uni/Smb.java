/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;

/**
 *
 * @author d-kas
 */
public class Smb {

    static public ArrayList<ArrayList<String>> getEvents() {
        String doc = Scraper.getHtmlDoc("https://www.smb.museum/ausstellungen/aktuell/");

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

        return null;

    }

    static private ArrayList<ArrayList<String>> buildEvents(ArrayList<String> segments) {

        return null;

    }

    static private ArrayList<ArrayList<String>> formatEvents(ArrayList<ArrayList<String>> events) {

        return null;

    }

}
