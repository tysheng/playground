package com.tysheng.playground.share;

/**
 * Created by tysheng
 * Date: 11/10/17 5:40 PM.
 * Email: tyshengsx@gmail.com
 */

public interface IPlatform {
    String shareType();

    String text1();

    String text2();

    String smallImageUrl();//16:9

    String bigImageUrl();

    String videoUrl();

    String shareUrl();

    boolean addWatermark();
}
