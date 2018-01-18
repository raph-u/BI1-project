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
        String requestResult = RequestHandler.getMoviesSince("2018");
        System.out.println(requestResult);
    }
}
