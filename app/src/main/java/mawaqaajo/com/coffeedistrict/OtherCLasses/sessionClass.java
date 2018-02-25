package mawaqaajo.com.coffeedistrict.OtherCLasses;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.widget.ProgressBar;

import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/1/2017.
 */

public class sessionClass
{
    public static  String cafeID="";
    public static  String loacalCurrent="";
    public static  String user_id="";
    public static  String user_email="";
    public static  String user_password="";
    public static  String blogID="";
     public static  int   appTimeOut=8000;

    public static double curentLongtit=0.0;
    public static double curentLat=0.0;

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    public static final String SHARED_PREF = "ah_firebase";
    public static final String DEVICE_ID = "DeviceId";
    public static final String DEVICE_TOKEN = "DeviceToken";
    public static final String DEVICE_PLATFORM = "Platform";



    public static Dialog spinWheelDialog = null;
    public static void startSpinwheel(Context context, boolean setDefaultLifetime, boolean isCancelable) {

        if (spinWheelDialog != null && spinWheelDialog.isShowing())
            return;
        spinWheelDialog = new Dialog(context, R.style.wait_spinner_style);
        ProgressBar progressBar = new ProgressBar(context);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        spinWheelDialog.addContentView(progressBar, layoutParams);
        spinWheelDialog.setCancelable(isCancelable);
        spinWheelDialog.show();

        Handler spinWheelTimer = new Handler();
        spinWheelTimer.removeCallbacks(dismissSpinner);
        if (setDefaultLifetime) // If requested for default dismiss time.
            spinWheelTimer.postAtTime(dismissSpinner, SystemClock.uptimeMillis() + 1000);

        spinWheelDialog.setCanceledOnTouchOutside(false);
    }
    static Runnable dismissSpinner = new Runnable() {

        @Override
        public void run() {
            stopSpinWheel();
        }

    };

    public static void stopSpinWheel() {
        if (spinWheelDialog != null)
            try
            {
                spinWheelDialog.dismiss();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        spinWheelDialog = null;
    }
}
