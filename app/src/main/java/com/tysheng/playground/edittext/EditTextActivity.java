package com.tysheng.playground.edittext;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tysheng.playground.R;

public class EditTextActivity extends AppCompatActivity {
    private AdvanceEditText mEdit1;
    private AdvanceEditText mEdit2;
    private AdvanceEditText mEdit3;
    private AdvanceEditText mEdit4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        mEdit1 = (AdvanceEditText) findViewById(R.id.edit1);
        mEdit2 = (AdvanceEditText) findViewById(R.id.edit2);
        mEdit3 = (AdvanceEditText) findViewById(R.id.edit3);
        mEdit4 = (AdvanceEditText) findViewById(R.id.edit4);

        mEdit1.setHint("Home address");


        mEdit2.setHint("Mobile");
        mEdit2.setRightTextViewColor(Color.parseColor("#7CC633"));
        mEdit2.setRightTextViewText("GET CODE");
        TextView t2 = mEdit2.getRightTextView();
        t2.setTypeface(t2.getTypeface(), Typeface.BOLD);
        mEdit3.setHint("Current job");
        mEdit3.setRightTextViewDrawableId(R.drawable.ic_icon_md_arrow_down);

        mEdit4.setHint("Verification code");
        mEdit4.setRightTextViewBackground(new ColorDrawable(Color.parseColor("#FFD8D8D8")));
        mEdit4.setRightTextViewText("232222");
        mEdit4.setRightTextViewColor(Color.WHITE);
        TextView t4 = mEdit4.getRightTextView();
        t4.setPadding(18, 9, 18, 9);
    }
}
