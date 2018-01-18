/*
 * Fetches data by sending HTTP requests to The Movie DB API (https://www.themoviedb.org/documentation/api)
 */
package datafetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author Raph
 */
public class RequestHandler {
    /** 
     * Fetches the apiKey from a text file located at projectFolder/apiKey.txt
     * 
     * @return  The string contained in the "apiKey.txt" file
     */
    public static String getApiKey() throws IOException {
        String envPath = "apiKey.txt";
        String content = "";

         try {
             Scanner fileIn = new Scanner(new File(envPath));
             while(fileIn.hasNextLine()){
                 content += fileIn.nextLine();
             }
         } catch (FileNotFoundException ex) {
             System.out.println("[RequestHandler]: getApiKey() error: "+ex.getMessage().toString());
         }

         return content;
    }
    
    /** 
     * Performs a POST request to the specified URL
     *  
     * @param URL a string pointing to the API endpoint holding the data that will be fetched
     * 
     * @return  A JSON String representation of all movies from the specified year
     */
    public static String executePost(String URL) throws IOException {
        // Get the API key
        String apiKey = getApiKey();
        
        // Build a URL object with the api key and open a connection
        URL targetUrl = new URL(URL + "?api_key=" + apiKey);
        URLConnection connection = targetUrl.openConnection();
        
        // Read the data
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        String inputLine;
        String JSON = "";
        
        // Store the JSON response
        while ((inputLine = in.readLine()) != null) {
            JSON = inputLine;
        }
        
        in.close();
        
        return JSON;
    }
    
    /** 
     * Fetches the list of movies for a specific year
     *  
     * @param year a string representation the year the movie was released
     * 
     * @return  A JSON String representation of all movies from the specified year
     */
    public static String getMovies(String year) {
        // TODO
        return year;
    }
}
