package com.example.rxoperatorexercise.operators;

import com.example.rxoperatorexercise.contract.RxContract;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*Distinct operator filters out items emitted by an Observable by avoiding duplicate items in the list.
Below, we have list of integers with duplicates. Using distinct(), emission of duplicates can be avoided.*/
public class DistinctOperator implements RxContract<Integer> {
    @Override
    public void startRX() {
 getObservable().subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .distinct()
         .subscribeWith(getObserver());
    }

    @Override
    public Observable<Integer> getObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for(int i=1;i<=10;i++){
                    emitter.onNext(i);
                }
            }
        });
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
