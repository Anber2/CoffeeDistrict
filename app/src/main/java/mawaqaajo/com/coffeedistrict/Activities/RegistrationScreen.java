package mawaqaajo.com.coffeedistrict.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

public class RegistrationScreen extends AppCompatActivity {
    Button submitBTN;
    EditText fnameTXT,lnameTXT, emailTXT, passTXT, confirmPassTXT,mobileTXT;
    Spinner genderSpin;
    String genderID="0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_account_layout);
        fnameTXT = (EditText) findViewById(R.id.f_nameTXT_singup);
        lnameTXT= (EditText) findViewById(R.id.l_nameTXT_singup);
        passTXT = (EditText) findViewById(R.id.userPassTXT_signup);
        confirmPassTXT = (EditText) findViewById(R.id.confirmPassTXT_signup);
        emailTXT = (EditText) findViewById(R.id.useremail_signupTXT);
        mobileTXT= (EditText) findViewById(R.id.mobile_signupTXT);
        genderSpin= (Spinner) findViewById(R.id.genderSpinner);
        submitBTN = (Button) findViewById(R.id.submitRegisterBTN);


        String[] list  = getResources().getStringArray(R.array.gender_arrays);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpin.setAdapter(dataAdapter);
        //genderSpin.setSelection(0);


        submitBTN.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sessionClass.startSpinwheel(RegistrationScreen.this,false,true);
               new Thread(new Runnable() {
                   @Override
                   public void run()
                   {
                       try
                       {
                           JSONObject obj = new JSONObject();
                           obj.putOpt("first_name", fnameTXT.getText().toString());
                           obj.putOpt("last_name", lnameTXT.getText().toString());
                           obj.putOpt("email", emailTXT.getText().toString());
                           obj.putOpt("password", passTXT.getText().toString());
                           obj.putOpt("password_confirmation", confirmPassTXT.getText().toString());
                           obj.putOpt("phone", mobileTXT.getText().toString());
                           obj.putOpt("gender",genderID);
                           obj.putOpt("agreement", "1");

                           postRegistrationData(urlClass.registerURL,obj);
                       }
                       catch (Exception xx)
                       {}
                   }
               }).start();
            }
        });

        genderSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                genderID=String.valueOf(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private boolean checkRule() {
        if (fnameTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the first name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lnameTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the last name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mobileTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the mobile", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (emailTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passTXT.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!passTXT.getText().toString().trim().equals(confirmPassTXT.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), " password not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void postRegistrationData(String urlPost, final JSONObject jsonObject) {

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
                                        Toast.makeText(getApplicationContext(), result.get("status").toString(), Toast.LENGTH_SHORT).show();
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Please try another email", Toast.LENGTH_SHORT).show();

                                    }

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
                    Toast.makeText(getApplicationContext(), "Failed :"+xx, Toast.LENGTH_SHORT).show();
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