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
//        String requestResult = RequestHandler.getMoviesSince("2018");
        String requestResult = RequestHandler.getPopularActors();
        
        DataHandler.saveActors(requestResult);
        
//        System.out.println(requestResult);
        
//        DataHandler.test(requestResult);
    }
}
