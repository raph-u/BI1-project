/*
 * This class is responsible for the data manipulation
 */
package datafetcher;

import org.json.*;

/**
 *
 * @author Raph
 */
public class DataHandler {
    
// Test method
    public static void test(String json) {
        JSONObject moviesData = new JSONObject(json);
        
        JSONArray movieList = moviesData.getJSONArray("results");
        
        for (int i = 0; i < movieList.length(); i++) {
            JSONObject movie = (JSONObject) movieList.get(i);
            
            // Only handle movies that have one or several genres assigned
            if (movie.getJSONArray("genre_ids").length() > 0) {
                 // Get movie info
                int id = movie.getInt("id");
                String title = movie.getString("title");
                // Genres are stored in an array. The first one is considered the main genre for the movie
                int mainGenre = movie.getJSONArray("genre_ids").getInt(0);

                System.out.println(id + " " + title + " " + mainGenre);
            }
        }    
        System.out.println("\n___________\n");
    }
}
