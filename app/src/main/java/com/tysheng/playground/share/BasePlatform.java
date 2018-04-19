package com.tysheng.playground.share;

/**
 * Created by tysheng
 * Date: 11/10/17 5:40 PM.
 * Email: tyshengsx@gmail.com
 */

public abstract class BasePlatform implements IPlatform {
    public static final String TEXT = "text/plain";
    public static final String IMAGE = "image/*";
    public static final String VIDEO = "video/*";

    @Override
    public String text1() {
        return null;
    }

    @Override
    public String text2() {
        return null;
    }

    @Override
    public String smallImageUrl() {
        return null;
    }

    @Override
    public String bigImageUrl() {
        return null;
    }

    @Override
    public String videoUrl() {
        return null;
    }

    @Override
    public boolean addWatermark() {
        return false;
    }
}
