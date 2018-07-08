package com.omar.carlist;

public interface ParentPresenter<T extends ParentView> {
    void registerView(T view);

    void unregisterView();

    void start();

}