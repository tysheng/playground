package com.tysheng.playground.imagecache;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tysheng.playground.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ImageCacheActivity extends AppCompatActivity {
    String url = "https://developer.android.com/images/tools/studio/studio-feature-gradle_2x.png";
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cache);
        mImageView = (ImageView) findViewById(R.id.imageView);

        Observable.create(new ImageDownloadSubscribe(this, url))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResourceObserver<File>() {
                    @Override
                    public void onNext(@NonNull File file) {
                        Timber.d(file.getAbsolutePath());
                        Glide.with(ImageCacheActivity.this)
                                .load(file)
                                .into(mImageView);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public static Observable<File> getFileObservable(final Context context, final String url) {
        return Observable.create(new FileSubscribe(context, url));
    }

    private static class ImageDownloadSubscribe implements ObservableOnSubscribe<File> {
        private Context context;
        private String url;

        public ImageDownloadSubscribe(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void subscribe(@NonNull ObservableEmitter<File> e) throws Exception {
            File file = Glide.with(context)
                    .asFile()
                    .load(url)
                    .submit().get();
            if (file == null || !file.exists()) {
                e.onError(new RuntimeException("Download failed"));
            } else {
                e.onNext(file);
                e.onComplete();
            }
        }
    }

    private static class FileSubscribe implements ObservableOnSubscribe<File> {
        private Context context;
        private String url;

        FileSubscribe(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void subscribe(@NonNull ObservableEmitter<File> e) throws Exception {
            File file = Glide.with(context)
                    .asFile()
                    .load(url)
                    .submit().get();
            if (file == null || !file.exists()) {
                e.onError(new RuntimeException("Download failed"));
            } else {
                // Generate a temp file, can be overridden.
                File tmp = new File(context.getCacheDir(), "ShareTmp.jpg");
                copy(file, tmp, false);
                e.onNext(tmp);
                e.onComplete();
            }
        }
    }

    public static void copy(File src, File dst, boolean deleteSrc) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src));
            out = new BufferedOutputStream(new FileOutputStream(dst));
            // Transfer bytes from in to out
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (deleteSrc) {
                src.delete();
            }
        }
    }
}
