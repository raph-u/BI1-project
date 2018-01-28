/*
 * Main program file
 */
package datafetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Raph
 */
public class DataFetcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Fetch all movie genres supported by TMDB
        String movieGenres = RequestHandler.getMovieGenres();
        DataHandler.saveGenres(movieGenres);
        
        // Get the actorLists
        String popularActors = RequestHandler.getPopularActors();
        if (DataHandler.saveActors(popularActors, 20)) {
            // Fetch the list of movie for each actors
            for (Actor actor: DataHandler.actors) {
                System.out.println("Fetching data for " + actor.getFullName());
                String actorsMovie = RequestHandler.getMoviesFeaturing(actor.getId());
                
                // Store the fetched json data into an arrayList in case more needs to be fetched
                ArrayList<String> jsons = new ArrayList<String>();
                jsons.add(actorsMovie);
                
                // Contains the index of the next page that needs to be fetched, if any
                int remainingPages = DataHandler.filterActorMovies(jsons, actor);
                
                // Keep fetching pages until they've all been parsed
                while (remainingPages != 0) {
                    String fetchedJson = RequestHandler.getMoviesFeaturing(actor.getId(), remainingPages);
                    jsons.add(fetchedJson);
                    remainingPages =  DataHandler.filterActorMovies(jsons, actor);
                }
                
                // Get the most popular movies for the current actor
                String mostPopMovies = RequestHandler.getMostPopularMovies(actor.getId());
                DataHandler.filterMostPopularMovie(mostPopMovies, actor);
            }
        }
        
        /* The following bit has been added to prevent a bug from occuring
        when saving CSV files... lines #55 to #62 */
        ArrayList<Actor> actorz = DataHandler.actors;
        ArrayList<String> popGenres = new ArrayList<String>();
        ArrayList<Integer> genreCount = new ArrayList<Integer>();

        for (Actor actor: actorz) {
            popGenres.add(actor.getMostPopularGenre());
            genreCount.add(actor.getTotalPerGenre().get(actor.getMostPopularGenre()));
        }
        
        DataWriter.writePopularToCSV(actorz, popGenres, genreCount, "popular.csv");

        
        DataWriter.writeGenresToCSV(DataHandler.actors, "genres.csv");
        DataWriter.writeTotalsToCSV(DataHandler.actors, "totals.csv");
    }
}
