package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sharedPrefsUtils;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.search_fake;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

public class Select_location extends Fragment implements View.OnClickListener {
    Button  pickup;
    Spinner locationSpin;
    private ArrayList area;
    private MainActivity myContext;
    String[] location_list;
    String[] location_list_Id;
    static String areaID="";
    static String deleverType="2";
    sharedPrefsUtils sharedPref;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.select_location_fragment, container, false);

        pickup = (Button) v.findViewById(R.id.pickup);
        locationSpin= (Spinner) v.findViewById(R.id.choose_locationSpinner);
        locationSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                areaID=location_list_Id[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        toolbar_title.setVisibility(View.VISIBLE);
        toolbar_title.setText(getString(R.string.slct_u_location));
        imgmenu.setVisibility(View.GONE);
        filterimg.setVisibility(View.GONE);
        search_fake.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);

        pickup.setOnClickListener(this);

        sessionClass.startSpinwheel(getActivity(),false,true);
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    getAllAreaData(urlClass.getAllAreaURL);
                }
                catch (Exception xx)
                {}
            }
        }).start();

        return v;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.pickup:
                if(!areaID.equalsIgnoreCase(""))
                {
                    FragmentManager fragManager = myContext.getSupportFragmentManager();
                    FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.main_container, new HomeFragment(), "HomeFragment");
                    mFragmentTransaction.addToBackStack("Select_location");
                    mFragmentTransaction.commit();
                    break;
                }
                else
                {
                    Toast.makeText(getActivity(), "Please select an area", Toast.LENGTH_SHORT).show();

                }
        }
    }



    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
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
                            try
                            {
                                if (response != null)
                                {
                                    JSONObject result=new JSONObject(response);
                                    JSONArray arrayObj=new JSONArray(result.getJSONArray("area").toString());

                                    List<String> location = new ArrayList<String>();
                                    List<String> location_id = new ArrayList<String>();

                                    for(int i=0;i<arrayObj.length();i++)
                                    {
                                        JSONObject obj=new JSONObject(arrayObj.getJSONObject(i).toString());
                                        String id=obj.getString("id");
                                        String Name=obj.getString("name_en");
                                        location_id.add(id);
                                        location.add(Name);

                                    }

                                    location_list=new String[location.size()];
                                    location.toArray(location_list);

                                    location_list_Id=new String[location_id.size()];
                                    location_id.toArray(location_list_Id);

                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, location_list);
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    locationSpin.setAdapter(dataAdapter);

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
}