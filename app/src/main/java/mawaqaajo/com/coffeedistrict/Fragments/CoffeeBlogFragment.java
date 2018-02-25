package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import mawaqaajo.com.coffeedistrict.Adapters.CoffeeBlogAdapter;
import mawaqaajo.com.coffeedistrict.DataClasses.CoffeeBlogData;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/7/2017.
 */

public class CoffeeBlogFragment extends Fragment {
    FragmentTransaction tx;
    private static MainActivity myContext;

    ListView coffeeBlogListView;
    CoffeeBlogAdapter coffeeBlogAdapter;
    ArrayList<CoffeeBlogData> coffeeBlogDataArrayList;
    CoffeeBlogData coffeeBlogData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.coffee_blog_fragment_layout, container, false);

        initView(v);


        getBlogList();


        return v;
    }

    public void getBlogList() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    getBlogListReq(urlClass.getBlog);
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void getBlogListReq(String urlPost) {

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
                                    coffeeBlogDataArrayList = new ArrayList<CoffeeBlogData>();

                                    JSONObject result = new JSONObject(response);
                                    JSONObject arrayObj = result.getJSONObject("oResults");

                                    JSONArray arrayObj2 = arrayObj.getJSONArray("data");


                                    for (int j = 0; j < arrayObj2.length(); j++) {

                                        JSONObject obj = arrayObj2.getJSONObject(j);

                                        String id = obj.getString("id");
                                        String title = obj.getString("title");
                                        String img = obj.getString("img");


                                        coffeeBlogData = new CoffeeBlogData(id, img, title, "");
                                        coffeeBlogDataArrayList.add(coffeeBlogData);
                                    }

                                    coffeeBlogAdapter = new CoffeeBlogAdapter(coffeeBlogDataArrayList, getActivity());
                                    coffeeBlogListView.setAdapter(coffeeBlogAdapter);


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

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }
    public static void goToBlogDetails()
    {
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_container, new CoffeeBlogDetailsFragment(), "CoffeeBlogDetailsFragment");
        mFragmentTransaction.commit();
        mFragmentTransaction.addToBackStack("CoffeeBlogFragment");
    }

    void initView(View v) {

        coffeeBlogListView = (ListView) v.findViewById(R.id.listView_coffeeBlog);




    }
}
