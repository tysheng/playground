package com.tysheng.playground.share;

/**
 * Created by tysheng
 * Date: 11/10/17 5:47 PM.
 * Email: tyshengsx@gmail.com
 */

public class FacebookPlatform extends BasePlatform {
    @Override
    public String shareType() {
        return IMAGE;
    }

    @Override
    public String shareUrl() {
        return null;
    }
}
