/*
 * This class is responsible for the data manipulation
 */
package datafetcher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.json.*;

/**
 *
 * @author Raph
 */
public class DataHandler {
    // Links genres IDs with genre names e.g. <28, action>
    public static LinkedHashMap<Integer, String> genres = new LinkedHashMap<>();
    public static ArrayList<Actor> actors = new ArrayList<>();
    
    /** 
     * Parses a JSON to extract and save contained genres data
     * 
     * @param json The String representation of genres
     */
    public static void saveGenres(String json) {
        JSONObject moviesData = new JSONObject(json);
        
        JSONArray genreList = moviesData.getJSONArray("genres");
        
        for (int i = 0; i < genreList.length(); i++) {
            JSONObject genre = (JSONObject) genreList.get(i);
            
            int id = genre.getInt("id");
            String name = genre.getString("name");
            
            genres.put(id, name);
        }    
        
//        System.out.println("Genres fetched! \n");
//        
//        System.out.println(genres);
//        
//        System.out.println("\n___________\n");
    }
    
    /** 
     * Parses a JSON to extract and save contained actors data
     * 
     * @param json The String representation of actors
     * @param max The maximum number of actors to fetch
     * 
     * @return A boolean indicating whether the data has been saved or not 
     */
    public static boolean saveActors(String json, int max) {
        JSONObject moviesData = new JSONObject(json);
        
        JSONArray actorList = moviesData.getJSONArray("results");
        
        // The limit of actor that will be fetched
        int limit;
        
        // Define the limit
        if (max == 0) {
            limit = actorList.length();
        } else {
            limit = max;
        }
        
        for (int i = 0; i < limit; i++) {
            JSONObject actorData = (JSONObject) actorList.get(i);
            
            String name = actorData.getString("name");
            
            int id = actorData.getInt("id");
            
            // Create a new actor and feed it with fetched data
            Actor actor = new Actor();
            actor.setId(id);
            actor.setFullName(name);
            
            actors.add(actor);
        }   
        
//        System.out.println("Actors fetched! \n");
//        
//        for (Actor actor: actors) {
//            System.out.println(String.valueOf(actor.getId()) + " " + actor.getFullName());
//        }
//        
//        System.out.println("\n___________\n");
        
        return true;
    }
    
    /** 
     * Parses a JSON to extract and save contained actor movies data
     * 
     * @param json The String representation of a specific actor movies
     * @param actor The Actor instance for which the json data will be parsed
     * 
     */
    public static void filterActorMovies(String json, Actor actor) {
        JSONObject moviesData = new JSONObject(json);
        
        JSONArray movieList = moviesData.getJSONArray("results");
        
        // Stores the amount of movies of a given genre for the actor
        // Structure <genreId, amountOfMovies>
        LinkedHashMap<String, Integer> genreAmounts = new LinkedHashMap<>();
        
        // Stores the total amount of movies in which the actor is featured
        int totalMovies = 0;
        
        for (int i = 0; i < movieList.length(); i++) {
            JSONObject movie = (JSONObject) movieList.get(i);
            
            // Only handle movies that have at least one genres assigned
            if (movie.getJSONArray("genre_ids").length() > 0) {
                 // Get movie info
                int id = movie.getInt("id");
                String title = movie.getString("title");
                // Genres are stored in an array. The first one is considered the main genre for the movie
                int mainGenreId = movie.getJSONArray("genre_ids").getInt(0);
                String maingenreName = genres.get(mainGenreId);
                
                // Init the movie count to 1 in case it is null / Increment it by one
                if (genreAmounts.get(maingenreName) == null) {
                    genreAmounts.put(maingenreName, 1);
                } else {
                    int currentAmount = genreAmounts.get(maingenreName);
                    genreAmounts.put(maingenreName, currentAmount + 1);
                }
                
                totalMovies++;
            }
        }    
        actor.setTotalPerGenre(genreAmounts);
        actor.setTotalMovies(totalMovies);
    }
    
    /** 
     * Parses multiple JSON to extract and save contained actor movies data
     * 
     * @param json An ArrayList of String representation of specific actor movies
     * @param actor The Actor instance for which the json data will be parsed
     * 
     * @return An integer indicating the index of the next page which contains
     * movie information that needs to be fetched. Returns 0 otherwise.
     */
    public static int filterActorMovies(ArrayList<String> json, Actor actor) {
        // Get the last item in the list
        String lastJson = json.get(json.size() - 1);

        JSONObject moviesData = new JSONObject(lastJson);

        int page = moviesData.getInt("page");
        int totalPages = moviesData.getInt("total_pages");

        System.out.println("page " + page + "/" + totalPages);

        if (page < totalPages) {
            // Return the index of the next page of movie data to fetch
            return page + 1;
        } else {
            // Stores the amount of movies of a given genre for the actor
            // Structure <genreId, amountOfMovies>
            LinkedHashMap<String, Integer> genreAmounts = new LinkedHashMap<>();

            // Stores the total amount of movies in which the actor is featured
            int totalMovies = 0;
            for (String data : json) {
                JSONObject pageData = new JSONObject(data);

                JSONArray movieList = pageData.getJSONArray("results");

                

                for (int i = 0; i < movieList.length(); i++) {
                    JSONObject movie = (JSONObject) movieList.get(i);

                    // Only handle movies that have at least one genres assigned
                    if (movie.getJSONArray("genre_ids").length() > 0) {
                        // Get movie info
                        int id = movie.getInt("id");
                        String title = movie.getString("title");
                        // Genres are stored in an array. The first one is considered the main genre for the movie
                        int mainGenreId = movie.getJSONArray("genre_ids").getInt(0);
                        String maingenreName = genres.get(mainGenreId);

                        // Init the movie count to 1 in case it is null / Increment it by one
                        if (genreAmounts.get(maingenreName) == null) {
                            genreAmounts.put(maingenreName, 1);
                        } else {
                            int currentAmount = genreAmounts.get(maingenreName);
                            genreAmounts.put(maingenreName, currentAmount + 1);
                        }

                        totalMovies++;
                    }
                    // Add movie data to the actor's ref
                    actor.setTotalPerGenre(genreAmounts);
                    actor.setTotalMovies(totalMovies);
                }
                
            }
            System.out.println("recap for " + actor.getFullName());
            System.out.println(actor.getTotalPerGenre());
            System.out.println(actor.getTotalMovies());
        }

        return 0;
    }
    
    /** 
     * Parses a JSON to extract the most popular movie
     * 
     * @param json A String representation of movies from a specific actor
     * @param actor The Actor instance for which the json data will be parsed
     */
    public static void filterMostPopularMovie(String json, Actor actor) {
        JSONObject moviesData = new JSONObject(json);
        
        JSONArray movieList = moviesData.getJSONArray("results");
        
        for (int i = 0; i < movieList.length(); i++) {
            JSONObject movie = (JSONObject) movieList.get(i);
            
            // Only handle movies that have at least one genres assigned
            if (movie.getJSONArray("genre_ids").length() > 0) {
                 // Get movie info
                int id = movie.getInt("id");
                String title = movie.getString("title");
                // Genres are stored in an array. The first one is considered the main genre for the movie
                int mainGenreId = movie.getJSONArray("genre_ids").getInt(0);
                String maingenreName = genres.get(mainGenreId);
                
                actor.setMostPopularGenre(maingenreName);
                System.out.println(actor.getFullName() + "'s MOST POPULAR CAT WAS " + actor.getMostPopularGenre());
                break;
            }
        }
    }
}
