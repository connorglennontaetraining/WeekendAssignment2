package connorglennontaetraining.at.gmail.com.weekendassignment2.view.songlist;

import android.util.Log;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.IDataManager;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.ui.base.BasePresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2.SchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class SongListPresenter <V extends ISongListFragment> extends BasePresenter<V> implements ISongListPresenter<V>{
    public SongListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    private Disposable subscribeTo(Observable<Results> resultsObservable){
        return resultsObservable.observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<Results>() {
                               @Override
                               public void accept(Results results) throws Exception {
                                   getMvpView().onFetchDataSuccess(results.getResults());
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().onFetchDataError(throwable.getMessage());
                                getMvpView().onError(throwable.getMessage());
                            }
                        }
                );
    }

    @Override
    public void onCallGetClassicSongList() {
        Observable<Results> resultsObservable = getDataManager()
                .requestClassicResults();

        getCompositeDisposable()
                .add(subscribeTo(resultsObservable));
    }

    @Override
    public void onCallGetRockSongList() {
        Observable<Results> resultsObservable = getDataManager()
                .requestRockResults();

        getCompositeDisposable()
                .add(subscribeTo(resultsObservable));
    }

    @Override
    public void onCallGetPopSongList() {
        Observable<Results> resultsObservable = getDataManager()
                .requestPopResults();

        getCompositeDisposable()
                .add(subscribeTo(resultsObservable));
    }
}
