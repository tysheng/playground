package com.tysheng.playground.dagger

import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tysheng.playground.R
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class Dagger2Activity : AppCompatActivity() {
    @Inject
    lateinit var mFooPresenter: FooPresenter

    @Inject
    lateinit var mBarPresenter: BarPresenter

    @Inject
    lateinit var holder: Holder
    lateinit var daggerViewModel: DaggerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)

        AndroidInjection.inject(this)

        daggerViewModel = ViewModelProviders.of(this).get(DaggerViewModel::class.java)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //                        .setAction("Action", null).show();

            holder!!.num = holder!!.num + 1
            daggerViewModel.count += 1
            Timber.d("num is %d  view model count %d", holder!!.num, daggerViewModel.count)
            finish()
        }
        Timber.d("onCreate savedInstanceState == null %b, %d,  view model %d", savedInstanceState == null, holder!!.num, daggerViewModel.count)
        Timber.d("holder instance %d", holder!!.hashCode())

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.d("onSaveInstanceState %d", holder!!.num)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("onRestoreInstanceState %d", holder!!.num)
    }

    override fun finish() {
        super.finish()
        Timber.d("finish")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy %d   view model %d", holder!!.num, daggerViewModel.count)
    }

    override fun onResume() {
        super.onResume()
        // two ways to inject
        mFooPresenter.foo()
        mBarPresenter.bar()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Timber.d("onConfigurationChanged")
    }
}
