package uni;

import java.util.*;
import java.io.*;

public class StringConverter {

    private static String eventsPath = ".\\src\\main\\resources\\uni\\events.txt";

    public static void writeEvents(ArrayList<ArrayList<String>> events) {

        String strEvents = "";

        for (int i = 0; i < events.size(); i++) {
            strEvents = strEvents + "**"; //Anfang eines Events

            for (int j = 0; j < events.get(i).size(); j++) {
                strEvents = strEvents + events.get(i).get(j) + "##";//Trennzeichen. Kein Komma oder Semikolon, da diese in Details vorkommen können

            }
        }

        saveEvents(strEvents);

    }

    private static void saveEvents(String events) {
        //write to file
        try {
            FileOutputStream writeData = new FileOutputStream(eventsPath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(events);
            writeStream.flush();
            writeStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> readEvents() {
        try {
            FileInputStream readData = new FileInputStream(eventsPath);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            String strEvents = (String) readStream.readObject();
            readStream.close();

            ArrayList<ArrayList<String>> events = new ArrayList<ArrayList<String>>();

            int counter = -1; //Zählt Events
            for (int i = 0; i < strEvents.length(); i++) {
                char curr1 = strEvents.charAt(i);
                char curr2 = strEvents.charAt(i + 1);

                if (curr1 == '*' && curr2 == '*') {
                    counter++;
                    strEvents = strEvents.substring(i + 2);
                    ArrayList<String> event = new ArrayList<String>();
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
