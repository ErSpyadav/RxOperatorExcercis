package com.example.rxoperatorexercise.impoperator;

import com.example.rxoperatorexercise.contract.RxContract;
import com.example.rxoperatorexercise.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ConcateOperator  {

    public void startRX() {
        Observable.concat(getFemaleObservable(),getMaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());

    }


    private Observable<User> getFemaleObservable() {
      String[] female =new String[]{"Riya","Ruchi","Rinki"};
        ArrayList<User> femaleList =new ArrayList<>();
        for(String name :female){
            User user =new User();
            user.setName(name);
            user.setGender("Female");
            femaleList.add(user);
        }

        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for(User user :femaleList){
                    if(!emitter.isDisposed()) {
                        Thread.sleep(1000);
                        emitter.onNext(user);
                    }
                }
                if(!emitter.isDisposed())
                    emitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());

    }

    public Observable<User> getMaleObservable(){
        String[] male = new String[]{"Sarayu","Roushan","Bitu","Ujjawal"};
        ArrayList<User> maleList = new ArrayList<>();
        for(String name : male){
            User user=new User();
            user.setName(name);
            user.setGender("Male");
            maleList.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for(User user:maleList){
                    if(!emitter.isDisposed()) {
                        Thread.sleep(1000);
                        emitter.onNext(user);
                    }
                }
                if(!emitter.isDisposed())
                    emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());
    }


    public Observer<User> getObserver() {
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                System.out.println("User Name:"+user.getName()+", Gender:"+user.getGender());
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
