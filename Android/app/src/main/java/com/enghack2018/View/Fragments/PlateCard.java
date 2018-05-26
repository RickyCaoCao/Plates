package com.enghack2018.View.Fragments;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.Fragments.CallBack.SwipeCallBack;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

@Layout(R.layout.plate_card_view)
public class PlateCard {

    private final SwipeCallBack swipeCallBack;
    @View(R.id.image)
    private ImageView image;

    @View(R.id.nameText)
    private TextView nameText;

    @View(R.id.ratingText)
    private TextView ratingText;

    @View(R.id.priceText)
    private TextView priceText;

    @View(R.id.typeText)
    private TextView typeText;

    private PlateDO plateDO;
    private Context context;
    private SwipePlaceHolderView swipePlaceHolderView;

    public PlateCard(Context context, PlateDO plateDO, SwipePlaceHolderView swipePlaceHolderView, SwipeCallBack swipeCallBack){
        this.context = context;
        this.plateDO = plateDO;
        this.swipePlaceHolderView = swipePlaceHolderView;
        this.swipeCallBack = swipeCallBack;
    }

    @Resolve
    private void onResolved(){
        Glide.with(context).load(plateDO.getImageUrl()).into(image);
        nameText.setText(plateDO.getName());
        ratingText.setText(plateDO.getAvgReviews());
        priceText.setText(plateDO.getDollar());
        typeText.setText(plateDO.getType());
    }

    @SwipeOut
    private void onSwipedOut(){
        //Log.d("EVENT", "onSwipedOut");
        swipePlaceHolderView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        //Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        this.swipeCallBack.onSwipeIn(plateDO);
    }

    @SwipeInState
    private void onSwipeInState(){
        //Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        //Log.d("EVENT", "onSwipeOutState");
    }
}
