/*
 * Fetches data by sending HTTP requests to The Movie DB API (https://www.themoviedb.org/documentation/api)
 */
package datafetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Raph
 */
public class RequestHandler {
    /** 
     * Performs a POST request to the specified URL
     *  
     * @param URL a string pointing to the API endpoint holding the data that will be fetched
     * 
     * @return  A JSON String representation of all movies from the specified year
     */
    public static String executePost(String URL) throws IOException {
        
        // Build a URL object and open a connection
        URL targetUrl = new URL(URL);
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
