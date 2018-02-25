package mawaqaajo.com.coffeedistrict.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
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
import java.util.HashMap;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Adapters.FAQListAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 12/28/2017.
 */

public class FaqFragment extends Fragment {
    //layout
    ExpandableListView expListView;
    //FAQ list
    FAQListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, String> listDataChild;
    List<String> answerList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.faq_fragment_layout, container, false);
        toolbar_title.setText(getString(R.string.contact_us));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);

        getFAQData();

        initiate(v);

        //getPolicy();
        return v;
    }

    private void getFAQData() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getFAQDataReq(urlClass.faqURL);
                } catch (Exception xx) {
                }
            }
        }).start();
    }

    private void getFAQDataReq(String urlPost) {

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
                                    listDataChild = new HashMap<>();
                                    listDataHeader = new ArrayList<String>();
                                    answerList = new ArrayList<String>();
                                    List<String> answerList2 = new ArrayList<String>();

                                    JSONObject result = new JSONObject(response);
                                    JSONObject arrayObj = result.getJSONObject("oResults");

                                    JSONArray arrayObj2 = arrayObj.getJSONArray("data");


                                    for (int j = 0; j < arrayObj2.length(); j++) {

                                        JSONObject obj = arrayObj2.getJSONObject(j);

                                        String Question = obj.getString("title");
                                        String answer = obj.getString("body");

                                        listDataHeader.add(Question);

                                        answerList.add(answer);

                                    }

                                    for (int i = 0; i < listDataHeader.size(); i++) {
                                        answerList2.add(answerList.get(i));

                                        listDataChild.put(listDataHeader.get(i), answerList.get(i)  );

                                    }

                                    if (getActivity() != null) {
                                        listAdapter = new FAQListAdapter(getActivity(), listDataHeader, listDataChild);

                                        expListView.setAdapter(listAdapter);
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


    private void initiate(View v) {
        expListView = (ExpandableListView) v.findViewById(R.id.lvExp);



    }
}
