package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.Adapters.TabPaymentAdapter;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

public class PaymentFragment extends Fragment {
    View v;
    private static MainActivity myContext;
    LinearLayout knet_linear, credit_linear;
    TextView knet_tv, credit_tv;
    ImageView knet_img, credit_img;
    private ViewPager viewPager;
    private TabPaymentAdapter mAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.payment_layout_fragment, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.pager);
        toolbar_title.setText(myContext.getString(R.string.choose_payment));
        searchView.setVisibility(View.GONE);
        knet_linear = (LinearLayout) v.findViewById(R.id.knet_linear);
        credit_linear = (LinearLayout) v.findViewById(R.id.credit_linear);
        mAdapter = new TabPaymentAdapter(myContext.getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(1);

        knet_tv = (TextView) v.findViewById(R.id.knet_tv);
        credit_tv = (TextView) v.findViewById(R.id.credit_tv);
        knet_img = (ImageView) v.findViewById(R.id.knet_img);
        credit_img = (ImageView) v.findViewById(R.id.credit_img);

        credit_tv.setTextColor(myContext.getResources().getColor(R.color.White));
        credit_img.setImageResource((R.drawable.credit_card));
        credit_linear.setBackgroundColor(myContext.getResources().getColor(R.color.payment_dark));

        knet_tv.setTextColor(myContext.getResources().getColor(R.color.payment_txt));
        knet_img.setImageResource((R.drawable.credit_icon_inactive));
        knet_linear.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        knet_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                knet_tv.setTextColor(myContext.getResources().getColor(R.color.White));
                knet_img.setImageResource((R.mipmap.credit));
                knet_linear.setBackgroundColor(myContext.getResources().getColor(R.color.payment_dark));
                viewPager.setCurrentItem(0);
                credit_tv.setTextColor(myContext.getResources().getColor(R.color.payment_txt));
                credit_img.setImageResource((R.mipmap.credit_inactive));
                credit_linear.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });

        credit_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credit_tv.setTextColor(myContext.getResources().getColor(R.color.White));
                credit_img.setImageResource((R.mipmap.credit));
                credit_linear.setBackgroundColor(myContext.getResources().getColor(R.color.payment_dark));
                viewPager.setCurrentItem(1);
                knet_tv.setTextColor(myContext.getResources().getColor(R.color.payment_txt));
                knet_img.setImageResource((R.mipmap.credit_inactive));
                knet_linear.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });
//        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
//        tabview();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    knet_tv.setTextColor(myContext.getResources().getColor(R.color.White));
                    knet_img.setImageResource((R.mipmap.credit));
                    knet_linear.setBackgroundColor(myContext.getResources().getColor(R.color.payment_dark));
                    credit_tv.setTextColor(myContext.getResources().getColor(R.color.payment_txt));
                    credit_img.setImageResource((R.mipmap.credit_inactive));
                    credit_linear.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                } else {
                    credit_tv.setTextColor(myContext.getResources().getColor(R.color.White));
                    credit_img.setImageResource((R.mipmap.credit));
                    credit_linear.setBackgroundColor(myContext.getResources().getColor(R.color.payment_dark));
                    knet_tv.setTextColor(myContext.getResources().getColor(R.color.payment_txt));
                    knet_img.setImageResource((R.mipmap.credit_inactive));
                    knet_linear.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return v;
    }

//    private void tabview() {
//        RelativeLayout relativeLayout1 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab_white, null);
//        TextView tabTwo = (TextView) relativeLayout1.findViewById(R.id.tab);
//        //   ImageView img1=(ImageView)relativeLayout1.findViewById(R.id.icon);
//        tabTwo.setText(R.string.knet);
//        //    img1.setImageResource(R.drawable.credit_card);
//        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.credit_card, 0, 0);
//        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout1));
//
//        RelativeLayout relativeLayout2 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab_white, null);
//        TextView tab2 = (TextView) relativeLayout2.findViewById(R.id.tab);
//        //   ImageView img2=(ImageView)relativeLayout2.findViewById(R.id.icon);
//        //   img2.setImageResource(R.drawable.credit_card);
//        tab2.setText(R.string.credit_card);
//         tab2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.credit_card, 0, 0);
//        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout2));
//    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }
}