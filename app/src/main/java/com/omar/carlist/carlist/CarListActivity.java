package com.omar.carlist.carlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;

import com.omar.carlist.ParentFragmentActivity;
import com.omar.carlist.R;
import com.omar.carlist.utils.LocaleHelper;

import javax.inject.Inject;

public class CarListActivity extends ParentFragmentActivity {


    @Inject
    protected CarListFragment carListFragment;

    private LocaleHelper localeHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localeHelper = LocaleHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSupportActionBar(carListFragment.getToolbar());
        getSupportActionBar().setTitle(null);
        createOptionsMenu(R.menu.menu_home);
    }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_lang) {

            localeHelper.setLanguage(localeHelper.getLanguage().equals(LocaleHelper.LANGUAGE_ARABIC)
                    ? LocaleHelper.LANGUAGE_ENGLISH : LocaleHelper.LANGUAGE_ARABIC);
            startActivity(new Intent(this, CarListActivity.class));
            finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }
}
