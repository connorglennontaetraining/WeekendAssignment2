package connorglennontaetraining.at.gmail.com.weekendassignment2.data.network;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IRequest {
    @GET(ApiList.REQUEST_CLASSIC)
    Observable<Results> requestClassicResults();

    @GET(ApiList.REQUEST_ROCK)
    Observable<Results> requestRockResults();

    @GET(ApiList.REQUEST_POP)
    Observable<Results> requestPopResults();
}
