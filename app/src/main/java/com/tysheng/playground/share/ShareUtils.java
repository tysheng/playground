package com.tysheng.playground.share;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import com.tysheng.playground.BuildConfig;
import com.tysheng.playground.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tysheng
 * Date: 2/10/17 12:23 PM.
 * Email: tyshengsx@gmail.com
 */

public class ShareUtils {
    private static final String TAG = "ShareUtils";
    private static Map<String, Float> sPlatformMap;

    private static Map<String, Float> getPlatformMap() {
        if (sPlatformMap == null) {
            sPlatformMap = new HashMap<>();
            for (Platform platform : Platform.values()) {
                sPlatformMap.put(platform.getActivityName(), platform.getIndex());
            }
        }
        return sPlatformMap;
    }

    // order
    // facebook, facebook lite, FB messenger, FB messeager lite,
    // whatsapp, instragram, line, BBM, twitter, SMS, email, telegram
    public enum Platform {
        Telegram("org.telegram.messenger", "org.telegram.ui.LaunchActivity", 11),
        Gmail("com.google.android.gm", "com.google.android.gm.ComposeActivityGmailExternal", 10),
        Messages("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.conversationlist.ShareIntentActivity", 9),
        Twitter("com.twitter.android", "com.twitter.composer.ComposerShareActivity", 8),
        BBM("com.bbm", "com.bbm.ui.share.SingleEntryShareActivity", 7),
        Line("jp.naver.line.android", "jp.naver.line.android.activity.selectchat.SelectChatActivity", 6),
        Instagram("com.instagram.android", "com.instagram.share.common.ShareHandlerActivity", 5),
        WhatsApp("com.whatsapp", "com.whatsapp.ContactPicker", 4),

        Messenger_Lite("com.facebook.mlite", "com.facebook.mlite.share.view.ShareActivity", 3),
        Messenger("com.facebook.orca", "com.facebook.messenger.intents.ShareIntentHandler", 2),
        Facebook_Lite_2("com.facebook.lite", "com.facebook.lite.composer.activities.ShareIntentAlphabeticalAlias", 1.1f),
        Facebook_Lite("com.facebook.lite", "com.facebook.lite.composer.activities.ShareIntentDefaultAlias", 1),
        Facebook("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", 0),;
        private String packageName;
        private String activityName;
        private float index;

        Platform(String packageName, String activityName, float index) {
            this.packageName = packageName;
            this.activityName = activityName;
            this.index = index;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public float getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
    }

    public static List<ShareAppInfo> getShareAppList(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ShareAppInfo> shareAppInfoList = new ArrayList<>();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("image/*|text/plain|video/*");
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(shareIntent, 0);

        for (ResolveInfo resInfo : resolveInfoList) {
            String packageName = resInfo.activityInfo.packageName;
            String activityName = resInfo.activityInfo.name;

            Log.d(TAG, String.format(Locale.US, "name %s packageName %s", activityName, packageName));
            ShareAppInfo info = new ShareAppInfo();
            info.setTitle(resInfo.activityInfo.loadLabel(packageManager).toString());
            info.setIcon(resInfo.loadIcon(packageManager));
            info.setPackageName(packageName);
            info.setActivityName(activityName);
            info.setIndex(getPlatformMap().get(activityName));
            shareAppInfoList.add(info);

        }

        return sortShareAppList2(shareAppInfoList);
    }

    private static List<ShareAppInfo> sortShareAppList(List<ShareAppInfo> list) {
        List<ShareAppInfo> priorityList = new ArrayList<>();


        Platform[] platforms = Platform.values();
        Arrays.sort(platforms, new Comparator<Platform>() {
            @Override
            public int compare(Platform o1, Platform o2) {
                return o1.getIndex() > o2.getIndex() ? 1 : -1;
            }
        });

        for (Platform platform : platforms) {
            Iterator<ShareAppInfo> iterator = list.iterator();
            while (iterator.hasNext()) {
                ShareAppInfo info = iterator.next();
                if (TextUtils.equals(platform.getActivityName(), info.getActivityName())) {
                    priorityList.add(info);
                    iterator.remove();
                }
            }
        }
        list.addAll(0, priorityList);
        return list;
    }

    private static List<ShareAppInfo> sortShareAppList2(List<ShareAppInfo> list) {
        Collections.sort(list, new Comparator<ShareAppInfo>() {
            @Override
            public int compare(ShareAppInfo o1, ShareAppInfo o2) {
                return o1.getIndex() > o2.getIndex() ? 1 : -1;
            }
        });
        return list;
    }

    public static boolean checkIntentHandle(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            return true;
        } else {
            return false;
        }
    }


    public static Intent handleIntentByPlatform(Context context, ShareAppInfo shareAppInfo, ShareMessage shareMessage) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(shareAppInfo.getPackageName(), shareAppInfo.getActivityName()));
        intent.setAction(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        String activityName = shareAppInfo.getActivityName();
        boolean hasVideo = !TextUtils.isEmpty(shareMessage.getVideoUrl());
        // text and media
        if ( TextUtils.equals(activityName, ShareUtils.Platform.WhatsApp.getActivityName())
                || TextUtils.equals(activityName, ShareUtils.Platform.Telegram.getActivityName())) {
            intent.setType(hasVideo ? "video/*" : "image/*");
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage.getText1());
            intent.putExtra(Intent.EXTRA_STREAM, downloadUrlToUri(context,hasVideo ? shareMessage.getVideoUrl() : shareMessage.getImageUrl()));
        }
        // only text, text can contain url
        else if (TextUtils.equals(activityName, ShareUtils.Platform.Twitter.getActivityName())
                ||TextUtils.equals(activityName, ShareUtils.Platform.Line.getActivityName())
                || TextUtils.equals(activityName, ShareUtils.Platform.BBM.getActivityName())
                || TextUtils.equals(activityName, ShareUtils.Platform.Messenger.getActivityName())
                || TextUtils.equals(activityName, ShareUtils.Platform.Messenger_Lite.getActivityName())) {
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage.getText2());
        }
        // only media
        else if (TextUtils.equals(activityName, ShareUtils.Platform.Instagram.getActivityName())) {
            intent.setType(hasVideo ? "video/*" : "image/*");
            intent.putExtra(Intent.EXTRA_STREAM, downloadUrlToUri(context,hasVideo ? shareMessage.getVideoUrl() : shareMessage.getImageUrl()));
        } else {
            // only url, include other apps
//        if (TextUtils.equals(activityName, ShareUtils.Platform.Facebook.getActivityName())
//                || TextUtils.equals(activityName, ShareUtils.Platform.Facebook_Lite.getActivityName())
//                || TextUtils.equals(activityName, ShareUtils.Platform.Facebook_Lite_2.getActivityName())) {
//
//        }
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, shareMessage.getShareUrl());
        }


        return intent;
    }

    private static Uri downloadUrlToUri(Context context,String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url)
                .build();
        Response response;
        File file = null;
        try {
            response = client.newCall(request).execute();
            BufferedInputStream in = new BufferedInputStream(response.body().byteStream());
            file = new File(context.getFilesDir(), "1.jpg");
            BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(file));

            byte[] bytes = new byte[10240];
            int c;
            while ((c = in.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, c);
            }
            in.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
//        return Uri.fromFile(file);
        return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID,file);
    }

    private static void show(FragmentActivity activity, ShareMessage message) {
        FragmentManager manager = activity.getSupportFragmentManager();
        ShareDialogFragment fragment = ShareDialogFragment.newInstance(message);
        fragment.show(manager, ShareDialogFragment.TAG);
    }

    public static void shareNextPrayerCard(FragmentActivity activity, String dayMonthYear, String location, String shareUrl, String imageUrl) {
        ShareMessage message = new ShareMessage();
        message.setText1(activity.getString(R.string.share_pray_card_1, dayMonthYear, location, shareUrl));
        message.setText2(activity.getString(R.string.share_pray_card_1, dayMonthYear, location, shareUrl));
        message.setShareUrl(shareUrl);
        message.setImageUrl(imageUrl);
        show(activity, message);
    }


    public static void shareVerseDay(FragmentActivity activity, String shareUrl, String imageUrl) {
        ShareMessage message = new ShareMessage();
        message.setShareUrl(shareUrl);
        message.setText1(activity.getString(R.string.share_verse_day_1, shareUrl));
        message.setText2(activity.getString(R.string.share_verse_day_2, imageUrl));
        message.setImageUrl(imageUrl);
        show(activity, message);
    }

    public static void sharePhotoDay(FragmentActivity activity, String description, String shareUrl, String imageUrl) {
        ShareMessage message = new ShareMessage();
        message.setShareUrl(shareUrl);
        message.setText1(activity.getString(R.string.share_photo_day_1, description, shareUrl));
        message.setText2(activity.getString(R.string.share_photo_day_2, description, imageUrl));
        message.setImageUrl(imageUrl);
        show(activity, message);
    }

    public static void shareSingleVerse(FragmentActivity activity, String shareUrl, String imageUrl, String videoUrl) {
        ShareMessage message = new ShareMessage();
        message.setShareUrl(shareUrl);
        message.setText1(activity.getString(R.string.share_single_verse_1, shareUrl));
        message.setText2(activity.getString(R.string.share_single_verse_2, videoUrl));
        message.setImageUrl(imageUrl);
        message.setVideoUrl(videoUrl);
        show(activity, message);
    }

    public static void shareSingleWord(FragmentActivity activity, String shareUrl, String imageUrl, String videoUrl) {
        ShareMessage message = new ShareMessage();
        message.setShareUrl(shareUrl);
        message.setText1(activity.getString(R.string.share_single_word_1, shareUrl));
        message.setText2(activity.getString(R.string.share_single_word_2, videoUrl));
        message.setImageUrl(imageUrl);
        message.setVideoUrl(videoUrl);
        show(activity, message);
    }

}
