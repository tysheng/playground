package com.tysheng.playground;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.annotations.NonNull;
import timber.log.Timber;

/**
 * Created by tysheng
 * Date: 31/10/17 7:54 PM.
 * Email: tyshengsx@gmail.com
 */

public class Utils {


    public void log(){
        Timber.d(this.hashCode()+"");
    }

    public static String md5Suffix(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-");
        String md5 = md5(str);
        Timber.d("md5 " + md5);
        stringBuilder.append(md5.substring(md5.length() - 6, md5.length()));
        return stringBuilder.toString();
    }

    public static String md5(@NonNull String string) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest((string).getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
