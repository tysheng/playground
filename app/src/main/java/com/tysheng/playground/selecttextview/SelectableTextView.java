package com.tysheng.playground.selecttextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by tysheng
 * Date: 22/9/17 10:28 AM.
 * Email: tyshengsx@gmail.com
 */

public class SelectableTextView extends android.support.v7.widget.AppCompatTextView {

    private List<Info> mInfoList = new ArrayList<>();
    private CharacterStyle mCurrentSpan;
    private Spannable mContent;
    private Info mCurrentInfo;
    private int mPositionInAdapter;

    public SelectableTextView(Context context) {
        super(context);

    }

    public SelectableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public SelectableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setPositionInAdapter(int positionInAdapter) {
        setPositionInAdapter(this);
        this.mPositionInAdapter = positionInAdapter;
    }

    private void setPositionInAdapter(TextView definitionView) {
        String definition =
                "الَّذينَ يُؤمِنونَ بِالغَيبِ وَيُقيمونَ  الصَّلاةَ وَمِمّا رَزَقناهُم يُنفِقونَ";

        definitionView.setMovementMethod(LongClickLinkMovementMethod.getInstance());
        definitionView.setText(definition, TextView.BufferType.SPANNABLE);
        mContent = (Spannable) definitionView.getText();
        BreakIterator iterator = BreakIterator.getWordInstance(Locale.US);
        iterator.setText(definition);
        int start = iterator.first();
        int position = 0;
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator
                .next()) {
            String possibleWord = definition.substring(start, end);

            if (Character.isLetterOrDigit(possibleWord.charAt(0))) {

                Info info = new Info();
                info.position = position;
                info.startEnd = new int[]{start, end};
                info.word = possibleWord;
                mInfoList.add(position, info);
                mContent.setSpan(getClickableSpan(info), start, end,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                position++;

            }
        }
        definitionView.setHighlightColor(Color.TRANSPARENT);
    }

    private ClickableSpan getClickableSpan(Info info) {
        return new WordSpan(info);
    }


    public void reset() {
        mInfoList.clear();
        clear();
        mCurrentInfo = null;
        mCurrentSpan = null;
    }

    public void clear() {
        if (mCurrentSpan != null) {
            mContent.removeSpan(mCurrentSpan);
        }
    }

    public void move(int num) {
        if (mCurrentInfo != null) {
            clear();
            int pos = mCurrentInfo.position + num;
            if (pos >= 0 && pos < mInfoList.size()) {
                Info info = mInfoList.get(pos);
                CharacterStyle span = new BackgroundColorSpan(Color.BLUE);
                mContent.setSpan(span, info.startEnd[0], info.startEnd[1], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mCurrentInfo = info;
                mCurrentSpan = span;
            }

        }
    }

    private class WordSpan extends LongClickableSpan {

        private Info mInfo;

        WordSpan(Info info) {
            mInfo = info;
        }

        @Override
        public void onClick(View widget) {

        }

        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(Color.BLACK);

        }

        @Override
        public void onLongClick(View view) {
            mCurrentInfo = mInfo;
            if (mLongClickListener != null) {
                mLongClickListener.onLongClick(mPositionInAdapter);
            }
            move(0);
        }
    }

    private LongClickListener mLongClickListener;

    public void setLongClickListener(LongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    public interface LongClickListener {
        void onLongClick(int adapterPosition);
    }

}
