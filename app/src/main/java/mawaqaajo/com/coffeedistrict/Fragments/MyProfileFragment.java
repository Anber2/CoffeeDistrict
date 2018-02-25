package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;
import static mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass.user_id;


public class MyProfileFragment extends Fragment {
    private static MainActivity mContext;
    View v;
    TextView terms, textView_changePass;
    FragmentTransaction tx;
    ImageView imageView_myProfile_addAddress, imageView_edit;
    Button button_viewAddresses, submitRegisterBTN;
    EditText editText_firstName, editText_lastName, editText_birthDay, editText_phone, editText_email, editText_gender;
    Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        mContext = (MainActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.my_profile_fragment, container, false);

        defineView();

        getUserProfile();


        return v;
    }

    private void getUserProfile() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String url = urlClass.getUserProfile + user_id;
                    getUserProfileReq(url);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void getUserProfileReq(String urlPost) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {

            queue = Volley.newRequestQueue(getActivity());

            stringRequest = new StringRequest(Request.Method.GET, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                if (response != null) {

                                    JSONObject result = new JSONObject(response);

                                    JSONObject jsonObject = result.getJSONObject("UserProfile");

                                    // editText_firstName, editText_lastName, editText_birthDay, editText_phone, editText_email, editText_gender
                                    editText_firstName.setText(jsonObject.getString("first_name"));
                                    editText_lastName.setText(jsonObject.getString("last_name"));
                                    editText_birthDay.setText(jsonObject.getString("birth_day"));
                                    editText_phone.setText(jsonObject.getString("phone"));
                                    editText_email.setText(jsonObject.getString("email"));

                                    if (jsonObject.getString("gender").equals("0")) {
                                        editText_gender.setText("Male");

                                    } else {
                                        editText_gender.setText("Female");
                                    }


                                    sessionClass.stopSpinWheel();

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
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                    sessionClass.stopSpinWheel();


                }


            })


            {


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


    private void defineView() {
        terms = (TextView) v.findViewById(R.id.terms);
        textView_changePass = (TextView) v.findViewById(R.id.textView_changePass);

        imageView_edit = (ImageView) v.findViewById(R.id.imageView_edit);

        editText_firstName = (EditText) v.findViewById(R.id.editText_firstName);
        editText_firstName.setEnabled(false);

        editText_lastName = (EditText) v.findViewById(R.id.editText_lastName);
        editText_lastName.setEnabled(false);

        editText_birthDay = (EditText) v.findViewById(R.id.editText_birthDay);
        editText_birthDay.setEnabled(false);

        editText_phone = (EditText) v.findViewById(R.id.editText_phone);
        editText_phone.setEnabled(false);

        editText_email = (EditText) v.findViewById(R.id.editText_email);
        editText_email.setEnabled(false);

        editText_gender = (EditText) v.findViewById(R.id.editText_gender);
        editText_gender.setEnabled(false);

        submitRegisterBTN = (Button) v.findViewById(R.id.submitRegisterBTN);
        submitRegisterBTN.setVisibility(View.INVISIBLE);


        imageView_myProfile_addAddress = (ImageView) v.findViewById(R.id.imageView_myProfile_addAddress);
        button_viewAddresses = (Button) v.findViewById(R.id.button_viewAddresses);
        toolbar_title.setText(getString(R.string.my_profile));
        filter_drawer.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);

        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = mContext.getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new PolicyFragment(), "PolicyFragment");
                mFragmentTransaction.commit();
                mFragmentTransaction.addToBackStack("MyProfileFragment");
            }
        });

        imageView_myProfile_addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragManager = mContext.getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new AddAddressFragment(), "AddAddressFragment");
                mFragmentTransaction.commit();
                mFragmentTransaction.addToBackStack("MyProfileFragment");

            }
        });

        button_viewAddresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager fragManager = mContext.getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new AddressListFragment(), "AddressListFragment");
                mFragmentTransaction.commit();
                mFragmentTransaction.addToBackStack("MyProfileFragment");

            }
        });

        imageView_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRegisterBTN.setVisibility(View.VISIBLE);


                editText_firstName.setEnabled(true);

                editText_lastName.setEnabled(true);

                editText_birthDay.setEnabled(true);

                editText_phone.setEnabled(true);


                editText_gender.setEnabled(true);

            }


        });

        submitRegisterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitProfile();
            }
        });

        textView_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleChangePassword();
            }
        });
    }

    private void handleChangePassword() {

          dialog = new Dialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.change_password_dialog);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final EditText oldPassword = (EditText) dialog.findViewById(R.id.editText_oldPass);
        final EditText newPassword = (EditText) dialog.findViewById(R.id.editText_newPass);
        final EditText newPassConfirm = (EditText) dialog.findViewById(R.id.editText_confirmNewPass);


        Button submit = (Button) dialog.findViewById(R.id.button_changePassSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(oldPassword.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Please enter old password", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(newPassword.getText().toString().equalsIgnoreCase(""))
                {
                    Toast.makeText(getActivity(), "Please enter new password", Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(!newPassConfirm.getText().toString().equalsIgnoreCase(newPassword.getText().toString()))
                {
                    Toast.makeText(getActivity(), "New password does not match confirmed password", Toast.LENGTH_SHORT).show();
                    return ;
                }
                sessionClass.startSpinwheel(getActivity(),false,true);
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try
                        {
                           JSONObject obj = new JSONObject();
                            obj.putOpt("user_id",sessionClass.user_id);
                            obj.putOpt("new_pass",newPassConfirm.getText().toString());

                            postchangePasswordData(urlClass.changePasswordURL,obj);
                        }
                        catch (Exception xx)
                        {}
                    }
                }).start();
            }
        });
        dialog.show();

    }

    private void submitProfile() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    JSONObject obj = new JSONObject();

                    obj.putOpt("user_id", sessionClass.user_id);
                    obj.putOpt("first_name", editText_firstName.getText().toString());
                    obj.putOpt("last_name", editText_lastName.getText().toString());
                    obj.putOpt("gender", editText_gender.getText().toString());
                    obj.putOpt("phone", editText_phone.getText().toString());
                    obj.putOpt("birth_day", editText_birthDay.getText().toString());

                    String url = urlClass.updateUserProfile;
                    updateUserProfileReq(url, obj);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void updateUserProfileReq(String urlPost, final JSONObject jsonObject) {

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

                                        submitRegisterBTN.setVisibility(View.INVISIBLE);



                                        editText_firstName.setEnabled(false);

                                        editText_lastName.setEnabled(false);

                                        editText_birthDay.setEnabled(false);

                                        editText_phone.setEnabled(false);
                                        editText_gender.setEnabled(false);
                                    } else {
                                        Toast.makeText(getActivity(), result.get("status").toString(), Toast.LENGTH_SHORT).show();

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
    private void postchangePasswordData(String urlPost, final JSONObject jsonObject) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {


            queue = Volley.newRequestQueue(getActivity());

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
                                        Toast.makeText(getActivity(), result.get("messages").toString(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();

                                    }
                                    else
                                    {
                                        Toast.makeText(getActivity(), "Failed to login", Toast.LENGTH_SHORT).show();

                                    }
                                    sessionClass.stopSpinWheel();

                                }



                            }
                            catch (Exception xx)
                            {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }

                            sessionClass.stopSpinWheel();

                        }

                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
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