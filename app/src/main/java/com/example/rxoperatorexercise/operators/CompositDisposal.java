package com.example.rxoperatorexercise.operators;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CompositDisposal {


    public void startRx(){
   CompositeDisposable disposal =new CompositeDisposable();
   disposal.add(getObservable()
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .map(new Function<Profile, Profile>() {
               @Override
               public Profile apply(Profile profile) {
                   profile.setName(profile.name.toUpperCase());
                   return profile;
               }
           })
           .subscribeWith(getProfileObserver()));

    }

    public Observable<Profile> getObservable(){
        List<Profile> profiles =getProfile();
        return  Observable.create(new ObservableOnSubscribe<Profile>() {
            @Override
            public void subscribe(ObservableEmitter<Profile> emitter) {
                for (Profile profile :profiles) {
                    if (!emitter.isDisposed())
                        emitter.onNext(profile);
                }

                    if(!emitter.isDisposed()){
                        emitter.onComplete();
                    }
            }
        });
    }

    public DisposableObserver<Profile> getProfileObserver(){
        return new DisposableObserver<Profile>() {
            @Override
            public void onNext(Profile profile) {
                System.out.println(profile);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }





    public List<Profile> getProfile(){
        List<Profile> notes =new ArrayList<>();
        notes.add(new Profile("Sarayu",29,"Yadav"));
        notes.add(new Profile("Sunita",26,"Yadav"));
        notes.add(new Profile("Mukesh",35,"Yadav"));
        notes.add(new Profile("Raju",32,"Yadav"));
        return notes;
    }


    class Profile{
        String name;
        int age;
        String lastName;

        public Profile(String name, int age, String lastName) {
            this.name = name;
            this.age = age;
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @NonNull
        @Override
        public String toString() {
            return "Name:"+name+"  "+lastName+", Age :"+age;
        }
    }
}
