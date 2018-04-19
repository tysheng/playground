package com.tysheng.playground.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tysheng.playground.R;

public class ShareActivity extends AppCompatActivity {
    private static final String TAG = "ShareActivity";
    public static final String TEST_URL = "http://test.muslimummah.co/share/verseoftheday/21-107-id";
    String imageUrl = "https://dt61rxrlsr49.cloudfront.net/verseoftheday/21-107-id.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

    }

    public void share(View view) {
//        ShareMessage message = new ShareMessage();
//        ShareDialogFragment fragment = ShareDialogFragment.newInstance(message);
//        fragment.show(getSupportFragmentManager(), null);

        ShareUtils.shareNextPrayerCard(this, "01-01-2017", "Singapore", TEST_URL, imageUrl);
    }

    public void pop(View view) {
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.show(getSupportFragmentManager(), null);
    }
}
