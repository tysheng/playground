package com.tysheng.playground.shader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.tysheng.playground.R;

public class ShaderActivity extends AppCompatActivity {

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(ShaderActivity.class.getSimpleName(), "update");
//            textView.update();
//            mHandler.sendEmptyMessageDelayed(0,24);
        }
    };

    ShaderText textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shader);
        textView = (ShaderText) findViewById(R.id.text);

        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.update(200);
            }
        });
//        mHandler.sendEmptyMessage(0);


    }

    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void clear(View view) {
        textView.clear();
    }
}
