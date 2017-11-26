package connorglennontaetraining.at.gmail.com.weekendassignment2.data;

import android.util.Log;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.database.Database;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.database.ADatabase;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.ApiHelper;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.IApiHelper;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.IRequest;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class DataManager implements IDataManager {
    IApiHelper apiHelper;
    ADatabase database;

    public DataManager() {
        apiHelper = new ApiHelper();
        database = Database.getDatabase();
    }

    //TODO look into repeated code.
    @Override
    public Observable<Results> requestClassicResults() {
        Observable<Results> resultsObservable;
        if(hasConnection()){ //Try get from internet if it is available.
            Log.i("DataManager", "Trying network");
            resultsObservable = apiHelper.requestClassicResults();

            //Save results in database.
            saveToDatabase(resultsObservable, ADatabase.GENRE_CLASSIC);

        }else { //Try get from database.
            Log.i("DataManager", "Trying database");
            resultsObservable = database.requestClassicResults();
        }
        if (resultsObservable == null){ //Last resort, try cache.
            Log.i("DataManager", "Trying cache");
            resultsObservable = apiHelper.requestClassicResults();
        }
        return resultsObservable;
    }

    @Override
    public Observable<Results> requestRockResults() {
        Observable<Results> resultsObservable;
        if(hasConnection()){ //Try get from internet if it is available.
            Log.i("DataManager", "Trying network");
            resultsObservable = apiHelper.requestRockResults();

            //Save results in database.
            saveToDatabase(resultsObservable, ADatabase.GENRE_ROCK);

        }else { //Try get from database.
            Log.i("DataManager", "Trying database");
            resultsObservable = database.requestRockResults();
        }
        if (resultsObservable == null){ //Last resort, try cache.
            Log.i("DataManager", "Trying cache");
            resultsObservable = apiHelper.requestRockResults();
        }
        return resultsObservable;
    }

    @Override
    public Observable<Results> requestPopResults() {
        Observable<Results> resultsObservable;
        if(hasConnection()){ //Try get from internet if it is available.
            Log.i("DataManager", "Trying network");
            resultsObservable = apiHelper.requestPopResults();

            //Save results in database.
            saveToDatabase(resultsObservable, ADatabase.GENRE_POP);

        }else { //Try get from database.
            Log.i("DataManager", "Trying database");
            resultsObservable = database.requestPopResults();
        }
        if (resultsObservable == null){ //Last resort, try cache.
            Log.i("DataManager", "Trying cache");
            resultsObservable = apiHelper.requestPopResults();
        }
        return resultsObservable;
    }

    @Override
    public boolean hasConnection() {
        return apiHelper.hasConnection();
    }

    private void saveToDatabase(Observable<Results> resultsObservable, String genre){
        resultsObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Results>() {
                    @Override
                    public void accept(Results results) throws Exception {
                        Log.i("DataManager", "Saving to database");
                        database.saveData(results, genre);
                    }
                });
    }
}
