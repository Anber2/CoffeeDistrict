package mawaqaajo.com.coffeedistrict.Fragments;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mawaqaajo.com.coffeedistrict.Adapters.CoffeeShopAdapter;
import mawaqaajo.com.coffeedistrict.DataClasses.CoffeeShopData;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 8/6/2017.
 */

public class CatringFragment extends Fragment {

    static String areaID = "";
    CoffeeShopData coffeeShopData;
    CoffeeShopAdapter coffeeShopAdapter;
    ArrayList<CoffeeShopData> coffeeShopDataArrayList;
    Spinner spinner_coffeeShop, spinner_cateringArea;
    Button button_submit_catring;
    Calendar myCalendar;
    EditText editText_fillName, editText_phone, editText_email, editText_noOfGuests, editText_date_catring, editText_time_catring, editText_additional_info;
    String[] location_list;
    String[] location_list_Id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.catring_fragment_layout, container, false);
        initiate(v);
        toolbar_title.setText(getString(R.string.catering));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);


        getAllArea();


        return v;
    }

    void getCoffeeShops() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String url = urlClass.getCofeShopListURL + "&area_id=" + areaID + "+&deliver_type=" + 0 + "";
                    getCafeShopData(url);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void getAllArea() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getAllAreaData(urlClass.getAllAreaURL);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void getAllAreaData(String urlPost) {

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
                                    JSONArray arrayObj = new JSONArray(result.getJSONArray("area").toString());

                                    List<String> location = new ArrayList<String>();
                                    List<String> location_id = new ArrayList<String>();

                                    for (int i = 0; i < arrayObj.length(); i++) {
                                        JSONObject obj = new JSONObject(arrayObj.getJSONObject(i).toString());
                                        String id = obj.getString("id");
                                        String Name = obj.getString("name_en");
                                        location_id.add(id);
                                        location.add(Name);

                                    }

                                    location_list = new String[location.size()];
                                    location.toArray(location_list);

                                    location_list_Id = new String[location_id.size()];
                                    location_id.toArray(location_list_Id);

                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, location_list);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    spinner_cateringArea.setAdapter(dataAdapter);

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

    private void getCafeShopData(String urlPost) {

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
                                    coffeeShopDataArrayList = new ArrayList<>();
                                    JSONObject result = new JSONObject(response);
                                    JSONArray arrayObj = new JSONArray(result.getJSONArray("shopslist").toString());

                                    for (int i = 0; i < arrayObj.length(); i++) {
                                        JSONObject obj = new JSONObject(arrayObj.getJSONObject(i).toString());
                                        String id = obj.getString("id");
                                        String Name = obj.getString("name_en");

                                        coffeeShopData = new CoffeeShopData(id, Name);
                                        coffeeShopDataArrayList.add(coffeeShopData);

                                    }

                                    coffeeShopAdapter = new CoffeeShopAdapter(getActivity(), coffeeShopDataArrayList);

                                    spinner_coffeeShop.setAdapter(coffeeShopAdapter);


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

    public void getCatering() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject obj = new JSONObject();

                    obj.putOpt("name", editText_fillName.getText().toString());
                    obj.putOpt("email", editText_email.getText().toString());
                    obj.putOpt("phone", editText_phone.getText().toString());
                    obj.putOpt("shop_id", "1");
                    obj.putOpt("people_number", editText_noOfGuests.getText().toString());
                    obj.putOpt("book_date", editText_date_catring.getText().toString());
                    //obj.putOpt("book_time", editText_time_catring.getText().toString());
                    obj.putOpt("location", areaID);
                    obj.putOpt("request", editText_additional_info.getText().toString());


                    postCateringData(urlClass.cataring, obj);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void postCateringData(String urlPost, final JSONObject jsonObject) {

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
                                        Toast.makeText(getActivity(), result.get("message").toString(), Toast.LENGTH_SHORT).show();

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


    public void initiate(View v) {

        editText_fillName = (EditText) v.findViewById(R.id.editText_fillName);
        editText_phone = (EditText) v.findViewById(R.id.editText_phone);
        editText_email = (EditText) v.findViewById(R.id.editText_email);
        editText_noOfGuests = (EditText) v.findViewById(R.id.editText_noOfGuests);
        editText_date_catring = (EditText) v.findViewById(R.id.editText_date_catring);
        editText_time_catring = (EditText) v.findViewById(R.id.editText_time_catring);
        editText_additional_info = (EditText) v.findViewById(R.id.editText_additional_info);
        spinner_coffeeShop = (Spinner) v.findViewById(R.id.spinner_coffeeShop);
        button_submit_catring = (Button) v.findViewById(R.id.button_submit_catring);
        spinner_cateringArea = (Spinner) v.findViewById(R.id.spinner_cateringArea);
        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editText_date_catring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editText_time_catring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText_time_catring.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        spinner_cateringArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaID = location_list_Id[position].toString();

                getCoffeeShops();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button_submit_catring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CateringDataAuthorization()) {
                    getCatering();
                }

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {

        String myFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        editText_date_catring.setText(sdf.format(myCalendar.getTime()));
    }

    boolean CateringDataAuthorization() {

        if (editText_fillName.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Name is empty", Toast.LENGTH_LONG).show();
            editText_fillName.requestFocus();
            return false;
        }
        if (editText_phone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Phone is empty", Toast.LENGTH_LONG).show();
            editText_phone.requestFocus();
            return false;
        }
        if (editText_email.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Email is empty", Toast.LENGTH_LONG).show();
            editText_email.requestFocus();
            return false;
        }
        if (editText_noOfGuests.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Number Of Gusts is empty", Toast.LENGTH_LONG).show();
            editText_noOfGuests.requestFocus();
            return false;
        }
        if (editText_date_catring.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Date is empty", Toast.LENGTH_LONG).show();
            editText_date_catring.requestFocus();
            return false;
        }
        if (editText_time_catring.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Time is empty", Toast.LENGTH_LONG).show();
            editText_time_catring.requestFocus();
            return false;
        }
        if (editText_time_catring.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Time is empty", Toast.LENGTH_LONG).show();
            editText_time_catring.requestFocus();
            return false;
        }

        return true;
    }


}
