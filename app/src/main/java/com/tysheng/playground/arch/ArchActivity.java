package com.tysheng.playground.arch;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tysheng.playground.R;

import timber.log.Timber;

public class ArchActivity extends AppCompatActivity {
    private ListView listView;
    ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arch);
        listView = findViewById(R.id.listView);

        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        adapter = new ArrayAdapter<>(ArchActivity.this, R.layout.item_content, R.id.text);
        listView.setAdapter(adapter);
        myViewModel.getUsers().observe(this, users -> {
            Timber.d("onChanged ");
            adapter.addAll(users);
        });
    }
}
