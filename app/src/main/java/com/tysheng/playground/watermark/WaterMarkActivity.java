package com.tysheng.playground.watermark;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.tysheng.playground.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.tysheng.playground.watermark.Util.createWatermarkLeft;

public class WaterMarkActivity extends AppCompatActivity {
    private ImageView mImage;
    private ImageView mTrans;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_mark);
        mImage = (ImageView) findViewById(R.id.image);
//        set();

    }


    public void start(Context context) {
        Reader reader;
        try {
            reader = Util.getReaderFromInputStream(getAssets().open("wbw.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Activity> activities = new ArrayList<>();
        a(activities);
    }

    private void a(List<? super AppCompatActivity> a){

    }
    private void set() {
        Observable.just(1)
                .map(new Function<Integer, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Integer integer) throws Exception {
                        Bitmap origin = BitmapFactory.decodeResource(getResources(), R.drawable.big);
                        Bitmap result = createWatermarkLeft(WaterMarkActivity.this, origin, "Muslim Ummah App", R.drawable.btm_icon_ummah);
                        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "watermark.jpg");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        result.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                        fileOutputStream.close();
                        return result;

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        mImage.setImageBitmap(bitmap);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    private static final String TAG = "WaterMarkActivity";


}
