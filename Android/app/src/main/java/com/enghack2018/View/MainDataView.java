package com.enghack2018.View;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;
import com.enghack2018.View.Fragments.FoodFragment;
import com.enghack2018.View.Fragments.InterestedListFragment;
import com.enghack2018.View.SecondaryViews.NonSwipeableViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainDataView {

    private FragmentPagerAdapter fragmentPagerAdapter;
    private NonSwipeableViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager fragmentManager;
    private Context context;
    private ViewGroup viewGroup;



    public MainDataView(Context context, ViewGroup viewGroup, FragmentManager fragmentManager){
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.viewGroup = viewGroup;
    }

    public void setupTabs(List<PlateDO> plateDOS){

        fragments.add(FoodFragment.newInstance(plateDOS));
        fragments.add(InterestedListFragment.newInstance());

        //Setup ViewPager
        this.viewPager = this.viewGroup.findViewById(R.id.mainDataViewPager);
        this.viewPager.setAdapter(new FragmentPagerAdapter(this.fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position){
                switch(position){
                    case 0:
                        return "Food";
                    case 1:
                        return "Favourites";
                    default:
                        return null;
                }
            }
        });

        TabLayout tabLayout = this.viewGroup.findViewById(R.id.mainDataTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //Override the onTabSelected method
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                super.onTabSelected(tab);
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }


    public void createRecyclerView() {
        InterestedListFragment interestedListFragment = (InterestedListFragment) fragments.get(1);
        interestedListFragment.createRecyclerView();
    }

    public void refreshRecyclerView() {
        InterestedListFragment interestedListFragment = (InterestedListFragment) fragments.get(1);
        interestedListFragment.refreshRecyclerView();
    }
}
