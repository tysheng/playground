package com.tysheng.playground.selecttextview;

/**
 * Created by tysheng
 * Date: 22/9/17 12:40 PM.
 * Email: tyshengsx@gmail.com
 */

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class LongClickLinkMovementMethod extends LinkMovementMethod {

    private Long lastClickTime = 0l;
    private int lastX = 0;
    private int lastY = 0;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer,
                                MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            lastX = x;
            lastY = y;
            int deltaX = Math.abs(x - lastX);
            int deltaY = Math.abs(y - lastY);

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            LongClickableSpan[] link = buffer.getSpans(off, off, LongClickableSpan.class);

            if (link.length != 0) {
                if (action == MotionEvent.ACTION_UP) {
                    if (System.currentTimeMillis() - lastClickTime < 500)
                        link[0].onClick(widget);
                    else if (deltaX < 10 && deltaY < 10)
                        link[0].onLongClick(widget);
                } else if (action == MotionEvent.ACTION_DOWN) {
                    Selection.setSelection(buffer,
                            buffer.getSpanStart(link[0]),
                            buffer.getSpanEnd(link[0]));
                    lastClickTime = System.currentTimeMillis();
                }


                // long touch
//                if (action == MotionEvent.ACTION_UP) {
//                    consumeEvent(link, widget, deltaX, deltaY);
//                    isConsumed = false;
//                    isFirstDown = false;
//                } else if (action == MotionEvent.ACTION_DOWN) {
//                    if (!isFirstDown) {
//                        isFirstDown = true;
//                        Selection.setSelection(buffer,
//                                buffer.getSpanStart(link[0]),
//                                buffer.getSpanEnd(link[0]));
//                        lastClickTime = System.currentTimeMillis();
//                    } else {
//                        consumeEvent(link, widget, deltaX, deltaY);
//                    }
//
//                }

                Log.d("sty", "action is "+action);
                return true;
            }
        }

        return super.onTouchEvent(widget, buffer, event);
    }

    private boolean isFirstDown = false;
    private boolean isConsumed = false;

    private void consumeEvent(LongClickableSpan[] link, TextView widget, int deltaX, int deltaY) {
        if (System.currentTimeMillis() - lastClickTime > 500 && deltaX < 10 && deltaY < 10 && !isConsumed) {
            isConsumed = true;
            link[0].onLongClick(widget);

        }
    }

    public static MovementMethod getInstance() {
        if (sInstance == null)
            sInstance = new LongClickLinkMovementMethod();

        return sInstance;
    }

    private static LongClickLinkMovementMethod sInstance;
}

