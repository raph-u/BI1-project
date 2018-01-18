/*
 * Main program file
 */
package datafetcher;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raph
 */
public class DataFetcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           String requestResult = RequestHandler.executePost("https://api.themoviedb.org/3/discover/movie");
           System.out.println(requestResult);
        } catch (IOException ex) {
            Logger.getLogger(DataFetcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
