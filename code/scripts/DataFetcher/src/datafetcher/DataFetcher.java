/*
 * Main program file
 */
package datafetcher;

/**
 *
 * @author Raph
 */
public class DataFetcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String requestResult = RequestHandler.getPopularActors();
//        DataHandler.saveActors(requestResult);
        
        String movieGenres = RequestHandler.getMovieGenres();
        DataHandler.saveGenres(movieGenres);
    }
}
