/*
 * Fetches data by sending HTTP requests to The Movie DB API (https://developers.themoviedb.org)
 * The first URL parameter will always be the api key
 */
package datafetcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raph
 */
public class RequestHandler {
    private static String mainEndPoint = "https://api.themoviedb.org/3/discover/movie";
    private static String yearParam = "&primary_release_date.gte=";
    
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
     * @param params a string array of parameter prepended with "&". e.g. "&page=12"
     * 
     * @return  A JSON String representation of the data targeted by the specified parameters
     */
    public static String executePost(String[] params) throws IOException {
        // Get the API key
        String apiKey = getApiKey();
        
        String completeURL = mainEndPoint + "?api_key=" + apiKey;
        
        // Append request parameters to the final URL
        for (String param: params) {
            completeURL += param;
        }
        
        System.out.println(completeURL + "\n");
        
        // Build a URL object with the main endpoint, the api key and open a connection
        URL targetUrl = new URL(completeURL);
                
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
     * Fetches the list of movies released since a specific year
     *  
     * @param year a string representation the minimum year the movie was released
     * 
     * @return  A JSON String representation of all movies since the specified year
     */
    public static String getMoviesSince(String year) {
        // Building the parameter to use in the request
        String[] parameter = {yearParam + year};
        
        String result = "";
        
        try {
            result = executePost(parameter);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
