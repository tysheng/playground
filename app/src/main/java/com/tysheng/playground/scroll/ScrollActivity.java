package com.tysheng.playground.scroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tysheng.playground.R;

public class ScrollActivity extends AppCompatActivity {
    private ScrollView mSv;
    private TextView mTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        mSv = (ScrollView) findViewById(R.id.sv);
        mTv = (TextView) findViewById(R.id.tv);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            stringBuilder.append(String.valueOf(i)).append("\n");
        }
        mTv.setText(stringBuilder.toString());

        Log.d(TAG, "onCreate: " + mTv.getMeasuredWidth() + "  " + mTv.getWidth());
        Log.d(TAG, "onCreate: " + mTv.getMeasuredHeight() + "  " + mTv.getHeight());
        Log.d(TAG, "onCreate: " + mTv.getLineHeight() + " " + mTv.getLineSpacingExtra());
        mTv.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onCreate: " + mTv.getMeasuredWidth() + "  " + mTv.getWidth());
                Log.d(TAG, "onCreate: " + mTv.getMeasuredHeight() + "  " + mTv.getHeight());
                Log.d(TAG, "onCreate: " + mTv.getLineHeight() + " " + mTv.getLineSpacingExtra());
            }
        });
    }

    private static final String TAG = "ScrollActivity";

    public void positive(View view) {
        final int x = 100;
        mSv.scrollBy(0, x);
    }

    public void negative(View view) {
        final int x = -100;
        mSv.scrollBy(0, x);
    }
}
