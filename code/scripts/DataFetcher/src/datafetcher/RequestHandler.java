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
    // Endpoints
    private static final String mainEndPoint = "https://api.themoviedb.org/3";
    private static final String discoverMoviesEndpoint = "/discover/movie";
    private static final String popularActorsEndpoint = "/person/popular";
    private static final String movieGenresEndpoint = "/genre/movie/list";
    
    // Parameters
    private static final String yearParam = "&primary_release_date.gte=";
    private static final String castingParam = "&with_cast=";
    private static final String pageParam = "&page=";
    private static final String popularityParam = "&sort_by=popularity.desc";
    
    
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
     * Fetches the data targeted by the given url parameter
     *  
     * @param url a string representation of the URL from where data will be fetched
     * 
     * @return  A JSON String representation of the data targeted by the URL parameter
     */
    public static String fetch(String url) throws IOException {
        // Build a URL object with the main endpoint, the api key and open a connection
        URL targetUrl = new URL(url);
                
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
     * Performs a POST request to the specified URL using parameters
     *  
     * @param params a string array of parameter prepended with "&". e.g. "&page=12"
     * 
     * @return  A JSON String representation of the data targeted by the specified parameters
     */
    public static String executePost(String endpoint, String[] params) throws IOException {
        // Get the API key
        String apiKey = getApiKey();
        String completeURL = mainEndPoint + endpoint + "?api_key=" + apiKey;
        
        // Append request parameters to the final URL
        for (String param: params) {
            completeURL += param;
        }
        
        System.out.println(completeURL + "\n");
        
        return fetch(completeURL);
    }
    
    /** 
     * Performs a POST request to the specified URL without parameters
     * 
     * @return  A JSON String representation of the data targeted by the URL
     * 
     */
    public static String executePost(String endpoint) throws IOException {
        // Get the API key
        String apiKey = getApiKey();
        
        String completeURL = mainEndPoint + endpoint + "?api_key=" + apiKey;
        
        System.out.println(completeURL + "\n");
        
        return fetch(completeURL);
    }
    
    /** 
     * Fetches a list of popular actors sorted by popularity (default mode)
     *  
     * @return  A JSON String representation of the most popular actors according to TMDB
     * 
     */
    public static String getPopularActors() {
         String result = "";
        
        try {
            result = executePost(popularActorsEndpoint);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /** 
     * Fetches the list of movies genres available on TMDB
     *  
     * @return  A JSON String representation of the list of genres available on TMDB
     * 
     */
    public static String getMovieGenres() {
        String result = "";
        
        try {
            result = executePost(movieGenresEndpoint);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /** 
     * Fetches a list of movies featuring a specific actor
     * 
     * @param actorId The actor id for which movies will be fetched 
     *  
     * @return  A JSON String representation of the movies featuring the actor specified by parameter
     * 
     */
    public static String getMoviesFeaturing(int actorId) {
         String result = "";
         String[] parameter = {castingParam + actorId};
        
        try {
            result = executePost(discoverMoviesEndpoint, parameter);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /** 
     * Fetches the nth page from the list of movies featuring a specific actor
     * 
     * @param actorId The actor id for which movies will be fetched 
     * @param pageNumber The number of the page that has to be fetched
     *  
     * @return  A JSON String representation of the movies featuring the actor specified by parameter
     * 
     */
    public static String getMoviesFeaturing(int actorId, int pageNumber) {
        String result = "";
        String[] parameter = {castingParam + actorId + pageParam + pageNumber};

        // Delaying API calls to avoid problems regarding TMDB limitations (4/sec)
        try {
            // Wait 1/4th of a second
            Thread.sleep(250);

            try {
                result = executePost(discoverMoviesEndpoint, parameter);
            } catch (IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    /** 
     * Fetches the most popular movies featuring a specific actor
     * 
     * @param actorId The actor id for which movies will be fetched 
     *  
     * @return  A JSON String representation of the movies featuring the actor specified by parameter
     * 
     */
    public static String getMostPopularMovies(int actorId) {
        String result = "";
        String[] parameter = {castingParam + actorId + popularityParam};

        // Delaying API calls to avoid problems regarding TMDB limitations (4/sec)
        try {
            // Wait 1/4th of a second
            Thread.sleep(250);

            try {
                result = executePost(discoverMoviesEndpoint, parameter);
            } catch (IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
