package com.tysheng.playground.ellipsize

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tysheng.playground.EllipsizeTextView
import com.tysheng.playground.R
import com.tysheng.playground.TestConstants
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.io.InputStream

class EllipsizeActivity : AppCompatActivity() {
    lateinit var iv: ImageView
    lateinit var iv2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ellipsize)

        val tv1 = findViewById<EllipsizeTextView>(R.id.tv1)
        tv1.setText("sad asd asd asd asd asd as sd sad s ds dsd s dsd as ds asd as dsa s ds sad asd sd ", "(123)")
        val tv2 = findViewById<EllipsizeTextView>(R.id.tv2)
        tv2.setText("sad asd as", "(123)")

        iv = findViewById<ImageView>(R.id.imageView)
        iv2 = findViewById(R.id.imageView2)
//        val ins = assets.open("big.jpg")


        val request = Request.Builder().url(TestConstants.AVATAR1).build()

        Observable.just(request)
                .map {
                    1/0
                    OkHttpClient().newCall(it).execute()
                }
                .doOnError {
                    Timber.d("thread1 "+Thread.currentThread().name)
                }
                .map {
                    return@map it?.body()?.byteStream()
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Timber.d("thread2 "+Thread.currentThread().name)
                }
                .subscribe {
                    if (it != null) {
                        load(it)
                    }
                }


        Glide.with(iv2)
                .load("file:///android_asset/big.jpg")
                .into(iv2)


    }

    private fun load(ins: InputStream) {
        Glide.with(iv)
                .load(ins)
                .apply(RequestOptions.centerCropTransform())
                .into(iv)
    }
}
