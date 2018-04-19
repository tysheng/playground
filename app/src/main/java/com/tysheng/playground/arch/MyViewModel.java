package com.tysheng.playground.arch;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tysheng
 * Date: 31/1/18 11:23 AM.
 * Email: tyshengsx@gmail.com
 */

public class MyViewModel extends AndroidViewModel {
    private MutableLiveData<List<User>> users;

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();

        }
        return users;
    }

    private void loadUsers() {
        Observable
                .create((ObservableOnSubscribe<List<User>>) e -> {
                    List<User> list = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        User user = new User();
                        user.id = i;
                        user.name = String.valueOf(UUID.randomUUID());
                        list.add(user);

                    }
                    e.onNext(list);
                    e.onComplete();
                })
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(list -> users.postValue(list));
        // Do an asyncronous operation to fetch users.
    }

    @Override
    protected void onCleared() {
        super.onCleared();

    }
}
