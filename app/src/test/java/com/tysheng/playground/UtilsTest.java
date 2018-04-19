package com.tysheng.playground;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by tysheng
 * Date: 20/11/17 1:13 PM.
 * Email: tyshengsx@gmail.com
 */
public class UtilsTest {
    @Test
    public void main() {
        Observable.just(new ArrayList<>())
                .flatMapIterable(objects -> objects)
                .map(o -> o)
                .toList()
                .subscribe(objects -> System.out.println(objects));

    }


    @Test
    public void list() {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 3 == 0) {
                list2.add(i);
            }
        }

        list.removeAll(list2);
        System.out.println(list);
        System.out.println(list2);
    }

    @Test
    public void test1(){
        Observable.just(1)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("1:"+integer);
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return false;
                    }
                })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("2:"+integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("3:"+integer);
                    }
                });
    }
}