package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.search_fake;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 8/8/2017.
 */

public class MyOrder extends Fragment {
    View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static MainActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.my_order_fragment_layout, container, false);
        toolbar_title.setText(myContext.getString(R.string.my_order));
        searchView.setVisibility(View.GONE);
        filterimg.setVisibility(View.GONE);
        search_fake.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);
        filter_drawer.setVisibility(View.GONE);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.Black));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new CurrentOrderFragment(), "Current Order");
        adapter.addFragment(new CompletedOrdersFragment(), "Completed Orders");


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }
}
