package com.example.rxoperatorexercise.operators;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class JustOperator {
    public static String TAG="MainActivity.class";

   public void startRx(){
       System.out.println("***********Just Operator**************");
       // observable
       Observable<String> animalsObservable = getAnimalsObservable();

       // observer
       Observer<String> animalsObserver = getAnimalsObserver();

       // observer subscribing to observable
       animalsObservable
               .observeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe(animalsObserver);
   }



    private Observer<String> getAnimalsObserver() {

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Just: " + s);
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

    private Observable<String> getAnimalsObservable() {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }
}
