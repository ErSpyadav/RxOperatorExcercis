package com.example.rxoperatorexercise.operators;

import com.example.rxoperatorexercise.contract.RxContract;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
* Skip(n) operator skips the emission of first N items emitted by an Observable.
* Let’s say you have an Observable that emits integers from 1-10
* and if skip(4) is operator is used,
* it skips 1-4 and emits the numbers 5, 6, 7, 8, 9, 10.
* */

public class SkipOperator implements RxContract<Integer> {
    @Override
    public void startRX() {
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(2)
                .subscribeWith(getObserver());
    }

    @Override
    public Observable<Integer> getObservable() {
        return Observable.fromArray(1,2,3,4,5);
    }

    @Override
    public Observer<Integer> getObserver() {
        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
            System.out.println("Next:"+integer);
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
//3,4,5