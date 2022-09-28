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

    public static Map<String, Map<String, Object>> museum = new HashMap<>();

    public static Map<String, Map<String, Object>> kino = new HashMap<>();

    public static Map<String, Map<String, Object>> flohmarkt = new HashMap<>();

    private static boolean init = false;

    public static List<String> getAllType(String type) {
        List<String> orte = new ArrayList<>();

        if (type.equals("museum")) {
            for (Map.Entry<String, Map<String, Object>> entry : museum.entrySet()) {
                orte.add(entry.getKey());
            }
        } else if (type.equals("kino")) {
            for (Map.Entry<String, Map<String, Object>> entry : kino.entrySet()) {
                orte.add(entry.getKey());
            }
        }else if (type.equals("flohmarkt")) {
            for (Map.Entry<String, Map<String, Object>> entry : flohmarkt.entrySet()) {
                orte.add(entry.getKey());
            }
        }
        return orte;
    }

    public static List<List<String>> getEvents(String ort) {

        if (museum.containsKey(ort)) {
            return (List<List<String>>) museum.get(ort).get("Events");
        } else if (kino.containsKey(ort)) {
            return (List<List<String>>) kino.get(ort).get("Events");
        }else if (flohmarkt.containsKey(ort)) {
            return (List<List<String>>) flohmarkt.get(ort).get("Events");
        }
        return null;

    }

    public static int getX(String ort) {

        if (museum.containsKey(ort)) {
            return (int) museum.get(ort).get("x");

        } else if (kino.containsKey(ort)) {
            return (int) kino.get(ort).get("x");

        } else if (flohmarkt.containsKey(ort)) {
            return (int) flohmarkt.get(ort).get("x");

        } else {
            System.out.println(ort);
            System.out.println("FEHLER: KONNTE ORT IN MAP NICHT FINDEN");
        }

        return -1;

    }

    public static int getY(String ort) {

        if (museum.containsKey(ort)) {
            return (int) museum.get(ort).get("y");

        } else if (kino.containsKey(ort)) {
            return (int) kino.get(ort).get("y");

        } else if (flohmarkt.containsKey(ort)) {
            return (int) flohmarkt.get(ort).get("y");

        }else {
            System.out.println(ort);
            System.out.println("FEHLER: KONNTE ORT IN MAP NICHT FINDEN");
        }

        return -1;

    }

    public static void initEvents() {

        //Berlinische Galerie
        List<List<String>> events = BerlinischeGalerie.getEvents();
        museum.put("Berlinische Galerie", createOrt("Alte Jakobstraße 124-128, 10969 Berlin", 828, 669, events, "https://berlinischegalerie.de/ausstellungen/aktuell/"));

        //Staatliche Museen zu Berlin
        events = Smb.getEvents();
        museum.put("Alte Nationalgalerie", createOrt("Bodestraße 1-3, 10178 Berlin", 834, 363, null, "https://www.smb.museum/museen-einrichtungen/alte-nationalgalerie/home/"));
        museum.put("Altes Museum", createOrt("Bodestraße 1-3, 10178 Berlin", 838, 395, null, "https://www.smb.museum/museen-einrichtungen/altes-museum/home/"));
        museum.put("Bode-Museum", createOrt("Am Kupfergraben, 10117 Berlin", 793, 340, null, "https://www.smb.museum/museen-einrichtungen/bode-museum/home/"));
        museum.put("Friedrichswerdersche Kirche", createOrt("Werderscher Markt, 10117 Berlin", 823, 448, null, "https://www.smb.museum/museen-einrichtungen/friedrichswerdersche-kirche/home/"));
        museum.put("Gemäldegalerie", createOrt("Matthäikirchplatz, 10785 Berlin", 475, 590, null, "https://www.smb.museum/museen-einrichtungen/gemaeldegalerie/home/"));
        museum.put("Hamburger Bahnhof – Museum für Gegenwart – Berlin", createOrt("Invalidenstraße 50-51, 10557 Berlin", 546, 230, null, "https://www.smb.museum/museen-einrichtungen/hamburger-bahnhof/home/"));
        museum.put("Humboldt Forum", createOrt("Schlossplatz, 10178 Berlin", 865, 415, null, "https://www.humboldtforum.org/de/programm/"));
        museum.put("James-Simon-Galerie", createOrt("Bodestraße, 10178 Berlin", 818, 375, null, "https://www.smb.museum/museen-einrichtungen/james-simon-galerie/home/"));
        museum.put("Kunstbibliothek", createOrt("Matthäikirchplatz 6, 10785 Berlin", 487, 585, null, "https://www.smb.museum/museen-einrichtungen/kunstbibliothek/home/"));
        museum.put("Kunstgewerbemuseum", createOrt("Matthäikirchplatz, 10785 Berlin", 489, 560, null, "https://www.smb.museum/museen-einrichtungen/kunstgewerbemuseum/home/"));
        museum.put("Kupferstichkabinett", createOrt("Gemäldegalerie, Matthäikirchplatz, 10785 Berlin", 475, 570, null, "https://www.smb.museum/museen-einrichtungen/kupferstichkabinett/home/"));
        museum.put("Museum für Fotografie", createOrt("Jebensstraße 2, 10623 Berlin", 100, 600, null, "https://www.smb.museum/museen-einrichtungen/museum-fuer-fotografie/home/"));
        museum.put("Neue Nationalgalerie", createOrt("Potsdamer Str. 50, 10785 Berlin", 495, 608, null, "https://www.smb.museum/museen-einrichtungen/neue-nationalgalerie/home/"));
        museum.put("Neues Museum", createOrt("Bodestraße 1-3, 10178 Berlin", 832, 382, null, "https://www.smb.museum/museen-einrichtungen/neues-museum/home/"));
        museum.put("Pergamonmuseum", createOrt("Bodestraße 1-3, 10178 Berlin", 816, 355, null, "https://www.smb.museum/museen-einrichtungen/pergamonmuseum/home/"));
        museum.put("Pergamonmuseum. Das Panorama", createOrt("Am Kupfergraben 2, 10117 Berlin", 775, 350, null, "https://www.smb.museum/museen-einrichtungen/pergamonmuseum-das-panorama/home/"));

        for (int i = 0; i < events.size(); i++) {
            String ort = events.get(i).get(0);
            List<String> event = new ArrayList<>();
            event = events.get(i).subList(1, events.get(i).size());

            if (!museum.containsKey(ort)) {//ignoriere Museen, die nicht in der Map vorkommen
                continue;
            }

            if (!museum.get(ort).containsKey("Events")) {
                List<List<String>> currEvents = new ArrayList<>();
                currEvents.add(event);
                museum.get(ort).put("Events", currEvents);
            } else {
                List<List<String>> currEvents = new ArrayList<>();
                currEvents = (List<List<String>>) museum.get(ort).get("Events");
                currEvents.add(event);
                museum.get(ort).replace("Events", currEvents);
            }

        }

        //UCI Kino
        events = Uci.getEvents();
        kino.put("UCI Kino Berlin - Mercedes Platz | Luxe", createOrt("Märkische Allee 176 - 178 , 12681 Berlin", 1316, 625, events, "https://www.uci-kinowelt.de/kinoprogramm/berlin-mercedes-platz/82/day"));
        
        //Flohmärkte
        flohmarkt.put("Antik- und Buchmarkt am Bode-Museum", createMarkt("Am Kupfergraben 1, 10117 Berlin", 786, 359, "Der regelmäßige Antik- und Buchmarkt bietete mit rund 60 Händlern jeden Sonnabend, Sonntag und an Feiertagen vor der tollen Kulisse jede Menge Bücher, Schallplatten, Kunsthandwerk, Schmuck, Geschirr und Besteck, Münzen, alte Postkarten und Mobiliar an.","https://www.berlin.de/special/shopping/flohmaerkte/1998194-1724959-antik-und-buchmarkt-am-bodemuseum.html"));
        flohmarkt.put("Trödelmarkt Straße des 17. Juni", createMarkt("Straße des 17. Juni 106, 10623 Berlin", 407, 469, "Der meist sehr belebte Trödelmarkt erstreckt sich vom Bahnhof Tiergarten ausgehend über die Straße des 17. Juni und ist am Samstag und Sonntag von 10 bis 17 Uhr geöffnet. Der Besuch lässt sich gut mit einem Snack an einem der Imbissstände auf dem Marktgelände oder in einem der umliegenden Restaurants und einem Spaziergang durch den Großen Tiergarten verbinden.","https://www.berlin.de/special/shopping/flohmaerkte/1998184-1724959-troedel-und-kunstmarkt-strasse-des-17-ju.html"));
        flohmarkt.put("Neuköllner Wochenmarkte Maybachufer", createMarkt("Maybachufer, 12047 Berlin", 1027, 686, "Auf dem Markt am Maybachufer riecht, seht, hört und schmeckt ihr die Welt! Mit internationalen Spezialitäten, frisch geerntetem Obst und Gemüse aus Afrika, Asien, Europa – und Bio aus Brandenburg. Auch mit feinen Stoffen, Bändern und Borten. Ein Besuch lohnt sich auf dem orientalischen Bazar mit Kiezflair, schließlich ist er der größte seiner Art in Berlin.","https://nowkoelln.de/"));
        flohmarkt.put("Kunst- & Trödelmarkt Fehrbelliner Platz", createMarkt("Fehrbelliner Platz 8, 10707 Berlin", 269, 737, "Origineller Wochenendflohmarkt mit Werken lokaler Künstler sowie Kleidung, Porzellan und Vintage-Schmuck.","https://burdack-maerkte.de/"));
        flohmarkt.put("Trödelmarkt Marheinekeplatz", createMarkt("Marheinekeplatz, 10961 Berlin", 836, 775, "Am Ende der Bergmannstraße mit typischem kreuzberger Flair findet jeden Samstag und Sonntag ein bunt gemischter Trödelmarkt statt. Hier gibt es für jeden etwas: Antiquitäten, Fashion, alles rund ums Kind, Fahrräder und Zubehör, ausgefallene Kunst und Designerstücke, Handarbeiten, Bücher, Gebrauchs-trödel, Platten und DVDs und noch vieles mehr…","http://trödelmarkt-marheinekeplatz.de/"));
        flohmarkt.put("Hallenflohmarkt an der Arena", createMarkt("Eichenstraße 4A, 12435 Berlin", 1251, 674, "Kaum etwas, was es hier nicht gibt: Beim Hallentrödelmarkt an der Arena in Treptow finden Besucher jeden Samstag und Sonntag Altes, Kurioses, Praktisches und Ramsch.","https://www.berlin.de/special/shopping/flohmaerkte/3079685-1724959-hallenflohmarkt-an-der-arena-treptow.html"));
        flohmarkt.put("RAW Flohmarkt", createMarkt("Revaler Str. 99, 10245 Berlin", 1257, 547, "Wöchentlicher Flohmarkt mit Kleidung, Möbeln und Sammlerstücken sowie Streetfood und extravaganter Atmosphäre.","https://www.rawflohmarkt.de/"));

    }

    public static String getLink(String ort) {
        if (museum.containsKey(ort)) {
            return (String) museum.get(ort).get("link");
        } else if (kino.containsKey(ort)) {
            return (String) kino.get(ort).get("link");
        } else if (flohmarkt.containsKey(ort)) {
            return (String) flohmarkt.get(ort).get("link");

        }else {
            return null;
        }
    }
    
    private static Map<String, Object> createOrt(String adresse, int x, int y, List<List<String>> events, String link) {

        Map<String, Object> ort = new HashMap<>();

        ort.put("Adresse", adresse);
        ort.put("x", x);
        ort.put("y", y);

        if (events != null) {
            ort.put("Events", events);
        }

        ort.put("link", link);

        return ort;

    }
    private static Map<String, Object> createMarkt(String adresse, int x, int y, String events, String link) {

        Map<String, Object> ort = new HashMap<>();

        ort.put("Adresse", adresse);
        ort.put("x", x);
        ort.put("y", y);
        
        List<List<String>> beschreibungListe = new ArrayList<>();
        List<String> beschreibung = new ArrayList<>();
        beschreibung.add(events);
        beschreibungListe.add(beschreibung);
        if (events != null) {
            ort.put("Events", beschreibungListe);
        }

        ort.put("link", link);

        return ort;

    }

    static String getAdress(String ort) {
        if (museum.containsKey(ort)) {
            return (String) museum.get(ort).get("Adresse");
        } else if (kino.containsKey(ort)) {
            return (String) kino.get(ort).get("Adresse");
        }else if (flohmarkt.containsKey(ort)) {
            return (String) flohmarkt.get(ort).get("Adresse");
        }
        return null;
    }

    
}
