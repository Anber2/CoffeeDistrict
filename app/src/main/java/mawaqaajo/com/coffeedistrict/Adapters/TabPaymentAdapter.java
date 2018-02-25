package mawaqaajo.com.coffeedistrict.Adapters;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import mawaqaajo.com.coffeedistrict.Fragments.CreditCardFragment;
import mawaqaajo.com.coffeedistrict.Fragments.KnetFragment;

/**
 * Created by Ayadi on 8/23/2017.
 */

public class TabPaymentAdapter extends FragmentPagerAdapter {

    public TabPaymentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new KnetFragment();
            case 1:
                // Games fragment activity
                return new CreditCardFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}