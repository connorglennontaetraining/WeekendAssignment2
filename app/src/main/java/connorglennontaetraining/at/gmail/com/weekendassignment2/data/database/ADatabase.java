package connorglennontaetraining.at.gmail.com.weekendassignment2.data.database;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.IRequest;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;

/**
 * Created by Connor Glennon on 26/11/2017.
 */

public abstract class ADatabase implements IRequest{
    public static final String GENRE_CLASSIC = "classic";
    public static final String GENRE_ROCK = "rock";
    public static final String GENRE_POP = "pop";

    abstract public void saveData(Results results, String genre);
}
