package com.enghack2018.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.enghack2018.Controllers.SplashScreenController;
import com.enghack2018.R;
import com.enghack2018.View.SplashScreenView;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenController splashScreenController;
    private SplashScreenView splashScreenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        setUpController();
        setUpView();
    }

    private void setUpController() {
        this.splashScreenController = new SplashScreenController(getApplicationContext());
        this.splashScreenController.fetchData(20);
    }

    private void setUpView() {
        this.splashScreenView = new SplashScreenView();
    }


}
