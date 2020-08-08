package com.example.rxoperatorexercise.operators;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BufferOperator {


public void startRx(){
getIntObservable().subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .buffer(3)
        .subscribe(getIntObserver());

}






    public Observable<Integer> getIntObservable(){
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4,
                5, 6, 7, 8, 9);
        return integerObservable;
    }

    public Observer<List<Integer>> getIntObserver() {
        return new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                System.out.println("Buffer Item:" + integers);
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

//Output
//Buffer Item :[1,2,3]
//Buffer Item :[4,5,6]
//Buffer Item :[7,8,9]
