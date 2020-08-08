package com.example.rxoperatorexercise.operators;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class RepeatOperator {
    public static String TAG="MainActivity.class";

   public void startRx(){
       Observable<Integer> animalsObservable = getAnimalsObservable();

       // observer
       Observer<Integer> animalsObserver = getAnimalsObserver();

       // observer subscribing to observable
       animalsObservable
               .observeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .repeat(2)
               .filter(new Predicate<Integer>() {
                   @Override
                   public boolean test(Integer integer) throws Exception {
                       return integer%2==0;
                   }
               })
               .subscribe(animalsObserver);
   }



    private Observer<Integer> getAnimalsObserver() {

        return new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer s) {
                Log.d(TAG, "Filter Sum Number: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<Integer> getAnimalsObservable() {
        return Observable.fromArray(new Integer[]{1,2,3,4,5,6});
    }
}

//output
