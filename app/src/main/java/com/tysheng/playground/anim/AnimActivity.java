package com.tysheng.playground.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tysheng.playground.R;
import com.tysheng.playground.UiUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class AnimActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView fire;
    private ImageView rocket;
    private ProgressBar progress;

    private int time;
    Disposable disposable;
    private ProgressBar progressBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        textView = findViewById(R.id.textView);

        fire = findViewById(R.id.fire);
        rocket = findViewById(R.id.rocket);
        progress = findViewById(R.id.progress);
        progressBack = findViewById(R.id.progressBack);

        progress.setMax(120);
        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
//                        progressBack.setProgress(++time);
                        
                        ViewGroup.LayoutParams layoutParams = progress.getLayoutParams();
                        layoutParams.width = (int) (UiUtil.getScreenWidth() * 1f * ++time / 120);
                        progress.setLayoutParams(layoutParams);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    public void start(View view) {
//        AnimationUtils.AnimationParams animationParams = new AnimationUtils.AnimationParams();
//        animationParams.setDuration(2000L);
//        animationParams.setFloats(new float[]{0, 1f});
//        animationParams.setInterpolator(new LinearInterpolator());
//
////        AnimationUtils.alpha(textView, animationParams, 10);
//        animationParams.setRepeatMode(ValueAnimator.INFINITE);
//        AnimationUtils.rocket(rocket, fire, animationParams);
    }
}
