package com.example.rxoperatorexercise.impoperator;

import com.example.rxoperatorexercise.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MergeOperator {

    public void startRX(){
        Observable.merge(getFemaleObservable(),getMaleObservable())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver());

    }

    public Observable<User> getFemaleObservable(){
        String[] names=new String[]{"Manju","Riya","Shruti"};
        ArrayList<User> females =new ArrayList<>();
        for(String name : names) {
            User user =new User();
            user.setName(name);
            user.setGender("Female");
            females.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for(User user:females)
                    if(!emitter.isDisposed()){
                        Thread.sleep(3000);
                        emitter.onNext(user);
                    }
                if(!emitter.isDisposed())
                    emitter.isDisposed();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Observable<User> getMaleObservable(){
        String[] names=new String[]{"Ashok","Pradeep","Bablu"};
        ArrayList<User> males =new ArrayList<>();
        for(String name : names) {
            User user =new User();
            user.setName(name);
            user.setGender("Male");
            males.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for(User user:males)
                    if(!emitter.isDisposed()){
                        Thread.sleep(3000);
                        emitter.onNext(user);
                    }
                if(!emitter.isDisposed())
                    emitter.isDisposed();
            }
        }).subscribeOn(Schedulers.io());



    }

    public Observer<User> getObserver(){
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                System.out.println(user.getName()+","+user.getGender());
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
