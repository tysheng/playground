package com.tysheng.playground.selecttextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.tysheng.playground.R;


public class SelectTextActivity extends AppCompatActivity {
    private RecyclerView rv;

    Adapter mAdapter;
    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_text);
        rv = (RecyclerView) findViewById(R.id.rv);    ll = (LinearLayout) findViewById(R.id.ll);
        View view = LayoutInflater.from(this).inflate(R.layout.content_shader,ll,false);
        mAdapter = new Adapter(this);
        mAdapter.setRV(rv);
//        setPositionInAdapter();
        popupWindow = new PopupWindow(view, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
//popupWindow.setOutsideTouchable(true);
//        popupWindow.setFocusable(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                clear(null);
                Log.d(TAG, "onDismiss: ");
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);

    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    private static final String TAG = "SelectTextActivity";
    public void show() {

        Log.d(TAG, "show: ");
//        if (popupWindow.isShowing())popupWindow.dismiss();
        rv.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAsDropDown(ll);
            }
        });
    }

    PopupWindow popupWindow;

    public void clear(View view) {
        mAdapter.clear();
    }

    public void previous(View view) {
        move(-1);
    }


    public void next(View view) {
        move(1);
    }

    void move(int num) {
        mAdapter.move(num);
    }

    public void show(View view) {
        show();
    }
}
