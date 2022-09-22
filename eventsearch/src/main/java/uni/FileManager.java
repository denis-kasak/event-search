package uni;

import java.util.*;
import java.io.*;

public class FileManager {

    private static String eventsPath = ".\\src\\main\\resources\\uni\\events.txt";

    public static void writeEvents(List<List<String>> events) {
        //wandelt events in eine String um und schreibt die String in eventsPath
        String strEvents = "";

        for (int i = 0; i < events.size(); i++) {
            strEvents = strEvents + "**"; //Anfang eines Events

            for (int j = 0; j < events.get(i).size(); j++) {
                strEvents = strEvents + events.get(i).get(j) + "##";//Trennzeichen. Kein Komma oder Semikolon, da diese in Details vorkommen können

            }
        }

        
        try {
            FileOutputStream writeData = new FileOutputStream(eventsPath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(strEvents);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<List<String>> readEvents() {
        //liest String aus eventsPath und wandelt sie in events um
        try {
            FileInputStream readData = new FileInputStream(eventsPath);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            String strEvents = (String) readStream.readObject();
            readStream.close();

            List<List<String>> events = new ArrayList<List<String>>();

            int counter = -1; //Zählt Events
            for (int i = 0; i < strEvents.length(); i++) {
                char curr1 = strEvents.charAt(i);
                char curr2 = strEvents.charAt(i + 1);

                if (curr1 == '*' && curr2 == '*') {
                    counter++;
                    strEvents = strEvents.substring(i + 2);
                    List<String> event = new ArrayList<String>();
                    events.add(event);
                    i=-1;
                }else{
                    events.get(counter).add(strEvents.substring(0,strEvents.indexOf("##")));
                    strEvents = strEvents.substring(strEvents.indexOf("##")+2);
                    i=-1;
                }
            }
            return events;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
