package com.omar.carlist;


public interface ParentView {
    void initView();

    void showProgress();

    void hideProgress();

    void showError(String error);

    void showError();

    void showNoConnection();
}
