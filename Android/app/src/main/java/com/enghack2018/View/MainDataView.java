package com.enghack2018.View;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.Fragments.FoodFragment;

import java.util.ArrayList;
import java.util.List;

public class MainDataView {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Context context;
    private ViewGroup viewGroup;

    public MainDataView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager){
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.viewGroup = viewGroup;
    }

    public void setupViewPager(List<PlateDO> plateDOS){
        plateDOS.forEach(plateDO -> {
            fragments.add(FoodFragment.newInstance(plateDO));
        });

        //Setup ViewPager
        this.viewPager = this.viewGroup.findViewById(R.id.mainDataViewPager);
        this.viewPager.setAdapter(new FragmentPagerAdapter(this.fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });

    }


}
