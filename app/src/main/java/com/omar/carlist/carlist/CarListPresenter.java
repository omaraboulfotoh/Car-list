package com.omar.carlist.carlist;

import android.os.CountDownTimer;

import com.omar.carlist.api.RestClient;
import com.omar.carlist.app.Constants;
import com.omar.carlist.app.data.api.CarListResponse;
import com.omar.carlist.app.data.models.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CarListPresenter implements CarlistContract.Presenter {
    private CarlistContract.View mView;
    private ArrayList<Car> cars = new ArrayList<>();
    private int count = 15;
    private CountDownTimer countDownTimer;
    private Long ticks;

    @Inject
    CarListPresenter() {
    }

    @Override
    public void registerView(CarlistContract.View view) {
        this.mView = view;
    }

    @Override
    public void unregisterView() {
        this.mView = DUMMY_VIEW;
    }

    @Override
    public void start() {
        mView.initView();
    }

    @Override
    public void getCarList(final int page, final int sortType) {
        mView.showProgress();
        if (page == 0)
            RestClient.getClient().getCarList().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<CarListResponse>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Response<CarListResponse> response) {
                            mView.hideProgress();
                            if (response.isSuccessful()) {
                                cars = response.body().getCars();
                                ticks = response.body().getTicks();
                                switch (sortType) {
                                    case Constants.END_DATE_SORT:
                                        Collections.sort(cars, new Comparator<Car>() {
                                            @Override
                                            public int compare(Car o1, Car o2) {
                                                return o1.getAuctionInfo().getEndDate().compareTo(o2.getAuctionInfo().getEndDate());
                                            }
                                        });
                                        break;
                                    case Constants.PRICE_SORT:
                                        Collections.sort(cars, new Comparator<Car>() {
                                            @Override
                                            public int compare(Car o1, Car o2) {
                                                return o1.getAuctionInfo().getCurrentPrice().compareTo(o2.getAuctionInfo().getCurrentPrice());
                                            }
                                        });
                                        break;
                                    case Constants.YEAR_SORT:
                                        Collections.sort(cars, new Comparator<Car>() {
                                            @Override
                                            public int compare(Car o1, Car o2) {
                                                return o1.getYear().compareTo(o2.getYear());
                                            }
                                        });
                                        break;

                                }
                                ArrayList<Car> arrayList = new ArrayList<>();
                                if (cars.size() > count) {
                                    for (int i = page * count; i < (count * (page + 1)); i++) {
                                        arrayList.add(cars.get(i));
                                    }
                                } else {
                                    for (int i = page; i < cars.size(); i++) {
                                        arrayList.add(cars.get(i));
                                    }
                                }
                                mView.updateList(arrayList);
                                startRefteshTimer(response.body().getRefreshInterval());
                            } else {
                                mView.showError();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.hideProgress();
                            mView.showNoConnection();
                        }

                        @Override
                        public void onComplete() {
                            mView.hideProgress();
                        }
                    });
        else if (cars.size() > page * count) {
            ArrayList<Car> arrayList = new ArrayList<>();
            for (int i = page * count; i < (count * (page + 1)); i++) {
                arrayList.add(cars.get(i));
            }

            mView.hideProgress();
            mView.addToList(arrayList);
        } else {
            mView.hideProgress();
            mView.stopLoading();
        }

    }

    @Override
    public void onRefresh() {
        mView.refreshList();
    }

    @Override
    public void startRefteshTimer(int interval) {
        countDownTimer = new CountDownTimer(interval * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mView.refreshList();
            }
        };
        countDownTimer.start();
    }

    private final CarlistContract.View DUMMY_VIEW = new CarlistContract.View() {
        @Override
        public void updateList(ArrayList<Car> cars) {

        }

        @Override
        public void addToList(ArrayList<Car> cars) {

        }

        @Override
        public void refreshList() {

        }

        @Override
        public void stopLoading() {

        }

        @Override
        public void initView() {

        }

        @Override
        public void showProgress() {

        }

        @Override
        public void hideProgress() {

        }

        @Override
        public void showError(String error) {

        }

        @Override
        public void showError() {

        }

        @Override
        public void showNoConnection() {

        }
    };
}
