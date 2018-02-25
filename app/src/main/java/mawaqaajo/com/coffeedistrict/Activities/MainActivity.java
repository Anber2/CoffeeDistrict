package mawaqaajo.com.coffeedistrict.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import mawaqaajo.com.coffeedistrict.Fragments.About;
import mawaqaajo.com.coffeedistrict.Fragments.CatringFragment;
import mawaqaajo.com.coffeedistrict.Fragments.CoffeeBlogFragment;
import mawaqaajo.com.coffeedistrict.Fragments.ContactUsFragment;
import mawaqaajo.com.coffeedistrict.Fragments.FaqFragment;
import mawaqaajo.com.coffeedistrict.Fragments.HomeFragment;
import mawaqaajo.com.coffeedistrict.Fragments.MyCart;
import mawaqaajo.com.coffeedistrict.Fragments.MyOrder;
import mawaqaajo.com.coffeedistrict.Fragments.MyProfileFragment;
import mawaqaajo.com.coffeedistrict.Fragments.OffersFragment;
import mawaqaajo.com.coffeedistrict.Fragments.ScheduleFragment;
import mawaqaajo.com.coffeedistrict.Fragments.Select_location;
import mawaqaajo.com.coffeedistrict.Fragments.SettingFragment;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NestedScrollView mainscrollView;
    TextView loginBTN, emailTXTHeader;
    ImageView toolbar_img;
    DrawerLayout drawer;
    public static Toolbar toolbar;
    public static ImageView imgmenu;
    public static ImageView filterimg;
    public static TextView toolbar_title;
    public static ImageView search_fake;
    public static FrameLayout filter_drawer;
    public static SearchView searchView;
    private View contentView;
    private static final float END_SCALE = 0.7f;


    FragmentManager mFragmentManager;

    FragmentTransaction mFragmentTransaction;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FragmentTransaction tx;
        TextView loginBTN, emailTXTHeader;
        ProgressDialog progressBar;
        int im;
        Fragment fragment;
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        imgmenu = (ImageView) findViewById(R.id.imgmenu);
        search_fake = (ImageView) findViewById(R.id.search_fake);
        filterimg = (ImageView) findViewById(R.id.filter);
        filter_drawer = (FrameLayout) findViewById(R.id.filter_drawer);

//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        toggle.setDrawerIndicatorEnabled(false);
//        drawer.addDrawerListener(toggle);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        contentView = findViewById(R.id.content);
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                     @Override
                                     public void onDrawerSlide(View drawerView, float slideOffset) {
                                         //    labelView.setVisibility(slideOffset > 0 ? View.VISIBLE : View.GONE);
                                         // Scale the View based on current slide offset
                                         final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                                         final float offsetScale = 1 - diffScaledOffset;
                                         contentView.setScaleX(offsetScale);
                                         contentView.setScaleY(offsetScale);

                                         // Translate the View, accounting for the scaled width
                                         final float xOffset = drawerView.getWidth() * slideOffset;
                                         final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                                         final float xTranslation = xOffset - xOffsetDiff;
                                         contentView.setTranslationX(xTranslation);
                                     }

                                     @Override
                                     public void onDrawerClosed(View drawerView) {
                                         // labelView.setVisibility(View.GONE);
                                     }
                                 }
        );

        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // navigationView.inflateHeaderView(R.layout.nav_header_main);
        View header = navigationView.getHeaderView(0);
        loginBTN = (TextView) header.findViewById(R.id.loginBTN_header);
        emailTXTHeader = (TextView) header.findViewById(R.id.emailTXT_header);
        if (sessionClass.user_id.equalsIgnoreCase("")) {
            emailTXTHeader.setVisibility(View.GONE);
        } else {
            emailTXTHeader.setVisibility(View.VISIBLE);
            emailTXTHeader.setText(sessionClass.user_email);
            loginBTN.setText("Logout");
        }

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionClass.user_id.equalsIgnoreCase("")) {
                    startActivity(new Intent(MainActivity.this, LoginScreen.class));
                } else {
                    sessionClass.user_email = "";
                    sessionClass.user_id = "";
                }
            }
        });

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.main_container, new Select_location(), "Select_location").commit();

        imgmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                drawer.openDrawer(GravityCompat.START);
            }
        });

        searchView = (SearchView) findViewById(R.id.serach);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar_title.setVisibility(View.GONE);
                filterimg.setVisibility(View.GONE);
                search_fake.setVisibility(View.VISIBLE);
                imgmenu.setVisibility(View.GONE);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                toolbar_title.setVisibility(View.VISIBLE);
                filterimg.setVisibility(View.VISIBLE);
                search_fake.setVisibility(View.GONE);
                imgmenu.setVisibility(View.VISIBLE);
                return false;
            }
        });

//        search_fake.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                searchView.
//            }
//        });

        filterimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.filter_dialog);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView close = (ImageView) dialog.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (backStackEntryCount == 0) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new HomeFragment(), "HomeFragment");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        } else if (id == R.id.nav_cart) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new MyCart(), "MyCart");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_login) {
            Intent i = new Intent(this, LoginScreen.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        } else if (id == R.id.nav_order) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new MyOrder(), "MyOrder");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_profile) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new MyProfileFragment(), "MyProfileFragment");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_setting) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new SettingFragment(), "SettingFragment");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_aboutapp) {
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new About(), "About");
            mFragmentTransaction.addToBackStack(null);
            mFragmentTransaction.commit();
        }
//        else if (id == R.id.nav_faq) {
//
//        }
        else if (id == R.id.nav_catring) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new CatringFragment(), "CatringFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_offers) {

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new OffersFragment(), "OffersFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_coffeblog) {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new CoffeeBlogFragment(), "CoffeeBlogFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();

        } else if (id == R.id.nav_contactus) {

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new ContactUsFragment(), "ContactUsFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();

        }
        else if (id == R.id.nav_policy)
        {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new ScheduleFragment(), "ScheduleFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();
        }
        else if (id == R.id.nav_faq)
        {
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.main_container, new FaqFragment(), "FaqFragment");
            mFragmentTransaction.addToBackStack("Select_location");
            mFragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}