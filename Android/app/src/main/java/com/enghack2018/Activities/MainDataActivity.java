package com.enghack2018.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.enghack2018.Activities.CallBacks.MainDataCallBack;
import com.enghack2018.Controllers.MainDataController;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.MainDataView;

import java.util.ArrayList;
import java.util.List;

public class MainDataActivity extends AppCompatActivity implements MainDataCallBack {

    private MainDataView mainDataView;
    private MainDataController mainDataController;

    private List<PlateDO> favouritePlates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data);

        setupController();
        setupView();
    }

    private void setupController() {
        this.mainDataController = new MainDataController();
    }

    @SuppressWarnings("ALL")
    private void setupView() {
        ArrayList<PlateDO> plateDOs = (ArrayList<PlateDO>) getIntent().getExtras().get("plates");
        this.mainDataView = new MainDataView(getApplicationContext(), findViewById(R.id.main_data), getSupportFragmentManager());
        this.mainDataView.setupTabs(plateDOs);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }

    @Override
    public List<PlateDO> getFavouritePlates() {
        return favouritePlates;
    }

    @Override
    public void addOneToFavouritePlate(PlateDO plateDO) {
        favouritePlates.add(plateDO);
        if (favouritePlates.size() == 1){
            this.mainDataView.createRecyclerView();
        } else {
            this.mainDataView.refreshRecyclerView();
        }
    }
}
