package mawaqaajo.com.coffeedistrict.FireBase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


import org.json.JSONException;
import org.json.JSONObject;

import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;


/**
 * Created by JijoCJ on 12/20/2016.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = MyFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(sessionClass.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    public void sendRegistrationToServer(final String token) {
        // sending fcm token to server
        try {
//            Log.e(TAG, "sendRegistrationToServer: " + token);
//            if(!PreferenceUtil.getUserId(this).equals(""))
//            {
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.putOpt(sessionClass.DEVICE_ID, Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID));
//                jsonObject.putOpt(sessionClass.DEVICE_TOKEN, token);
//                jsonObject.putOpt(sessionClass.DEVICE_PLATFORM, 0);
//                jsonObject.putOpt(sessionClass.user_id, PreferenceUtil.getUserId(this));
//                Log.e(TAG, "Json Req:"+ jsonObject);
//                if (VolleyUtils.volleyEnabled) {
//                    //Activity.BaseFragment.startSpinwheel(false, true);
//
//                    RequestQueue queue = VolleyUtils.getRequestQueue();
//                    JsonObjectRequest jsObjRequest = new JsonObjectRequest(
//                            Request.Method.POST, AppConstants.HP_DEVICE_REGISTRATION, jsonObject,
//                            new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    Log.e(TAG,
//                                            "Response : Device Registration" + response.toString());
//                                    try {
//                                        Log.e(TAG, response.getString(AppConstants.MESSAGE));
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    //onSaveMyAccountInfoSuccessfully(response);
//                                }
//
//
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e(TAG, "Response : Error Device Registration"+error.getMessage());
//
//                        }
//                    });
//                    jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
//                            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, 2, 2));
//                    queue.add(jsObjRequest);
//
//
//                }
 //           }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void storeRegIdInPref(String token)
    {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("devicetoken", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", token);
        editor.commit();
    }

    public interface DeviceRegistrationListener{
        public void onDeviceRegistration(String token);
    }

}