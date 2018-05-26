package com.enghack2018.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enghack2018.Activities.CallBacks.MainDataCallBack;
import com.enghack2018.R;
import com.enghack2018.View.Adapters.FavouritePlateAdapter;
import com.enghack2018.View.Dialogs.FavouritePlateDialog;

public class InterestedListFragment extends Fragment  implements SwipeRefreshLayout.OnRefreshListener  {

    private View rootView;
    private Context context;
    private MainDataCallBack mainDataCallBack;

    private RecyclerView favouriteList;
    private FavouritePlateAdapter favouritePlateAdapter;



    public static InterestedListFragment newInstance(){
        InterestedListFragment interestedListFragment =  new InterestedListFragment();
        return interestedListFragment;
    }

    public InterestedListFragment(){}


    public void onAttach(Context context){
        super.onAttach(context);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedOnInstance){
        this.rootView = layoutInflater.inflate(R.layout.interested_food_tab, viewGroup, false);
        this.context = getContext();
        this.mainDataCallBack = (MainDataCallBack) getActivity();
        return this.rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedOnInstance){
        super.onActivityCreated(savedOnInstance);

    }


    @Override
    public void onRefresh() {
        refreshRecyclerView();
        hideRefreshAnimation();
    }

    public void createRecyclerView() {
        if (mainDataCallBack.getFavouritePlates().isEmpty()){
            //First hide all the default setup views
            this.rootView.findViewById(R.id.noFavourites).setVisibility(View.VISIBLE);
        } else {
            this.rootView.findViewById(R.id.noFavourites).setVisibility(View.GONE);
            //Display the recycler views
            this.rootView.findViewById(R.id.favouriteList).setVisibility(View.VISIBLE);
            //Display the pending transaction data correctly in the list views
            favouritePlateAdapter = new FavouritePlateAdapter(mainDataCallBack.getFavouritePlates(), context, plateDO -> {
                FavouritePlateDialog favouritePlateDialog = new FavouritePlateDialog(context, R.style.ShowDialog, plateDO);
                favouritePlateDialog.show();
            });

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            favouriteList = this.rootView.findViewById(R.id.favouriteList);
            favouriteList.setLayoutManager(layoutManager);
            favouriteList.setItemAnimator(new DefaultItemAnimator());
            favouriteList.setAdapter(favouritePlateAdapter);
        }
    }

    public void refreshRecyclerView() {
        if (favouritePlateAdapter != null){
            favouritePlateAdapter.notifyItemInserted(mainDataCallBack.getFavouritePlates().size() - 1);
        }
    }

    private void hideRefreshAnimation(){
        if (((SwipeRefreshLayout)this.rootView).isRefreshing()){
            ((SwipeRefreshLayout)this.rootView).setRefreshing(false);
        }
    }
}
