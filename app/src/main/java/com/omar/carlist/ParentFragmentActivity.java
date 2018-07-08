package com.omar.carlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.omar.carlist.utils.GeneralUtils;


public abstract class ParentFragmentActivity extends ParentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        if (getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container) == null) {
            GeneralUtils.addFragmentToActivity(getSupportFragmentManager(), getFragment(), R.id.fragment_container);
        }

    }

    protected abstract Fragment getFragment();

    protected Fragment getAlreadyAddedFragment() {
        return getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1)
            finish();
        else
            super.onBackPressed();
    }
}
