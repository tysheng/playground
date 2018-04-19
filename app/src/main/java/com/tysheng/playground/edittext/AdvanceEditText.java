package com.tysheng.playground.edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tysheng.playground.R;
import com.tysheng.playground.UiUtil;


/**
 * Created by tysheng
 * Date: 13/10/17 10:54 AM.
 * Email: tyshengsx@gmail.com
 */

public class AdvanceEditText extends FrameLayout {
    public AdvanceEditText(@NonNull Context context) {
        this(context, null, 0);
    }

    public AdvanceEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvanceEditText(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private EditText mEditText;
    private TextView mRightTextView;
    private View mBottomLine;
    private String hint;

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AdvanceEditText);
        hint=a.getString(R.styleable.AdvanceEditText_ae_hint);

        a.recycle();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_advance_edittext, this, false);
        mEditText = (EditText) view.findViewById(R.id.editText);
        mRightTextView = (TextView) view.findViewById(R.id.rightTextView);
        mBottomLine = (View) view.findViewById(R.id.bottom_line);
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int color;
                ViewGroup.LayoutParams params = mBottomLine.getLayoutParams();
                if (hasFocus) {
                    params.height = UiUtil.dp2px(2);
                    color = R.color.colorAccent;
                } else {
                    params.height = UiUtil.dp2px(1);
                    color = R.color.grey_quaternary;
                }
                mBottomLine.setLayoutParams(params);
                mBottomLine.setBackgroundColor(ContextCompat.getColor(getContext(), color));
            }
        });
        addView(view);
    }

    public TextView getRightTextView() {
        return mRightTextView;
    }

    public void setHint(String hint) {
        mEditText.setHint(hint);
    }

    public void setRightTextViewVisibility(int visibility) {
        mRightTextView.setVisibility(visibility);
    }

    public void setRightTextViewDrawableId(@DrawableRes int id) {
        mRightTextView.setBackgroundResource(id);
    }

    public void setRightTextViewText(String text) {
        mRightTextView.setText(text);
    }

    public void setRightTextViewColor(int color) {
        mRightTextView.setTextColor(color);
    }

    public void setRightTextViewBackground(Drawable drawable) {
        mRightTextView.setBackground(drawable);
    }
}
