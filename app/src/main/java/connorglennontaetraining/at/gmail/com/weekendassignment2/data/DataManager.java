package connorglennontaetraining.at.gmail.com.weekendassignment2.data;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.ApiHelper;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.IApiHelper;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import io.reactivex.Observable;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class DataManager implements IDataManager {
    IApiHelper apiHelper;

    public DataManager()
    {
        apiHelper = new ApiHelper();
    }

    @Override
    public Observable<Results> requestClassicResults() {
        return apiHelper.requestClassicResults();
    }

    @Override
    public Observable<Results> requestRockResults() {
        return apiHelper.requestRockResults();
    }

    @Override
    public Observable<Results> requestPopResults() {
        return apiHelper.requestPopResults();
    }
}
