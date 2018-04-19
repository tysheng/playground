package com.tysheng.playground.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by tysheng
 * Date: 12/10/17 10:11 AM.
 * Email: tyshengsx@gmail.com
 */

class Util {
    static Reader getReaderFromInputStream(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024 * 1024);
        return new InputStreamReader(bufferedInputStream, "UTF-8");
    }
    public static Bitmap createWatermarkLeft(Context context, Bitmap bitmap, String markText, int markBitmapId) {

        // 当水印文字与水印图片都没有的时候，返回原图
        if (TextUtils.isEmpty(markText) && markBitmapId == 0) {
            return bitmap;
        }

        // 获取图片的宽高
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        // 创建一个和图片一样大的背景图
        Bitmap bmp = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        // 画背景图
        canvas.drawBitmap(bitmap, 0, 0, null);
        //-------------开始绘制文字-------------------------------

        // 文字开始的坐标,默认为左上角
        float textX = 0;
        float textY = 0;
//------------开始绘制图片-------------------------
        if (markBitmapId != 0) {
            // 载入水印图片
            Bitmap markBitmap = BitmapFactory.decodeResource(context.getResources(), markBitmapId);

//            // 如果图片的大小小于水印的3倍，就不添加水印
//            if (markBitmap.getWidth() > bitmapWidth / 3 || markBitmap.getHeight() > bitmapHeight / 3) {
//                return bitmap;
//            }

            int markBitmapWidth = markBitmap.getWidth();
            int markBitmapHeight = markBitmap.getHeight();

            float scale = 0.75f;

            // 图片开始的坐标
            textX = (float) (15);//这里的-10和下面的-20都是微调的结果
            textY =

                    (float) (bitmapHeight - markBitmapHeight + 8);
            Rect rect = new Rect((int) textX, (int) textY, (int) (markBitmapWidth * scale + textX), (int) (markBitmapHeight * scale + textY));
            // 画图
            textX += markBitmapWidth;
            canvas.drawBitmap(markBitmap, null, rect, null);
        }

        if (!TextUtils.isEmpty(markText)) {
            // 创建画笔
            Paint mPaint = new Paint();
            // 文字矩阵区域
            Rect textBounds = new Rect();
            // 获取屏幕的密度，用于设置文本大小
            //float scale = context.getResources().getDisplayMetrics().density;
            // 水印的字体大小
            //mPaint.setTextSize((int) (11 * scale));
            mPaint.setTextSize(40);
            // 文字阴影
            mPaint.setShadowLayer(0.5f, 0f, 1f, Color.BLACK);
            // 抗锯齿
            mPaint.setAntiAlias(true);
            // 水印的区域
            mPaint.getTextBounds(markText, 0, markText.length(), textBounds);
            // 水印的颜色
            mPaint.setColor(Color.WHITE);
//            float extraWidth = 18;
//            float width = mPaint.measureText(markText) + extraWidth;
            float[] extras = new float[]{2, 9};

            // 文字开始的坐标
            textX = textX + extras[0];//这里的-10和下面的+6都是微调的结果
            textY = bitmapHeight - textBounds.height() + extras[1];
            // 画文字
            canvas.drawText(markText, textX, textY, mPaint);
        }


        //保存所有元素
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return bmp;
    }

    /**
     * 添加水印
     *
     * @param context      上下文
     * @param bitmap       原图
     * @param markText     水印文字
     * @param markBitmapId 水印图片
     * @return bitmap      打了水印的图
     */
    public static Bitmap createWatermark(Context context, Bitmap bitmap, String markText, int markBitmapId) {

        // 当水印文字与水印图片都没有的时候，返回原图
        if (TextUtils.isEmpty(markText) && markBitmapId == 0) {
            return bitmap;
        }

        // 获取图片的宽高
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();

        // 创建一个和图片一样大的背景图
        Bitmap bmp = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        // 画背景图
        canvas.drawBitmap(bitmap, 0, 0, null);
        //-------------开始绘制文字-------------------------------

        // 文字开始的坐标,默认为左上角
        float textX = 0;
        float textY = 0;
//------------开始绘制图片-------------------------


        if (!TextUtils.isEmpty(markText)) {
            // 创建画笔
            Paint mPaint = new Paint();
            // 文字矩阵区域
            Rect textBounds = new Rect();
            // 获取屏幕的密度，用于设置文本大小
            //float scale = context.getResources().getDisplayMetrics().density;
            // 水印的字体大小
            //mPaint.setTextSize((int) (11 * scale));
            mPaint.setTextSize(40);
            // 文字阴影
            mPaint.setShadowLayer(0.5f, 0f, 1f, Color.BLACK);
            // 抗锯齿
            mPaint.setAntiAlias(true);
            // 水印的区域
            mPaint.getTextBounds(markText, 0, markText.length(), textBounds);
            // 水印的颜色
            mPaint.setColor(Color.WHITE);
//            float extraWidth = 18;
//            float width = mPaint.measureText(markText) + extraWidth;
            float[] extras = new float[]{18, 8};

            // 文字开始的坐标
            textX = bitmapWidth - textBounds.width() - extras[0];//这里的-10和下面的+6都是微调的结果
            textY = bitmapHeight - textBounds.height() + extras[1];
            // 画文字
            canvas.drawText(markText, textX, textY, mPaint);
        }
        if (markBitmapId != 0) {
            // 载入水印图片
            Bitmap markBitmap = BitmapFactory.decodeResource(context.getResources(), markBitmapId);

//            // 如果图片的大小小于水印的3倍，就不添加水印
//            if (markBitmap.getWidth() > bitmapWidth / 3 || markBitmap.getHeight() > bitmapHeight / 3) {
//                return bitmap;
//            }

            int markBitmapWidth = markBitmap.getWidth();
            int markBitmapHeight = markBitmap.getHeight();

            float scale = 0.75f;

            // 图片开始的坐标
            float bitmapX = (float) (textX - markBitmapWidth - 10);//这里的-10和下面的-20都是微调的结果
            float bitmapY =

                    (float) (textY - markBitmapHeight + 38);
            Rect rect = new Rect((int) bitmapX, (int) bitmapY, (int) (markBitmapWidth * scale + bitmapX), (int) (markBitmapHeight * scale + bitmapY));
            // 画图
            canvas.drawBitmap(markBitmap, null, rect, null);
        }

        //保存所有元素
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();

        return bmp;
    }
}
