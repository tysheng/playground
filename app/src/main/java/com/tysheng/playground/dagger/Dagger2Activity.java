package com.tysheng.playground.dagger;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tysheng.playground.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class Dagger2Activity extends AppCompatActivity {
    @Inject
    FooPresenter mFooPresenter;

    @Inject
    BarPresenter mBarPresenter;

    @Inject
    Holder holder;
    DaggerViewModel daggerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        AndroidInjection.inject(this);

        daggerViewModel = ViewModelProviders.of(this).get(DaggerViewModel.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                holder.num = holder.num + 1;
                daggerViewModel.count += 1;
                Timber.d("num is %d  view model count %d", holder.num, daggerViewModel.count);
            }
        });
        Timber.d("onCreate savedInstanceState == null %b, %d,  view model %d", savedInstanceState == null, holder.num, daggerViewModel.count);
        Timber.d("holder instance %d", holder.hashCode());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.d("onSaveInstanceState %d", holder.num);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Timber.d("onRestoreInstanceState %d", holder.num);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy %d   view model %d", holder.num, daggerViewModel.count);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // two ways to inject
//        mFooPresenter.foo();
//        mBarPresenter.bar();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Timber.d("onConfigurationChanged");
    }
}
