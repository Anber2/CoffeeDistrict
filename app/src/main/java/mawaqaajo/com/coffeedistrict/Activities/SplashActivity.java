package mawaqaajo.com.coffeedistrict.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.Locale;

import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.R;


public class SplashActivity extends AppCompatActivity {
    Animation anim;
    LinearLayout splashLO;
    private static int SPLASH_TIME_OUT = 2500;
    String langFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        anim= AnimationUtils.loadAnimation(this, R.anim.splashanimation);

        splashLO= (LinearLayout) findViewById(R.id.splashLO);
        splashLO.startAnimation(anim);

//        String loc = "en";
//        SharedPreferences prefs = getSharedPreferences("languagePref", MODE_PRIVATE);
//        String currentLoc = prefs.getString("local", null);
//        if (currentLoc != null)
//        {
//            loc = currentLoc;
//        }
//
//        if(loc.equalsIgnoreCase("en"))
//        {
//
//
//            Locale locale = new Locale("en");
//            Resources res = getResources();
//            DisplayMetrics dm = res.getDisplayMetrics();
//            Configuration conf = res.getConfiguration();
//            conf.locale = locale;
//            res.updateConfiguration(conf, dm);
//            onConfigurationChanged(conf);
//
//            sessionClass.loacalCurrent="en";
//
//        }
//        else if(loc.equalsIgnoreCase("ar"))
//        {
//
//
//            Locale locale = new Locale("ar");
//            Resources res = getResources();
//            DisplayMetrics dm = res.getDisplayMetrics();
//            Configuration conf = res.getConfiguration();
//            conf.locale = locale;
//            res.updateConfiguration(conf, dm);
//            onConfigurationChanged(conf);
//
//
//            sessionClass.loacalCurrent="ar";
//
//
//        }

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
