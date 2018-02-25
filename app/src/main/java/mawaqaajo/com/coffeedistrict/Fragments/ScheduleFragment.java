package mawaqaajo.com.coffeedistrict.Fragments;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import mawaqaajo.com.coffeedistrict.Adapters.ScheduleAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.ScheduleModle;

import static java.security.Policy.getPolicy;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 12/31/2017.
 */

public class ScheduleFragment extends Fragment {

    public static JSONArray timesArr;

    String scheduleTime = "[\"08:00 am\",\"08:20 am\",\"08:40 am\",\"09:00 am\",\"09:20\",\"09:40 am\",\"10:00 am\",\"10:20 am\",\"10:40 am\",\"11:00 am\",\"11:20 am\",\"11:40 am\",\"12:00 pm\",\"12:20 pm\",\"12:40 pm\",\"01:00 pm\",\"01:20 pm\",\"01:40 pm\",\"02:00 pm\",\"02:20 pm\",\"02:40 pm\",\"03:00 pm\",\"03:20 pm\",\"03:40 pm\",\"04:00 pm\",\"04:20 pm\",\"04:40 pm\",\"05:00 pm\",\"05:20 pm\",\"05:40 pm\",\"06:00 pm\",\"06:20 pm\",\"06:40 pm\",\"07:00 pm\",\"07:20 pm\",\"07:40 pm\",\"08:00 pm\",\"08:20 pm\",\"08:40 pm\",\"09:00 pm\",\"09:20 pm\",\"09:40 pm\",\"10:00 pm\",\"10:20 pm\",\"10:40 pm\",\"11:00 pm\",\"11:20 pm\",\"11:40 pm\"]";
    ;
    ListView listView_Schedule;
    ScheduleAdapter scheduleAdapter;
    ScheduleModle scheduleModle;
    Calendar myCalendar;
    TextView textView_selectWeek;
    String date, dateCurrent;
    private ArrayList<ScheduleModle> scheduleModleArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.schedule_fragment_layout, container, false);
        toolbar_title.setText(getString(R.string.contact_us));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);

        try {

            timesArr = new JSONArray(scheduleTime.toString());

        } catch (Exception xx) {
        }


        initiate(v);

        getPolicy();

        getScheduleList();
        return v;
    }


    private void getScheduleList() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String myFormat = "yyyy/MM/dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

                    dateCurrent = sdf.format(myCalendar.getTime());
                    /*if (!date.equals("null")) {
                        dateCurrent = date;
                    }*/


                    getScheduleListReq(urlClass.scheduleDataAPI + dateCurrent);
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void getScheduleListReq(String urlPost) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {


            queue = Volley.newRequestQueue(getActivity());

            stringRequest = new StringRequest(Request.Method.GET, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject result = new JSONObject(response);

                                if (response != null) {
                                    scheduleModleArrayList = new ArrayList<ScheduleModle>();


                                    JSONArray arrayObj2 = result.getJSONArray("selected_date");


                                    for (int j = 0; j < arrayObj2.length(); j++) {

                                        scheduleModle = new ScheduleModle("", arrayObj2.get(j).toString());
                                        scheduleModleArrayList.add(scheduleModle);
                                    }

                                    scheduleAdapter = new ScheduleAdapter(getActivity(), scheduleModleArrayList);
                                    listView_Schedule.setAdapter(scheduleAdapter);


                                    sessionClass.stopSpinWheel();

                                }
                                sessionClass.stopSpinWheel();


                            } catch (Exception xx) {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
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


    public void initiate(View v) {

        scheduleModleArrayList = new ArrayList<ScheduleModle>();
        scheduleModleArrayList.add(new ScheduleModle("0", "85/95/7899", null));

        listView_Schedule = (ListView) v.findViewById(R.id.listView_Schedule);
        textView_selectWeek = (TextView) v.findViewById(R.id.textView_selectWeek);
        listView_Schedule.setAdapter(new ScheduleAdapter(getActivity(), scheduleModleArrayList));

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


        textView_selectWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {

        String myFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        date = sdf.format(myCalendar.getTime());
        //editText_date_catring.setText(sdf.format(myCalendar.getTime()));

        dateCurrent = date;

        listView_Schedule.setAdapter(null);

        getScheduleList();
    }

}
