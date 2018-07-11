package com.tysheng.playground.edittext;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.widget.TextView;

import com.tysheng.playground.R;

import timber.log.Timber;

public class EditTextActivity extends AppCompatActivity {
    private AdvanceEditText mEdit1;
    private AdvanceEditText mEdit2;
    private AdvanceEditText mEdit3;
    private AdvanceEditText mEdit4;

    private MyEditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        mEdit1 = (AdvanceEditText) findViewById(R.id.edit1);
        mEdit2 = (AdvanceEditText) findViewById(R.id.edit2);
        mEdit3 = (AdvanceEditText) findViewById(R.id.edit3);
        mEdit4 = (AdvanceEditText) findViewById(R.id.edit4);
        et = findViewById(R.id.et);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Timber.d("beforeTextChanged s %s, start %d, count %d, after %d", s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Timber.d("onTextChanged s %s, start %d, before %d, count %d", s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Timber.d("afterTextChanged " + s.toString());
            }
        });
        SpannableString spannableString = new SpannableString("asdasdasdasdasdasd23e2323");
        spannableString.setSpan(new BackgroundColorSpan(Color.RED),2,5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        et.setText(spannableString);

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
