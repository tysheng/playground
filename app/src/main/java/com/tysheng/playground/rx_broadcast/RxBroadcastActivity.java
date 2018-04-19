package com.tysheng.playground.rx_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tysheng.playground.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class RxBroadcastActivity extends AppCompatActivity {
    private static final String TAG = "RxBroadcastActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_broadcast);
        RxBroadcastReceiver
                .create(this, new IntentFilter(TAG))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<Intent>() {
                    @Override
                    public void onNext(@NonNull Intent intent) {
                        Timber.d("accept");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dispose();
                    }
                });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sendBroadcast(new Intent(TAG));
            }
        });
    }
}
