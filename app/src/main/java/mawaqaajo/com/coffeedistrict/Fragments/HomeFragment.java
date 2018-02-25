package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.Adapters.CafeListAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.coffeeModel;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.search_fake;

public class HomeFragment extends Fragment {
    View view;
    private static MainActivity myContext;
    private RecyclerView.LayoutManager mLayoutManager;

    RecyclerView coffee_shop_recycl;
    coffeeModel coffeeModel_;
    private CafeListAdapter cafeAdapter_;
    ArrayList<coffeeModel> coffeeModelArrayList_;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.home_fragment, container, false);
        searchView.setVisibility(View.VISIBLE);
        filterimg.setVisibility(View.VISIBLE);
        search_fake.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);
        coffee_shop_recycl = (RecyclerView) view.findViewById(R.id.coffee_shop_recycl);
        coffee_shop_recycl.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        coffee_shop_recycl.setLayoutManager(mLayoutManager);


        sessionClass.startSpinwheel(getActivity(),false,true);
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    String areaID=Select_location.areaID;
                    String deleverType=Select_location.deleverType;

                    String url=urlClass.getCofeShopListURL+"&area_id="+areaID+"+&deliver_type="+deleverType+"";
                    getCafeShopData(url);
                }
                catch (Exception xx)
                {}
            }
        }).start();


        return view;
    }


    public static void goToDetails()
    {
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_container, new CafeDetailsFragment(), "CafeDetailsFragment");
        mFragmentTransaction.addToBackStack("HomeFragment");
        mFragmentTransaction.commit();
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }

    private void getCafeShopData(String urlPost)
    {

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
                                    coffeeModelArrayList_=new ArrayList<>();
                                    JSONObject result=new JSONObject(response);
                                    JSONArray arrayObj=new JSONArray(result.getJSONArray("shopslist").toString());

                                    for(int i=0;i<arrayObj.length();i++)
                                    {
                                        JSONObject obj=new JSONObject(arrayObj.getJSONObject(i).toString());
                                        String id=obj.getString("id");
                                        String Name=obj.getString("name_en");
                                        String email=obj.getString("email");
                                        String telephone=obj.getString("telephone");
                                        String status=obj.getString("status");
                                        String working_hour_from=obj.getString("working_hour_from");
                                        String working_hour_to=obj.getString("working_hour_to");
                                        String rating=obj.getString("rating");
                                        String website=obj.getString("url");
                                        String img=obj.getString("img");
                                        img=urlClass.imageBaseURL+img;
                                        String longt=obj.getString("long");
                                        String lat=obj.getString("lat");
                                        String address=obj.getString("address_en");

                                        coffeeModel_=new coffeeModel(id,Name,email,telephone,status,working_hour_from,working_hour_to,rating,website,img,longt,lat,address);
                                        coffeeModelArrayList_.add(coffeeModel_);



                                    }

                                    cafeAdapter_=new CafeListAdapter(getActivity(),coffeeModelArrayList_);
                                    coffee_shop_recycl.setAdapter(cafeAdapter_);

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