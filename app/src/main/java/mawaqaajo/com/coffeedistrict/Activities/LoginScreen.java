package mawaqaajo.com.coffeedistrict.Activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mawaqaajo.com.coffeedistrict.OtherCLasses.appConstants;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sharedPrefsUtils;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

public class LoginScreen extends AppCompatActivity {
    EditText emailTXT, passwordTXT;
    ProgressDialog progressBar;
    TextView signUPBtn, gestBTN, forget_password;
    Button loginBTN;
     Dialog dialog;
    sharedPrefsUtils  sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        emailTXT = (EditText) findViewById(R.id.useremail_logiTXT);
        passwordTXT = (EditText) findViewById(R.id.userPassTXT_login);
        loginBTN = (Button) findViewById(R.id.loginBTN);
        signUPBtn = (TextView) findViewById(R.id.signUPBTN);
        gestBTN = (TextView) findViewById(R.id.gestContinueBTN);
        forget_password = (TextView) findViewById(R.id.forget_password);

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                handleForgerPassword();


            }
        });

        gestBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginScreen.this, MainActivity.class));
            }
        });

        signUPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this, RegistrationScreen.class));

            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleLogIn();

            }
        });
    }

    private void handleLogIn() {
        if (checkRule()) {

            sessionClass.startSpinwheel(LoginScreen.this,false,true);
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    try
                    {
                        JSONObject obj = new JSONObject();
                        obj.putOpt("email", emailTXT.getText().toString());
                        obj.putOpt("password", passwordTXT.getText().toString());

                        postLoginData(urlClass.loginURL,obj);
                    }
                    catch (Exception xx)
                    {}
                }
            }).start();
        }
    }

    private void handleForgerPassword() {

        dialog = new Dialog(LoginScreen.this, android.R.style.Theme_Holo_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.forget_password_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final EditText emailTXT_forget= (EditText) dialog.findViewById(R.id.emailTXT_forgetPass);
        Button reset = (Button) dialog.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(emailTXT_forget.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "Please check the email", Toast.LENGTH_SHORT).show();
                    return ;
                }
                sessionClass.startSpinwheel(LoginScreen.this,false,true);
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try
                        {
                            JSONObject obj = new JSONObject();
                            obj.putOpt("email", emailTXT_forget.getText().toString());

                            postRecoverPassData(urlClass.recoverPasswordURL,obj);
                        }
                        catch (Exception xx)
                        {}
                    }
                }).start();
            }
        });
        dialog.show();
    }

    private boolean checkRule() {
        if (emailTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passwordTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void postLoginData(String urlPost, final JSONObject jsonObject) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {


            queue = Volley.newRequestQueue(getApplicationContext());

            stringRequest = new StringRequest(Request.Method.POST, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try
                            {
                                if (response != null)
                                {
                                    JSONObject result=new JSONObject(response);
                                    if(result.get("status").equals("success"))
                                    {
                                        JSONObject userObj=new JSONObject(result.get("users_id").toString());
                                        String userID=userObj.get("id").toString();
                                        String userEmail=userObj.get("email").toString();
                                        JSONArray userType=new JSONArray(userObj.get("roles").toString());

                                        JSONObject userRuleObj=userType.getJSONObject(0);
                                        String userTypeString=userRuleObj.getString("id");
                                        sessionClass.user_id = userID;

                                        sharedPref.setStringPreference(LoginScreen.this, appConstants.userID_KEY,userID);
                                        sharedPref.setStringPreference(LoginScreen.this, appConstants.userEmail_KEY,userEmail);
                                        sharedPref.setStringPreference(LoginScreen.this, appConstants.userType_KEY,userTypeString);

                                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                                        finish();


                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();

                                    }
                                    sessionClass.stopSpinWheel();

                                }



                            }
                            catch (Exception xx)
                            {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    sessionClass.stopSpinWheel();


                }


            })


            {



                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    try {

                        Iterator<?> keys = jsonObject.keys();

                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            String value = jsonObject.getString(key);
                            params.put(key, value);

                        }


                    } catch (Exception xx) {
                        xx.toString();
                        sessionClass.stopSpinWheel();
                    }
                    return params;
                }


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {

                        String jsonString = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers));

                        return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));


                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    }
                }


            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    sessionClass.appTimeOut,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);


        } catch (Exception e) {
            sessionClass.stopSpinWheel();
            e.toString();
        }



    }

    private void postRecoverPassData(String urlPost, final JSONObject jsonObject) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {


            queue = Volley.newRequestQueue(getApplicationContext());

            stringRequest = new StringRequest(Request.Method.POST, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try
                            {
                                if (response != null)
                                {
                                    JSONObject result=new JSONObject(response);
                                    if(result.get("status").equals("success"))
                                    {
                                        Toast.makeText(getApplicationContext(), result.get("messages").toString(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Failed to login", Toast.LENGTH_SHORT).show();

                                    }
                                    sessionClass.stopSpinWheel();

                                }



                            }
                            catch (Exception xx)
                            {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    sessionClass.stopSpinWheel();


                }


            })


            {



                @Override
                protected Map<String, String> getParams() {

                    Map<String, String> params = new HashMap<String, String>();
                    try {

                        Iterator<?> keys = jsonObject.keys();

                        while (keys.hasNext()) {
                            String key = (String) keys.next();
                            String value = jsonObject.getString(key);
                            params.put(key, value);

                        }


                    } catch (Exception xx) {
                        xx.toString();
                        sessionClass.stopSpinWheel();
                    }
                    return params;
                }


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {

                        String jsonString = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers));

                        return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));


                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    }
                }


            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    sessionClass.appTimeOut,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);


        } catch (Exception e) {
            sessionClass.stopSpinWheel();
            e.toString();
        }



    }


}
