package com.example.rxoperatorexercise.operators;

import com.example.rxoperatorexercise.contract.RxContract;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*take(n) acts exactly opposite to skip. It takes first N emissions of an Observable.
In the below example, take(4) takes first 4 emissions i.e 1, 2, 3, 4 and skips the remaining.

TakeLast will take last 4 emit data like 6,7,8,9
*/

public class TakeOperator implements RxContract<Integer> {
    @Override
    public void startRX() {
        System.out.println("************************Take *************************");
    getObservable().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(4)
            .subscribeWith(getObserver());

    System.out.println("************************Take Last*************************");
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .takeLast(4)
                .subscribeWith(getObserver());
    }

    @Override
    public Observable<Integer> getObservable() {
        return Observable.fromArray(1,2,3,4,5,6,7,8,9);
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

//outPur
//1,2,3,4

//take last
//6,7,8,9