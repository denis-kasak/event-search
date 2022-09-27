/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uni;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author d-kas
 */
public class Model {

    public Map<String, Map<String, Object>> museum = new HashMap<String, Map<String, Object>>();

    public Map<String, Map<String, Object>> kino = new HashMap<String, Map<String, Object>>();

    public Model() {
        initEvents();
    }

    public List<String> getType(String type) {
        List<String> orte = new ArrayList<String>();

        if (type.equals("museum")) {
            for (Map.Entry<String, Map<String, Object>> entry : museum.entrySet()) {
                orte.add(entry.getKey());
            }
        } else if (type.equals("kino")) {
            for (Map.Entry<String, Map<String, Object>> entry : kino.entrySet()) {
                orte.add(entry.getKey());
            }
        }
        return orte;
    }

    public int getX(String ort) {

        if (museum.containsKey(ort)) {
            return (int) museum.get(ort).get("x");

        } else if (kino.containsKey(ort)) {
            return (int) kino.get(ort).get("x");

        } else {
            System.out.println(ort);
            System.out.println("FEHLER: KONNTE ORT IN MAP NICHT FINDEN");
        }

        return -1;

    }

    public int getY(String ort) {

        if (museum.containsKey(ort)) {
            return (int) museum.get(ort).get("y");

        } else if (kino.containsKey(ort)) {
            return (int) kino.get(ort).get("y");

        } else {
            System.out.println(ort);
            System.out.println("FEHLER: KONNTE ORT IN MAP NICHT FINDEN");
        }

        return -1;

    }

    private void initEvents() {

        //Berlinische Galerie
        List<List<String>> events = BerlinischeGalerie.getEvents();
        museum.put("Berlinische Galerie", createOrt("Alte Jakobstraße 124-128, 10969 Berlin", 835, 667, events));

        //Staatliche Museen zu Berlin
        events = Smb.getEvents();
        museum.put("Alte Nationalgalerie", createOrt("Bodestraße 1-3, 10178 Berlin", 834, 369, null));
        museum.put("Altes Museum", createOrt("Bodestraße 1-3, 10178 Berlin", 838, 387, null));
        museum.put("Bode-Museum", createOrt("Am Kupfergraben, 10117 Berlin", 796, 350, null));
        museum.put("Friedrichswerdersche Kirche", createOrt("Werderscher Markt, 10117 Berlin", 823, 448, null));
        museum.put("Gemäldegalerie", createOrt("Matthäikirchplatz, 10785 Berlin", 479, 585, null));
        museum.put("Hamburger Bahnhof – Museum für Gegenwart – Berlin", createOrt("Invalidenstraße 50-51, 10557 Berlin", 546, 230, null));
        museum.put("Humboldt Forum", createOrt("Schlossplatz, 10178 Berlin", 881, 427, null));
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
            String ort = events.get(i).get(0);
            List<String> event = new ArrayList<String>();
            event = events.get(i).subList(1, events.get(i).size());

            if (!museum.containsKey(ort)) {//ignoriere Museen, die nicht in der Map vorkommen
                continue;
            }

            if (!museum.get(ort).containsKey("Events")) {
                List<List<String>> currEvents = new ArrayList<List<String>>();
                currEvents.add(event);
                museum.get(ort).put("Events", currEvents);
            } else {
                List<List<String>> currEvents = new ArrayList<List<String>>();
                currEvents = (List<List<String>>) museum.get(ort).get("Events");
                currEvents.add(event);
                museum.get(ort).replace("Events", currEvents);
            }

        }

        //UCI Kino
        events = Uci.getEvents();
        kino.put("UCI Kino Berlin - Mercedes Platz | Luxe", createOrt("Märkische Allee 176 - 178 , 12681 Berlin", 1316, 654, events));

    }

    private Map<String, Object> createOrt(String adresse, int x, int y, List<List<String>> events) {

        Map<String, Object> ort = new HashMap<String, Object>();

        ort.put("Adresse", adresse);
        ort.put("x", x);
        ort.put("y", y);

        if (events != null) {
            ort.put("Events", events);
        }

        return ort;

    }
}
