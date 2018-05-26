package com.enghack2018.View.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.Adapters.CallBacks.PlateOnClickListener;

import java.util.List;

public class FavouritePlateAdapter extends RecyclerView.Adapter<FavouritePlateAdapter.ViewHolder> {


    private final List<PlateDO> plateDOS;
    private PlateOnClickListener onClickListener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private PlateDO plateDO;

        private TextView nameTexRow;
        private TextView ratingTextRow;
        private TextView priceTextRow;
        private TextView typeTextRow;
        private ImageView plateImage;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTexRow = itemView.findViewById(R.id.nameTexRow);
            ratingTextRow = itemView.findViewById(R.id.ratingTextRow);
            priceTextRow = itemView.findViewById(R.id.priceTextRow);
            typeTextRow = itemView.findViewById(R.id.typeTextRow);
            plateImage = itemView.findViewById(R.id.plateImage);

            itemView.setOnClickListener(this);
        }

        //When the pending transaction is clicked
        @Override
        public void onClick(View view) {
            //Small click animation when the item view is clicked
            onClickListener.onClick(plateDO);
        }
    }

    public FavouritePlateAdapter(List<PlateDO> plateDOS, Context context, PlateOnClickListener onClickListener){
        this.plateDOS = plateDOS;
        this.onClickListener = onClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_plate_row, parent, false);
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlateDO plateDO = plateDOS.get(position);
        holder.nameTexRow.setText(plateDO.getName());
        holder.ratingTextRow.setText(plateDO.getAvgReviews());
        holder.priceTextRow.setText(plateDO.getDollar());
        holder.typeTextRow.setText(plateDO.getType());
        Glide.with(context).load(plateDO.getImageUrl()).into(holder.plateImage);
        holder.plateDO = plateDO;
    }

    @Override
    public int getItemCount() {
        return plateDOS.size();
    }
}
