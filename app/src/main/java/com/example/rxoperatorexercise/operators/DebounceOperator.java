package com.example.rxoperatorexercise.operators;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DebounceOperator {


public void startRx(){
getIntObservable().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .debounce(3000, TimeUnit.MILLISECONDS)
        .subscribe(getIntObserver());

}






    public Observable<Integer> getIntObservable(){
        Observable<Integer> integerObservable = Observable.fromArray(1, 2, 3, 4,
                5, 6, 7, 8, 9);
        return integerObservable;
    }

    public Observer<Integer> getIntObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("Debounce Item:" + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }


}
