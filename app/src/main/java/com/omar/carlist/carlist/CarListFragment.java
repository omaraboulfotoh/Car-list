package com.omar.carlist.carlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omar.carlist.ParentFragment;
import com.omar.carlist.R;
import com.omar.carlist.app.Constants;
import com.omar.carlist.app.data.models.Car;
import com.omar.carlist.utils.LocaleHelper;
import com.omar.carlist.utils.listeners.PaginationScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CarListFragment extends ParentFragment implements CarlistContract.View, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.filter_view)
    CardView filterView;
    @BindView(R.id.rv_car_list)
    RecyclerView rvCarList;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.btn_sort)
    TextView btnSort;

    private int page = 0;
    private int sortType = Constants.NORMLA_SORT;
    private boolean isLoading = false;
    private boolean isLastPage = false;


    @Inject
    public CarListFragment() {

    }

    @Inject
    protected CarlistContract.Presenter mPresenter;

    private Unbinder unbinder;
    private CarListAdapter adapter;
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.registerView(this);
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateList(ArrayList<Car> cars) {
        adapter.updateAll(cars);
        isLoading = false;
    }

    @Override
    public void addToList(ArrayList<Car> cars) {
        adapter.insertAll(cars);
        isLoading = false;
    }

    @Override
    public void refreshList() {
        adapter.clear();
        page = 0;
        isLastPage = false;
        isLoading = false;
        mPresenter.getCarList(page, sortType);


    }

    @Override
    public void stopLoading() {
        isLastPage = true;
        isLoading = true;
    }

    @Override
    public void initView() {
        manager = new LinearLayoutManager(getContext());
        adapter = new CarListAdapter(new ArrayList<Car>(), LocaleHelper.getInstance(getContext()).getLanguage());
        rvCarList.setLayoutManager(manager);
        rvCarList.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(this);
        rvCarList.addOnScrollListener(new PaginationScrollListener(manager) {
            @Override
            protected void loadMoreItems() {
                page++;
                isLoading = true;
                mPresenter.getCarList(page, sortType);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        mPresenter.getCarList(page, sortType);
    }

    @OnClick({R.id.btn_filter, R.id.btn_sort, R.id.btn_grid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                break;
            case R.id.btn_sort:
                PopupMenu popup = new PopupMenu(getContext(), btnSort);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.sort_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.end_date:
                                sortType = Constants.END_DATE_SORT;
                                break;
                            case R.id.price:
                                sortType = Constants.PRICE_SORT;
                                break;
                            case R.id.year:
                                sortType = Constants.YEAR_SORT;
                                break;
                        }
                        mPresenter.onRefresh();
                        return true;
                    }
                });

                popup.show();
                break;
            case R.id.btn_grid:
                break;
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showProgress() {
        if (page == 0) {
            if (swipeRefresh != null)
                swipeRefresh.setRefreshing(true);
        } else {
            if (adapter != null)
                adapter.addFooterProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (page == 0) {
            if (swipeRefresh != null)
                swipeRefresh.setRefreshing(false);
        } else {
            if (adapter != null)
                adapter.removeFooterProgress();
        }
    }


    public Toolbar getToolbar() {
        return toolbar;
    }
}
