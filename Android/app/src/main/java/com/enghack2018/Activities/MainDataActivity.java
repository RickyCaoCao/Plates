package com.enghack2018.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.enghack2018.Controllers.MainDataController;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.MainDataView;

import java.util.ArrayList;
import java.util.List;

public class MainDataActivity extends AppCompatActivity {

    MainDataView mainDataView;
    MainDataController mainDataController;

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
        this.mainDataView.setupViewPager(plateDOs);
    }
}
