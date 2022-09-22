/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author d-kas
 */
public class Model {

    private Map<String, Map<String, Object>> museum = new HashMap<String, Map<String, Object>>();

    private Map<String, Map<String, Object>> kino = new HashMap<String, Map<String, Object>>();

    public Model() {
        initEvents();
    }

    private void initEvents() {

        //Berlinische Galerie
        ArrayList<ArrayList<String>> events = BerlinischeGalerie.getEvents();
        museum.put("Berlinische Galerie", createOrt("Alte Jakobstraße 124-128, 10969 Berlin", 835, 667, events));

        //Staatliche Museen zu Berlin
        events = Smb.getEvents();
        museum.put("Alte Nationalgalerie", createOrt("Bodestraße 1-3, 10178 Berlin", 834, 369, null));
        museum.put("Altes Museum", createOrt("Bodestraße 1-3, 10178 Berlin", 838, 387, null));
        museum.put("Bode-Museum", createOrt("Am Kupfergraben, 10117 Berlin", 796, 350, null));
        museum.put("Friedrichswerdersche Kirche", createOrt("Werderscher Markt, 10117 Berlin", 823, 448, null));
        museum.put("Gemäldegalerie", createOrt("Matthäikirchplatz, 10785 Berlin", 479, 585, null));
        museum.put("Hamburger Bahnhof - Museum für Gegenwart - Berlin", createOrt("Invalidenstraße 50-51, 10557 Berlin", 546, 230, null));
        museum.put("James-Simon-Galerie", createOrt("Bodestraße, 10178 Berlin", 818, 381, null));
        museum.put("Kunstbibliothek", createOrt("Matthäikirchplatz 6, 10785 Berlin", 487, 585, null));
        museum.put("Kunstgewerbemuseum", createOrt("Matthäikirchplatz, 10785 Berlin", 491, 569, null));
        museum.put("Kupferstichkabinett", createOrt("Gemäldegalerie, Matthäikirchplatz, 10785 Berlin", 482, 577, null));
        museum.put("Museum für Fotografie", createOrt("Jebensstraße 2, 10623 Berlin", 108, 602, null));
        museum.put("Neue Nationalgalerie", createOrt("Potsdamer Str. 50, 10785 Berlin", 501, 618, null));
        museum.put("Neues Museum", createOrt("Bodestraße 1-3, 10178 Berlin", 832, 382, null));
        museum.put("Pergamonmuseum", createOrt("Bodestraße 1-3, 10178 Berlin", 816, 364, null));
        museum.put("Pergamonmuseum. Das Panorama", createOrt("Am Kupfergraben 2, 10117 Berlin", 782, 356, null));

        for (int i = 0; i < events.size(); i++) {
            String ort = events.get(i).get(0); //Museum ist nicht in HashMap
            ArrayList<String> event = new ArrayList<String>();
            event = (ArrayList<String>) events.get(i).subList(1,events.get(i).size());
            
            if(museum.get(ort).get("Events")==null){
                ArrayList<ArrayList<String>> currEvents = new ArrayList<ArrayList<String>>();
                currEvents.add(event);
                museum.get(ort).replace("Events", currEvents);
            }else{
                ArrayList<ArrayList<String>> currEvents = new ArrayList<ArrayList<String>>();
                currEvents = (ArrayList<ArrayList<String>>) museum.get(ort).get("Events");
                currEvents.add(event);
                museum.get(ort).replace("Events", currEvents);
            }
            
        }
    }

    private Map<String, Object> createOrt(String adresse, int x, int y, ArrayList<ArrayList<String>> events) {

        Map<String, Object> ort = new HashMap<String, Object>();

        ort.put("Adresse", adresse);
        ort.put("x", x);
        ort.put("y", y);
        ort.put("Events", events);

        return ort;

    }
}
