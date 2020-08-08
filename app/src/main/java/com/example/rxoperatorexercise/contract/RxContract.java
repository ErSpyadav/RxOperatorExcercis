package com.example.rxoperatorexercise.contract;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface RxContract<T> {

    public void startRX();
    public Observable<T> getObservable();
    public Observer<T> getObserver();

}
