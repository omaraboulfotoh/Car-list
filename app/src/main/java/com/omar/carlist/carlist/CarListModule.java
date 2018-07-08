package com.omar.carlist.carlist;

import com.omar.carlist.dagger.ActivityScoped;
import com.omar.carlist.dagger.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CarListModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract CarListFragment carListFragment();

    @ActivityScoped
    @Binds
    abstract CarlistContract.Presenter provideCarListPresenter(CarListPresenter presenter);

}
