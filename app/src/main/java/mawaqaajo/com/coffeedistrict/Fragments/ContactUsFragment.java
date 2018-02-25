package mawaqaajo.com.coffeedistrict.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 8/8/2017.
 */


public class ContactUsFragment extends Fragment {
    TextView contact_us_intru_textView, contact_us_info_textView;

    EditText contact_us_editText_firstName, contact_us_editText_lastName, contact_us_editText_email, contact_us_editText_phone, contact_us_editText_message;
    Button contact_us_button_submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.contact_us_fragment_layout, container, false);
        toolbar_title.setText(getString(R.string.contact_us));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);

        initiate(v);
        return v;
    }

    public void initiate(View v) {

        contact_us_editText_firstName = (EditText) v.findViewById(R.id.contact_us_editText_firstName);
        contact_us_editText_lastName = (EditText) v.findViewById(R.id.contact_us_editText_lastName);
        contact_us_editText_email = (EditText) v.findViewById(R.id.contact_us_editText_email);
        contact_us_editText_phone = (EditText) v.findViewById(R.id.contact_us_editText_phone);
        contact_us_editText_message = (EditText) v.findViewById(R.id.contact_us_editText_message);
        contact_us_button_submit = (Button) v.findViewById(R.id.contact_us_button_submit);

        contact_us_button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(contactUsAuthorization()){

                    getContactUS();
                }
            }
        });

    }

    private void getContactUS() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    JSONObject obj = new JSONObject();

                    obj.putOpt("first_name", contact_us_editText_firstName.getText().toString());
                    obj.putOpt("last_name", contact_us_editText_lastName.getText().toString());
                    obj.putOpt("email", contact_us_editText_email.getText().toString());
                    obj.putOpt("phone", contact_us_editText_phone.getText().toString());
                    obj.putOpt("comment", contact_us_editText_message.getText().toString());



                    postContactUSData(urlClass.contactUs, obj);

                } catch (Exception xx) {
                }
            }
        }).start();
    }


    private void postContactUSData(String urlPost, final JSONObject jsonObject) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {


            queue = Volley.newRequestQueue(getActivity());

            stringRequest = new StringRequest(Request.Method.POST, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject result = new JSONObject(response);

                                if (result != null) {
                                    if (result.get("status").equals("success")) {
                                        Toast.makeText(getActivity(), result.get("status").toString(), Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getActivity(), result.get("error_message").toString(), Toast.LENGTH_SHORT).show();

                                    }

                                }


                            } catch (Exception xx) {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getActivity(), "Failed :" + xx, Toast.LENGTH_SHORT).show();
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


    private boolean contactUsAuthorization() {

        if (contact_us_editText_firstName.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "First Name is empty", Toast.LENGTH_LONG).show();
            contact_us_editText_firstName.requestFocus();
            return false;
        }
        if (contact_us_editText_lastName.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Last Name is empty", Toast.LENGTH_LONG).show();
            contact_us_editText_lastName.requestFocus();
            return false;
        }
        if (contact_us_editText_email.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Email is empty", Toast.LENGTH_LONG).show();
            contact_us_editText_email.requestFocus();
            return false;
        }
        if (contact_us_editText_phone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Phone is empty", Toast.LENGTH_LONG).show();
            contact_us_editText_phone.requestFocus();
            return false;
        }
        if (contact_us_editText_message.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Message is empty", Toast.LENGTH_LONG).show();
            contact_us_editText_message.requestFocus();
            return false;
        }


        return true;
    }
}
