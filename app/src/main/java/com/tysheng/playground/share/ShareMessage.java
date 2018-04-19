package com.tysheng.playground.share;

import java.io.Serializable;

/**
 * Created by tysheng
 * Date: 3/10/17 8:05 PM.
 * Email: tyshengsx@gmail.com
 */

public class ShareMessage implements Serializable {

    private String shareUrl;
    private String text1;
    private String text2;
    private String imageUrl;
    private String videoUrl;


    public ShareMessage() {
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
