package com.tysheng.playground.dagger;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

/**
 * Created by tysheng
 * Date: 19/3/18 4:29 PM.
 * Email: tyshengsx@gmail.com
 */
public class DaggerViewModel extends AndroidViewModel {

    public int count;

    public DaggerViewModel(@NonNull Application application) {
        super(application);
    }
}
