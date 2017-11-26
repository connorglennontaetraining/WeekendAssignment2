package connorglennontaetraining.at.gmail.com.weekendassignment2.data.database.model;

import io.realm.RealmObject;

/**
 * Created by Connor Glennon on 26/11/2017.
 */

public class RealmSongResults extends RealmObject{
    String results;
    String genre;

    public RealmSongResults(){}

    public RealmSongResults(String results, String genre) {
        this.results = results;
        this.genre = genre;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
