package com.omar.carlist.app;


import android.content.Context;
import android.support.multidex.MultiDex;

import com.omar.carlist.dagger.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class App extends DaggerApplication {
    private static App instance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


    public static App getInstance() {
        return instance;
    }


}
