package com.example.rxoperatorexercise.impoperator;

import android.util.Log;

import com.example.rxoperatorexercise.model.User;

import java.util.ArrayList;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.example.rxoperatorexercise.MainActivity.TAG;

public class FlatMapOperator {


    public void startRX(){
        getUserObservale().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<User, Observable<User>>() {
                    @Override
                    public Observable<User> apply(User user) throws Exception {
                        return getAddressObservale(user);
                    }
                })
                .subscribe(getObserver());
    }


    public Observable<User> getUserObservale(){
        String[] names=new String[]{"Sarayu","Ashok","Krishna"};
        ArrayList<User> userList = new ArrayList();
        for(String name :names){
            User user =new User();
            user.setName(name);
            user.setGender("Male");
            userList.add(user);
        }
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for(User user:userList)
                    if(!emitter.isDisposed()){
                        Thread.sleep(5000);
                        emitter.onNext(user);
                    }

                if(!emitter.isDisposed())
                    emitter.onComplete();
            }
        });

    }

    public Observable<User> getAddressObservale(User user){
        String[] address=new String[]{"106,Kariwadih,Jharkhand","107,Township,jharkhand","108,Bhawariya,jharkhand"};

        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                    if(!emitter.isDisposed()){
                        user.setAddress(address[new Random().nextInt(2) + 0]);


                        // Generate network latency of random duration
                        int sleepTime = new Random().nextInt(1000) + 500;

                        Thread.sleep(sleepTime);
                        emitter.onNext(user);
                        emitter.onComplete();
                    }

                if(!emitter.isDisposed())
                    emitter.onComplete();
            }
        });

    }

    public Observer<User> getObserver(){
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(User user) {
                System.out.println(user.getName()+","+user.getGender()+","+user.getAddress());
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
