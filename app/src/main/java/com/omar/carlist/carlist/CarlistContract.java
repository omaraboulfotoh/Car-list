package com.omar.carlist.carlist;

import com.omar.carlist.ParentPresenter;
import com.omar.carlist.ParentView;
import com.omar.carlist.app.data.models.Car;

import java.util.ArrayList;

public interface CarlistContract {

    interface View extends ParentView {

        void updateList(ArrayList<Car> cars);

        void addToList(ArrayList<Car> cars);

        void refreshList();

        void stopLoading();
    }

    interface Presenter extends ParentPresenter<CarlistContract.View> {
        void getCarList(int page, int sortType);

        void onRefresh();

        void startRefteshTimer(int interval);
    }
}
