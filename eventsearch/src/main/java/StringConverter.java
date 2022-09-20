import java.util.*;
import java.util.List;
import java.util.stream.Stream;
//import java.util.*;
import java.io.*;
//import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StringConverter {
	public static void writeEvents(List<ArrayList> EventListe) { 	        
		
		//String fileSeparator = System.getProperty("file.separator");
		String relativPath = "..\\resources\\uni";
		//String relativPath2 = "C:\\Users\\marce\\Documents\\Test.txt";
		
		PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new File(relativPath));
            Iterator iter = EventListe.iterator();
            while(iter.hasNext() ) {					// läuft die ArrayListe durch, solange es ein nächstes Element gibt,
                Object nächstesElement = iter.next(); 	// speichert dieses Element in "nächstesElement" 
                printWriter.println(nächstesElement); 	// und schreib das "nächsteElement" wiederum in die Text-Datei
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(printWriter != null) printWriter.close();
        }
    } // listeInDatei
	
	public static void readEvents(String realtivPath) {
		try (Stream<String> stream = Files.lines(Paths.get(realtivPath))) {
		    stream.forEach(System.out::println);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}
