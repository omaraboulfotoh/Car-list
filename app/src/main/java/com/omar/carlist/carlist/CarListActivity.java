package com.omar.carlist.carlist;

import android.support.v4.app.Fragment;
import android.view.View;

import com.omar.carlist.ParentFragmentActivity;

import javax.inject.Inject;

public class CarListActivity extends ParentFragmentActivity {

    @Inject
    protected CarListFragment carListFragment;


    @Override
    protected Fragment getFragment() {
        return carListFragment;
    }

    @Override
    protected View getView() {
        return null;
    }

    @Override
    public void initView() {

    }
}
