package com.tysheng.playground.rx_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposables;
import timber.log.Timber;

/**
 * Created by tysheng
 * Date: 17/10/17 7:45 PM.
 * Email: tyshengsx@gmail.com
 */

public final class RxBroadcastReceiver implements ObservableOnSubscribe<Intent> {
    public static Observable<Intent> create(Context context, IntentFilter intentFilter) {
        return Observable.create(new RxBroadcastReceiver(context, intentFilter));
    }

    private final Context context;
    private final IntentFilter intentFilter;

    private RxBroadcastReceiver(Context context, IntentFilter intentFilter) {
        this.context = context;
        this.intentFilter = intentFilter;
    }

    @Override
    public void subscribe(final ObservableEmitter<Intent> emitter) throws Exception {
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Timber.d("onNext");
                emitter.onNext(intent);
            }
        };
        context.registerReceiver(broadcastReceiver, intentFilter);
        emitter.setDisposable(Disposables.fromRunnable(new Runnable() {
            @Override
            public void run() {
                Timber.d("unregisterReceiver");
                context.unregisterReceiver(broadcastReceiver);
            }
        }));
    }
}
