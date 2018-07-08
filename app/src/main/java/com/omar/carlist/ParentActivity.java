package com.omar.carlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import com.omar.carlist.utils.LocaleHelper;

import dagger.android.support.DaggerAppCompatActivity;


public abstract class ParentActivity extends DaggerAppCompatActivity implements ParentView {

    protected ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Set Application local
        LocaleHelper localeHelper = LocaleHelper.getInstance(this);
        localeHelper.setLanguage(localeHelper.getLanguage());
        if (localeHelper.getLanguage().equalsIgnoreCase(LocaleHelper.LANGUAGE_ARABIC))
            LocaleHelper.ChangeDesignToRTL(this);
        else
            LocaleHelper.ChangeDesignToLTR(this);
        super.onCreate(savedInstanceState);
    }

    public void showSnackbar(View view, String text) {
        Snackbar snack = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG);
        View snackView = snack.getView();
        snackView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        snack.show();
    }

    public void showSnackbar(View view, int text) {
        Snackbar snack = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG);
        View snackView = snack.getView();
        snackView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        snack.show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        showSnackbar(getView(), error);
    }

    @Override
    public void showError() {
        showSnackbar(getView(), R.string.error_occured);
    }

    @Override
    public void showNoConnection() {
        showSnackbar(getView(), R.string.error_connection);
    }

    protected abstract View getView();
}
