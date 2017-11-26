package connorglennontaetraining.at.gmail.com.weekendassignment2.data.database;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.database.model.RealmSongResults;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by Connor Glennon on 26/11/2017.
 */

public class Database extends ADatabase {

    static Database mDatabase;

    public static Database getDatabase() {
        if (mDatabase == null){
            synchronized (Database.class){
                mDatabase = new Database();
            }
        }
        return mDatabase;
    }
    Realm mRealm;

    private Database(){
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public Observable<Results> requestClassicResults() {
        Log.i("Database", "RequestClassicResults");
        return packageData(loadData(ADatabase.GENRE_CLASSIC));
    }

    @Override
    public Observable<Results> requestRockResults() {
        Log.i("Database", "RequestRockResults");
        return packageData(loadData(ADatabase.GENRE_ROCK));
    }

    @Override
    public Observable<Results> requestPopResults() {
        Log.i("Database", "RequestPopResults");
        return packageData(loadData(ADatabase.GENRE_POP));
    }

    private Observable<Results> packageData(Results results){
        if(results == null){
            return null;
        } else {
            List<Results> resultsList = new ArrayList<>();
            resultsList.add(results);
            return Observable.fromIterable(resultsList);
        }
    }

    public Results loadData(String genre){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        RealmSongResults realmSongResults = mRealm.where(RealmSongResults.class)
                .contains("genre", genre).findFirst();
        if(realmSongResults != null){
            return gson.fromJson(realmSongResults.getResults(), Results.class);
        } else {
            return null;
        }
    }

    public void saveData(Results results, String genre){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String resultsJson = gson.toJson(results);
        Log.i("JSON", resultsJson);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //TODO look at copyorupdate.
                mRealm.copyToRealm(new RealmSongResults(resultsJson, genre));
            }
        });
    }
}
