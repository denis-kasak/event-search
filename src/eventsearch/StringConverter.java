package eventsearch;
import java.util.*;
import java.util.List;
import java.io.*;


public class StringConverter {
	
	public static void listeInDatei(List liste, File datei) {
		//Speichert eine beliebige List als Text in gegebener File
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter(datei));
            Iterator iter = liste.iterator();
            while(iter.hasNext() ) {
                Object o = iter.next();
                printWriter.println(o);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(printWriter != null) printWriter.close();
        }
    } // listeInDatei
	
}																