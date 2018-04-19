package com.tysheng.playground.selecttextview;

/**
 * Created by tysheng
 * Date: 22/9/17 12:41 PM.
 * Email: tyshengsx@gmail.com
 */

import android.text.style.ClickableSpan;
import android.view.View;

public abstract class LongClickableSpan extends ClickableSpan {

    abstract public void onLongClick(View view);

}
