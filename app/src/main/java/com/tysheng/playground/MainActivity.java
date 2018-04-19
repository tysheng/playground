package com.tysheng.playground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tysheng.playground.anim.AnimActivity;
import com.tysheng.playground.arch.ArchActivity;
import com.tysheng.playground.dagger.Dagger2Activity;
import com.tysheng.playground.dao_delete.DeleteActivity;
import com.tysheng.playground.edittext.EditTextActivity;
import com.tysheng.playground.edittextstyle.EditTextStyleActivity;
import com.tysheng.playground.imagecache.ImageCacheActivity;
import com.tysheng.playground.optionbutton.GameActivity;
import com.tysheng.playground.png.PngActivity;
import com.tysheng.playground.rx_broadcast.RxBroadcastActivity;
import com.tysheng.playground.scroll.ScrollActivity;
import com.tysheng.playground.scroll_rv.ScrollRvActivity;
import com.tysheng.playground.selecttextview.SelectTextActivity;
import com.tysheng.playground.shader.ShaderActivity;
import com.tysheng.playground.share.ShareActivity;
import com.tysheng.playground.support.SupportActivity;
import com.tysheng.playground.touch.TouchActivity;
import com.tysheng.playground.unique.UniqueActivity;
import com.tysheng.playground.watermark.WaterMarkActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    List<Class> mClassList = new ArrayList<>();

    {
        mClassList.add(SupportActivity.class);
        mClassList.add(Dagger2Activity.class);
        mClassList.add(ShaderActivity.class);
        mClassList.add(SelectTextActivity.class);
        mClassList.add(TouchActivity.class);
        mClassList.add(ShareActivity.class);
        mClassList.add(ScrollActivity.class);
        mClassList.add(WaterMarkActivity.class);
        mClassList.add(ImageCacheActivity.class);
        mClassList.add(PngActivity.class);
        mClassList.add(EditTextActivity.class);
//        mClassList.add(DBImageActivity.class);
        mClassList.add(RxBroadcastActivity.class);
        mClassList.add(EditTextStyleActivity.class);
        mClassList.add(DeleteActivity.class);
        mClassList.add(ScrollRvActivity.class);
        mClassList.add(UniqueActivity.class);
        mClassList.add(GameActivity.class);
        mClassList.add(AnimActivity.class);
        mClassList.add(ArchActivity.class);
        mClassList.add(KtActivity.class);

    }

    private static final String TAG = "MainActivity";
    @Inject
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils.log();
        List<String> list = new ArrayList<>();
        for (Class c : mClassList) {
            list.add(c.getSimpleName().substring(0, c.getSimpleName().length() - 8));
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.item_main, list));
        listView.setOnItemClickListener(this);
//        Log.d(TAG, "getLanguage = " + Locale.getDefault().getLanguage());
//        Log.d(TAG, "Resources.getLanguage = " + Resources.getSystem().getConfiguration().locale.getLanguage());
//        Log.d(TAG, "getResources.getLanguage = " + getResources().getConfiguration().locale);

        List<Object> strings = new ArrayList<>();
//        strings.add("1");
//        Observable.just(strings)
//                .flatMapIterable(objects -> objects)
//                .map(o -> o)
//                .toList()
//                .subscribe(objects -> Timber.d(objects.toString()), new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Timber.d("handle ");
//                    }
//                });
//        Flowable.just(1)
//                .map(integer -> integer / 0)
//                .subscribe();
//
//        listView.postDelayed(() -> Completable.complete()
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Action() {
//                    @Override
//                    public void run() throws Exception {
//
//                        listView.setBackgroundColor(Color.RED);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
////                        Timber.d(throwable);
//                    }
//                }),1000L);
//        TextView textView = new TextView(this);
//        listView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            textView.setText("1");
//                            listView.setBackgroundColor(Color.RED);
//                        }catch (Exception e){
//                            Timber.d(e);
//                        }
//                    }
//                }).start();
//            }
//        },1000L);

//        ExecutorService e = Executors.newFixedThreadPool(3);
//        //submit方法有多重参数版本，及支持callable也能够支持runnable接口类型.
//        Future future = e.submit(new Callable<Object>() {
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        });
//        future.isDone(); //return true,false 无阻塞
//        try {
//            future.get(); // return 返回值，阻塞直到该线程运行结束
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        } catch (ExecutionException e1) {
//            e1.printStackTrace();
//        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(this, mClassList.get(position)));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy");
    }
}
