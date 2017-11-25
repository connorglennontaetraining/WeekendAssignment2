package connorglennontaetraining.at.gmail.com.weekendassignment2.view;

import connorglennontaetraining.at.gmail.com.weekendassignment2.data.IDataManager;
import connorglennontaetraining.at.gmail.com.weekendassignment2.data.network.model.Results;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.ui.base.BasePresenter;
import connorglennontaetraining.at.gmail.com.weekendassignment2.mindorks.utils.rx2.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Connor Glennon on 25/11/2017.
 */

public class SongListPresenter <V extends ISongListFragment> extends BasePresenter<V> implements ISongListPresenter<V>{
    public SongListPresenter(IDataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCallGetClassicSongList() {
        getCompositeDisposable()
                .add(getDataManager()
                .requestClassicResults()
                .observeOn(getSchedulerProvider().ui())
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
                                getMvpView().onError(throwable.getMessage());
                            }
                        }
                ));
    }

    @Override
    public void onCallGetRockSongList() {
        getCompositeDisposable()
                .add(getDataManager()
                .requestRockResults()
                .observeOn(getSchedulerProvider().ui())
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
                                getMvpView().onError(throwable.getMessage());
                            }
                        }
                ));
    }

    @Override
    public void onCallGetPopSongList() {
        getCompositeDisposable()
                .add(getDataManager()
                .requestPopResults()
                .observeOn(getSchedulerProvider().ui())
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
                                getMvpView().onError(throwable.getMessage());
                            }
                        }
                ));
    }
}
