package com.omar.carlist.carlist;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omar.carlist.ParentRecyclerAdapter;
import com.omar.carlist.ParentRecyclerViewHolder;
import com.omar.carlist.R;
import com.omar.carlist.app.data.models.Car;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarListAdapter extends ParentRecyclerAdapter<Car> {
    private final int NORMAL_TYPE = 0;
    private final int PROGRESS_TYPE = 1;
    private String local;

    CarListAdapter(List<Car> data, String local) {
        super(data);
        this.local = local;
    }

    @NonNull
    @Override
    public ParentRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case PROGRESS_TYPE:
                view = inflater.inflate(R.layout.item_progress, parent, false);
                return new ProgressViewHolder(view);
            default:
                view = inflater.inflate(R.layout.item_car, parent, false);
                ViewHolder holder = new ViewHolder(view);
                holder.setOnItemClickListener(getItemClickListener());
                return holder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ParentRecyclerViewHolder holder, int position) {
        if (holder.getItemViewType() == NORMAL_TYPE) {
            ViewHolder viewHolder = (ViewHolder) holder;
            Car car = getData().get(position);
            Picasso.get().load(car.getThumbnailImage()).into(viewHolder.ivCar);
            viewHolder.tvCarModel.setText(car.getModel(local));
            viewHolder.tvBids.setText(String.valueOf(car.getAuctionInfo().getBids()));
            viewHolder.tvCarPrice.setText(String.valueOf(car.getAuctionInfo().getCurrentPrice()));
            viewHolder.tvCarPriceCurrency.setText(car.getAuctionInfo().getCurrency(local));
            viewHolder.tvLotNumber.setText(String.valueOf(car.getAuctionInfo().getLot()));
            viewHolder.tvTimeLeft.setText("00:44:33");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getData().get(position) != null) {
            return NORMAL_TYPE;
        } else {
            return PROGRESS_TYPE;
        }
    }

    class ViewHolder extends ParentRecyclerViewHolder {
        @BindView(R.id.iv_car)
        ImageView ivCar;
        @BindView(R.id.tv_car_model)
        TextView tvCarModel;
        @BindView(R.id.tv_car_price)
        TextView tvCarPrice;
        @BindView(R.id.tv_car_price_currency)
        TextView tvCarPriceCurrency;
        @BindView(R.id.tv_lot_number)
        TextView tvLotNumber;
        @BindView(R.id.tv_bids)
        TextView tvBids;
        @BindView(R.id.tv_time_left)
        TextView tvTimeLeft;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ProgressViewHolder extends ParentRecyclerViewHolder {

        ProgressViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
