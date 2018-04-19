package com.tysheng.playground;

import com.google.gson.Gson;
import com.tysheng.playground.data.CommonResult;
import com.tysheng.playground.data.Phone;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.ResourceObserver;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {

        List<Object> strings = new ArrayList<>();
        strings.add("1");
        Observable.just(strings)
                .flatMapIterable(new Function<List<Object>, Iterable<Object>>() {
                    @Override
                    public Iterable<Object> apply(List<Object> objects) throws Exception {
                        return objects;
                    }
                })
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        return o;
                    }
                })
                .toList()
                .subscribe(new Consumer<List<Object>>() {
                    @Override
                    public void accept(List<Object> objects) throws Exception {
                        System.out.println(objects.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });
//        assertEquals(4, 2 + 2);
    }


    @Test
    public void gson() {
        Gson gson = new Gson();
        String str = "{\n" +
                "    \"data\": {\"country_code\": \"65\",\n" +
                "    \"phone\": \"92476751\"},\n" +
                "    \"resp_meta\": {\n" +
                "        \"msg\": \"exist\"\n" +
                "    }\n" +
                "}";

        CommonResult result = gson.fromJson(str, CommonResult.class);
        Phone phone = gson.fromJson(result.getData(), Phone.class);
        System.out.println(phone.getPhone());
    }

    @Test
    public void filter() {

        Observable.just(1, 2, 3)
                .filter(integer -> integer > 1)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("next " + integer);
                    }
                })
                .map(integer -> ++integer)
                .subscribe(new ResourceObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });

    }

    @Test
    public void concat() {
        Observable<Integer> observable = Observable.just(1, 2, 3)
                .filter(integer -> integer > 5);
        Observable<Integer> observable1 = Observable.just(4);
        observable.concatWith(observable1)
                .subscribe(new ResourceObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });

    }

    @Test
    public void zip() {

        Observable.zip(Observable.just(1).map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        Thread.sleep(1000);
                        System.out.println("1 map");
                        return integer;
                    }
                }).filter(integer -> integer > 10), Observable.just(2).map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        System.out.println("2 map");
                        return integer;
                    }
                }),
                (integer, integer2) -> integer + integer2)
                .subscribe(new ResourceObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete");
                    }
                });
    }

    int i = 10;

    @Test
    public void foo() {
//        System.out.println(i);
        String[] split = "sdsd".split(";");

    }


}