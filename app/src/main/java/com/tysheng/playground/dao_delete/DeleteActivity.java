package com.tysheng.playground.dao_delete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tysheng.playground.R;

public class DeleteActivity extends AppCompatActivity {


//    BarDao barDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
//        barDao = DBUtil.getInstance(this).getBarDao();
//
//        Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5))
//                .map(i -> {
//                    Bar bar = new Bar();
//                    bar.setAge(i);
//                    bar.setName("name " + i);
//                    return bar;
//                })
//                .toList()
//                .toObservable()
//                .map(bars -> {
//                    barDao.saveInTx(bars);
//                    return true;
//                })
//                .map(aBoolean -> {
//                    barDao.queryBuilder()
//                            .where(BarDao.Properties.Age.ge(2))
//                            .buildDelete()
//                            .executeDeleteWithoutDetachingEntities();
//                    barDao.detachAll();
//                    return barDao.queryBuilder().list();
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe(new ResourceObserver<List<Bar>>() {
//                    @Override
//                    public void onNext(List<Bar> bars) {
//                        Timber.d("size" + bars.size());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Timber.d(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }
}
