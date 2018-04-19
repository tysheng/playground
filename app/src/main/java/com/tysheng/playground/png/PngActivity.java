package com.tysheng.playground.png;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tysheng.playground.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PngActivity extends AppCompatActivity {
    Typeface mTypeface;
    int padding = 20;
    // 创建画笔
    Paint mPaint;
    private File dir;
    private ImageView mImageView;
    private TextView mText;
    private EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_png);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mTypeface = Typeface.createFromAsset(getAssets(), "uthmani.otf");

        mText = (TextView) findViewById(R.id.text);
        mText.setText("ٱلۡعَٰلَمِينَ");
        mText.setTypeface(mTypeface);
        mText.setTextSize(TypedValue.COMPLEX_UNIT_PX, 80f);
        mText.setTextColor(0xFFFFCB00);

        mPaint = new Paint();
//        float scale = Resources.getSystem().getDisplayMetrics().density;
//        // 水印的字体大小
//        mPaint.setTextSize((int) (15 * scale));
        mPaint.setTextSize(96f);
        mPaint.setAntiAlias(true);
        mPaint.setTypeface(mTypeface);
        mPaint.setColor(0xFFFFCB00);

        mEditText = (EditText) findViewById(R.id.editText);

    }

    public static final int INTERVAL = 500000;
    private int count;
    private int max;


    private int[] measureViewMaxWidth(View view) {
        if (view == null) {
            return null;
        }

        view.measure(0, 0);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    private void read() {
        TextPaint paint = mText.getPaint();
        Paint.FontMetrics m = paint.getFontMetrics();

        Timber.d("%f %f %f %d", m.top, m.leading, m.ascent, mText.getMeasuredHeight());

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("wbw.txt")));
            String line;

            while ((line = br.readLine()) != null) {
                // process the line.
                count++;
                if (count < start) {
                    continue;
                }
                if (count >= max) {
                    break;
                }
                if (count % 1000 == 0) {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Timber.d("count %d", count);
                String[] splits = line.split("\\s+");
                save(splits[1], splits[0]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timber.d("ok");
    }

    private void save(String text, final String filename) {
        mText.setText(text);
        final int[] wh = measureViewMaxWidth(mText);
        Observable.just(text)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull String s) throws Exception {
                        return start(s, wh);
                    }
                })
                .map(new Function<Bitmap, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Bitmap bitmap) throws Exception {
                        File file = new File(dir, filename + ".png");
                        FileOutputStream fos = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, fos);
                        fos.close();
                        bitmap.recycle();
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new ResourceObserver<Boolean>() {
                    @Override
                    public void onNext(@NonNull Boolean aBoolean) {
                        Timber.d("%b", aBoolean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.d(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private Bitmap start(String text, int[] wh) {
        // 文字矩阵区域
        Bitmap bitmap = Bitmap.createBitmap(wh[0] + 20, wh[1] + 20, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int xPos = 0 + 10;
        float yPos = -mText.getPaint().getFontMetrics().ascent + 10;
        mText.getPaint().setColor(0xFFFFCB00);
        canvas.drawText(text, xPos, yPos, mText.getPaint());

        //保存所有元素
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bitmap;

    }

    private int start;

    public void start(View view) {
        String str = mEditText.getText().toString();
        String[] ints = str.split(" ");
        start = Integer.valueOf(ints[0]);
        max = Integer.valueOf(ints[1]);
        dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "text_" + max + "/");
        dir.mkdir();
        read();
    }
}
