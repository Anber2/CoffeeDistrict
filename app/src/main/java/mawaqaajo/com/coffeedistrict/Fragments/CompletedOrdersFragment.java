package mawaqaajo.com.coffeedistrict.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Adapters.CompletedOrdersAdapter;
import mawaqaajo.com.coffeedistrict.DataClasses.CompletedOrdersData;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/8/2017.
 */

public class CompletedOrdersFragment extends Fragment{

    CompletedOrdersData  completedOrdersData;
    ArrayList<CompletedOrdersData> completedOrdersDataArrayList;
    CompletedOrdersAdapter completedOrdersAdapter;
    ListView complet_orders_main_Listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.compleated_orders_fragment_layout, container, false);


        initView(v);
        getCompletedOrdersList();

        return v;
    }

    public void getCompletedOrdersList() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getCompletedOrdersData(urlClass.CompletedOrders);
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void getCompletedOrdersData(String urlPost) {

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



    private void initView(View v) {
        complet_orders_main_Listview = (ListView) v.findViewById(R.id.complet_orders_main_Listview);
        completedOrdersDataArrayList = new ArrayList<>();

        completedOrdersAdapter = new CompletedOrdersAdapter(completedOrdersDataArrayList, getActivity());
        complet_orders_main_Listview.setAdapter(completedOrdersAdapter);

    }


}
