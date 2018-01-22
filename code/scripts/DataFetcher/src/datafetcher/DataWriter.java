/*
 * This class is responsible for writting data to CSV files
 */
package datafetcher;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Raph
 */
public class DataWriter {
    /** 
     * Extracts movie genres data from actor instances in order to create a CSV file with it
     *  
     * @param actors An array list of actor references holding the data that will power the CSV file
     * @param fileName The name of fileName under which the CSV will be saved
     */
    public static void writeGenresToCSV(ArrayList<Actor> actors, String fileName) {
        FileWriter writer = null;
        String separator = ";";

        try {
            // File header
            writer = new FileWriter(fileName);
            writer.append("Actor");
            writer.append(separator);
            
            // Write categorie headers
            Iterator it = DataHandler.genres.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                
                writer.append(pair.getValue().toString());
                writer.append(separator);
                
                it.remove(); // avoids a ConcurrentModificationException
            }
            
            writer.append('\n');
            
            // Write actual file content
            for (Actor actor: actors) {
                // Insert the actor name
                writer.append(actor.getFullName());
                writer.append(separator);
                
                // Insert the number of movies for each genre
                it = actor.getTotalPerGenre().entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    
                    writer.append(pair.getValue().toString());
                    writer.append(separator);
                
                    it.remove();
                }
                writer.append('\n');
            }
            
            System.out.println("Writing " + fileName + "...");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /** 
     * Extracts movie total amounts from actor instances in order to create a CSV file with it
     *  
     * @param actors An array list of actor references holding the data that will power the CSV file
     * @param fileName The name of fileName under which the CSV will be saved
     */
    public static void writeTotalsToCSV(ArrayList<Actor> actors, String fileName) {
        FileWriter writer = null;
        String separator = ";";

        try {
            // File header
            writer = new FileWriter(fileName);
            writer.append("Actor");
            writer.append(separator);
            writer.append("Totals");
            writer.append('\n');
            
            // Write actual file content
            for (Actor actor: actors) {
                // Insert the actor name
                writer.append(actor.getFullName());
                writer.append(separator);
                
                // Insert the total amount of movies he/she was featured in
                writer.append(String.valueOf(actor.getTotalMovies()));
                writer.append('\n');
            }
            
            System.out.println("Writing " + fileName + "...");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /** 
     * Extracts the most popular movie genre from actor instances in order to create a CSV file with it
     *  
     * @param actors An array list of actor references holding the data that will power the CSV file
     * @param fileName The name of fileName under which the CSV will be saved
     */
    public static void writePopularToCSV(ArrayList<Actor> actors, ArrayList<String> popGenres, ArrayList<Integer> genreCount, String fileName) {
        FileWriter writer = null;
        String separator = ";";

        try {
            // File header
            writer = new FileWriter(fileName);
            writer.append("Actor");
            writer.append(separator);
            writer.append("Totals");
            writer.append(separator);
            writer.append("Most popular movie genre");
            writer.append('\n');
            
            for (int i = 0; i < actors.size(); i++) {
                writer.append(actors.get(i).getFullName());
                writer.append(separator);
                
                System.out.println(popGenres.get(i));
                
                writer.append(genreCount.get(i).toString());
                writer.append(separator);
                
                writer.append(popGenres.get(i));
                
                writer.append('\n');
            }
            
            
            System.out.println("Writing " + fileName + "...");
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
