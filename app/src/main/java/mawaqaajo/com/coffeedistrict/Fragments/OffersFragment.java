package mawaqaajo.com.coffeedistrict.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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
import java.util.Timer;
import java.util.TimerTask;

import mawaqaajo.com.coffeedistrict.Adapters.OffersAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.OffersModel;
import me.relex.circleindicator.CircleIndicator;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;


/**
 * Created by HP on 8/8/2017.
 */

public class OffersFragment extends Fragment {

    private static ViewPager mPager;
    private static int currentPage = 0;
    OffersModel offersModel;
    private ArrayList<OffersModel> offersModelArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.offers_fragment_layout, container, false);

        init(v);

        getOffers();
        return v;
    }

    public void getOffers() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getOffersReq(urlClass.offers);
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void getOffersReq(String urlPost) {

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

                                    offersModelArrayList = new ArrayList<OffersModel>();

                                    JSONObject result = new JSONObject(response);
                                    JSONObject arrayObj = result.getJSONObject("oResults");

                                    JSONArray arrayObj2 = arrayObj.getJSONArray("data");


                                    for (int j = 0; j < arrayObj2.length(); j++) {

                                        JSONObject obj = arrayObj2.getJSONObject(j);

                                        String id = obj.getString("id");
                                        String img = obj.getString("img");


                                        offersModel = new OffersModel(id, img);
                                        offersModelArrayList.add(offersModel);
                                    }

                                    mPager.setAdapter(new OffersAdapter(getActivity(), offersModelArrayList));


                                    final Handler handler = new Handler();
                                    final Runnable Update = new Runnable() {
                                        public void run() {
                                            if (currentPage == offersModelArrayList.size()) {
                                                currentPage = 0;
                                            }
                                            mPager.setCurrentItem(currentPage++, true);
                                        }
                                    };
                                    Timer swipeTimer = new Timer();
                                    swipeTimer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            handler.post(Update);
                                        }
                                    }, 2500, 2500);



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


    private void init(View v) {

        toolbar_title.setText(getString(R.string.offers));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);


        mPager = (ViewPager) v.findViewById(R.id.offerPager);

        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager


    }
}
