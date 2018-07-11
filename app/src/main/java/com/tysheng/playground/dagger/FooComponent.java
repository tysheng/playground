package com.tysheng.playground.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by tysheng
 * Date: 14/9/17 2:17 PM.
 * Email: tyshengsx@gmail.com
 */
@Singleton
@Component(modules = {Module.class})
public interface FooComponent {

//    Dagger2Activity activity();
//    void inject(Dagger2Activity a);
}
