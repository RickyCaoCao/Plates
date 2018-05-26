package com.enghack2018.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enghack2018.Activities.CallBacks.MainDataCallBack;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;


/**
 * Food fragment
 */
public class FoodFragment extends Fragment {

    private List<PlateDO> plateDOS;
    private View rootView;
    private MainDataCallBack mainDataCallBack;

    private SwipePlaceHolderView swipePlaceHolderView;

    public static FoodFragment newInstance(List<PlateDO> plateDOS){
        FoodFragment foodFragment =  new FoodFragment();
        foodFragment.plateDOS = plateDOS;
        return foodFragment;
    }

    public FoodFragment(){

    }

    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedOnInstance){
        this.rootView = layoutInflater.inflate(R.layout.fragment_food, viewGroup, false);
        this.mainDataCallBack = (MainDataCallBack) getActivity();
        return this.rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedOnInstance){
        super.onActivityCreated(savedOnInstance);

        //fetch image
        setupSwipeView();
    }

    private void setupSwipeView() {
        swipePlaceHolderView = this.rootView.findViewById(R.id.swipeView);

        swipePlaceHolderView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.plate_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.plate_swipe_out_msg_view));

        for (PlateDO plateDO : plateDOS){
            swipePlaceHolderView.addView(new PlateCard(getContext(), plateDO, swipePlaceHolderView, (plateDO1) -> {
                mainDataCallBack.addOneToFavouritePlate(plateDO1);
            }));
        }

        this.rootView.findViewById(R.id.rejectBtn).setOnClickListener(v -> {
            swipePlaceHolderView.doSwipe(false);
        });

        this.rootView.findViewById(R.id.acceptBtn).setOnClickListener(v -> {
            swipePlaceHolderView.doSwipe(true);
        });

    }
}
