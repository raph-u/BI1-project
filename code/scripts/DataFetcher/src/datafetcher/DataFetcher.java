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
        String requestResult = RequestHandler.getMoviesSince("2018");
        
        DataHandler.test(requestResult);
    }
}
