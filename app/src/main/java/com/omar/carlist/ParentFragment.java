package com.omar.carlist;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import dagger.android.support.DaggerFragment;


public class ParentFragment extends DaggerFragment implements ParentView {

    protected ProgressBar progressBar;
    private int menuId;

    @Override
    public void initView() {

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


    protected void hideInputType() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);

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

    public void showSnackbarAction(View view, int text, int action, View.OnClickListener onClickListener) {
        Snackbar snack = Snackbar.make(
                view,
                text,
                Snackbar.LENGTH_LONG).setAction(action, onClickListener);
        View snackView = snack.getView();
        snackView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        snack.show();
    }




}
