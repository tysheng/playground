package com.tysheng.playground.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tysheng
 * Date: 22/11/17 1:49 PM.
 * Email: tyshengsx@gmail.com
 */

public class Like {

    /**
     * data : {"mylikes_list":[{"card_type":2,"id":24,"desc":"Sultan Omar Ali Saifudding Mosque, Brunei","image_url":"pictureoftheday/Pic000024_720x394.jpg","image_url_small":"pictureoftheday/Pic000024_480x263.jpg","share_url":"https://goo.gl/yLNJ7d","share_url_id":"https://goo.gl/8Jnijd","share_url_en":"https://goo.gl/yLNJ7d","mtime":1511176577705,"subtitle":"Ibrahim (14:7)","redirct_url":"https://app.muslimummah.co/verse_list?verse_number=7&chapter_number=14"},{"card_type":1,"id":100070,"subtitle":"Ibrahim (14:7)","image_url":"verseoftheday/14_7_196_en_720x720.jpg","image_url_small":"verseoftheday/14_7_196_en_480x480.jpg","share_url":"https://goo.gl/tdorLd","redirct_url":"https://app.muslimummah.co/verse_list?verse_number=7&chapter_number=14","mtime":1511176564204},{"card_type":2,"id":99,"desc":"Central Java Grand Mosque, Semarang, Indonesia","image_url":"pictureoftheday/Pic000100_720x480.jpg","image_url_small":"pictureoftheday/Pic000100_480x320.jpg","share_url":"https://goo.gl/3gLyTh","share_url_id":"https://goo.gl/8Dr2ES","share_url_en":"https://goo.gl/3gLyTh","mtime":1510491353703},{"card_type":2,"id":110,"desc":"Demak Grand Mosque, Demak, Indonesia","image_url":"pictureoftheday/Pic000111_720x480.jpg","image_url_small":"pictureoftheday/Pic000111_480x320.jpg","share_url":"https://goo.gl/cZfnVN","share_url_id":"https://goo.gl/9hZUuV","share_url_en":"https://goo.gl/cZfnVN","mtime":1510091323002},{"card_type":1,"id":100018,"subtitle":"Al-'Imran (3:103)","image_url":"verseoftheday/3_103_74_en_720x720.jpg","image_url_small":"verseoftheday/3_103_74_en_480x480.jpg","share_url":"https://goo.gl/os9sCR","redirct_url":"https://app.muslimummah.co/verse_list?verse_number=103&chapter_number=3","mtime":1510091323001},{"card_type":1,"id":100008,"subtitle":"Al-Hijr (15:24)","image_url":"verseoftheday/15_24_208_en_720x720.jpg","image_url_small":"verseoftheday/15_24_208_en_480x480.jpg","share_url":"https://goo.gl/fb5dMa","redirct_url":"https://app.muslimummah.co/verse_list?verse_number=24&chapter_number=15","mtime":1510091323000}]}
     * resp_meta : {"msg":"ok"}
     */

    @SerializedName("data")
    private DataBean data;
    @SerializedName("resp_meta")
    private RespMetaBean respMeta;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public RespMetaBean getRespMeta() {
        return respMeta;
    }

    public void setRespMeta(RespMetaBean respMeta) {
        this.respMeta = respMeta;
    }

    public static class DataBean {
        @SerializedName("mylikes_list")
        private List<MylikesListBean> mylikesList;

        public List<MylikesListBean> getMylikesList() {
            return mylikesList;
        }

        public void setMylikesList(List<MylikesListBean> mylikesList) {
            this.mylikesList = mylikesList;
        }

        public static class MylikesListBean {
            /**
             * card_type : 2
             * id : 24
             * desc : Sultan Omar Ali Saifudding Mosque, Brunei
             * image_url : pictureoftheday/Pic000024_720x394.jpg
             * image_url_small : pictureoftheday/Pic000024_480x263.jpg
             * share_url : https://goo.gl/yLNJ7d
             * share_url_id : https://goo.gl/8Jnijd
             * share_url_en : https://goo.gl/yLNJ7d
             * mtime : 1511176577705
             * subtitle : Ibrahim (14:7)
             * redirct_url : https://app.muslimummah.co/verse_list?verse_number=7&chapter_number=14
             */

            @SerializedName("card_type")
            private int cardType;
            @SerializedName("id")
            private int id;
            @SerializedName("desc")
            private String desc;
            @SerializedName("image_url")
            private String imageUrl;
            @SerializedName("image_url_small")
            private String imageUrlSmall;
            @SerializedName("share_url")
            private String shareUrl;
            @SerializedName("share_url_id")
            private String shareUrlId;
            @SerializedName("share_url_en")
            private String shareUrlEn;
            @SerializedName("mtime")
            private long mtime;
            @SerializedName("subtitle")
            private String subtitle;
            @SerializedName("redirct_url")
            private String redirctUrl;

            public int getCardType() {
                return cardType;
            }

            public void setCardType(int cardType) {
                this.cardType = cardType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getImageUrlSmall() {
                return imageUrlSmall;
            }

            public void setImageUrlSmall(String imageUrlSmall) {
                this.imageUrlSmall = imageUrlSmall;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getShareUrlId() {
                return shareUrlId;
            }

            public void setShareUrlId(String shareUrlId) {
                this.shareUrlId = shareUrlId;
            }

            public String getShareUrlEn() {
                return shareUrlEn;
            }

            public void setShareUrlEn(String shareUrlEn) {
                this.shareUrlEn = shareUrlEn;
            }

            public long getMtime() {
                return mtime;
            }

            public void setMtime(long mtime) {
                this.mtime = mtime;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getRedirctUrl() {
                return redirctUrl;
            }

            public void setRedirctUrl(String redirctUrl) {
                this.redirctUrl = redirctUrl;
            }
        }
    }

    public static class RespMetaBean {
        /**
         * msg : ok
         */

        @SerializedName("msg")
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
