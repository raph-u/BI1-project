/*
 * This class holds all the data relative to an actor
 * i.e. the number movies in which he/she was featured, the genre of his/her
 * most popular movie aswell as the number of movies for a given genre
 */
package datafetcher;

import java.util.LinkedHashMap;

/**
 *
 * @author Raph
 */
public class Actor {
    private int id;
    private String fullName;
    // Stores the total amount of movies in which the actor was featured for a given genre
    // Structure is as follows: <genreName, amount>
    private LinkedHashMap<String, Integer> totalPerGenre = new LinkedHashMap<>();
    private int totalMovies; // Number of movies in which the actor was featured
    private String mostPopularGenre;

    public Actor() {
    }

    public int getId() {
        return id;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }
    
    public LinkedHashMap<String, Integer> getTotalPerGenre() {
        return totalPerGenre;
    }
    
    public int getTotalMovies() {
        return totalMovies;
    }
    
    public String getMostPopularGenre() {
        return mostPopularGenre;
    }
    
    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTotalPerGenre(LinkedHashMap<String, Integer> totalPerGenre) {
        this.totalPerGenre = totalPerGenre;
    }

    public void setTotalMovies(int totalMovies) {
        this.totalMovies = totalMovies;
    }

    public void setMostPopularGenre(String mostPopularGenre) {
        this.mostPopularGenre = mostPopularGenre;
    }
}
