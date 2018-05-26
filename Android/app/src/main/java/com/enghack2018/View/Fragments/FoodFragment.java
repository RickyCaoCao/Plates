package com.enghack2018.View.Fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.net.URL;


/**
 *
 */
public class FoodFragment extends Fragment {

    private PlateDO plateDO;
    private View rootView;
    private Context context;

    public static FoodFragment newInstance(PlateDO plateDO){
        FoodFragment foodFragment =  new FoodFragment();
        foodFragment.plateDO = plateDO;
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
        this.context = getContext();
        return this.rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedOnInstance){
        super.onActivityCreated(savedOnInstance);

        //fetch image
        loadImageFromWebOperations(this.plateDO.getImageUrl());
        populateText();
    }

    public void loadImageFromWebOperations(String url) {
        new Thread(()-> {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, null);

                //setbackground of imageview
                ImageView imageView = this.rootView.findViewById(R.id.image);
                imageView.setBackground(d);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).run();

    }

    private void populateText() {
        ((TextView) this.rootView.findViewById(R.id.nameText)).setText(this.plateDO.getName());
        ((TextView) this.rootView.findViewById(R.id.ratingText)).setText(this.plateDO.getAvgReviews());
        ((TextView) this.rootView.findViewById(R.id.priceText)).setText(this.plateDO.getDollar());
        ((TextView) this.rootView.findViewById(R.id.typeText)).setText(this.plateDO.getType());
    }
}
