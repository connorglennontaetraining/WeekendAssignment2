package connorglennontaetraining.at.gmail.com.weekendassignment2.data.network;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import io.reactivex.Observable;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class ApiHelper implements IApiHelper {

    IRequest request;

    public ApiHelper() {
        request = ServerConnection.getServerConnection();
    }

    @Override
    public Observable<Results> requestClassicResults() {
        return request.requestClassicResults();
    }

    @Override
    public Observable<Results> requestRockResults() {
        return request.requestRockResults();
    }

    @Override
    public Observable<Results> requestPopResults() {
        return request.requestPopResults();
    }
}
