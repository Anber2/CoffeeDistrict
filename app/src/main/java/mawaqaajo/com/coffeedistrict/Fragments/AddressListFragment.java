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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Adapters.AddressListAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.AddressListModule;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by HP on 12/31/2017.
 */

public class AddressListFragment extends Fragment {

    ListView listView_address;

    private ArrayList<AddressListModule> addressListModuleArrayList;
    AddressListAdapter addressListAdapter;
    AddressListModule addressListModule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.address_list_fragment_layout, container, false);
        toolbar_title.setText(getString(R.string.contact_us));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);

        initiate(v);

        getAddressList();

        return v;
    }

    private void getAddressList() {
        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    getAddressListReq(urlClass.getlistOfAddress+sessionClass.user_id);
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }

    private void getAddressListReq(String urlPost) {

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
                                    addressListModuleArrayList = new ArrayList<AddressListModule>();


                                    JSONArray arrayObj2 = result.getJSONArray("Address");


                                    for (int j = 0; j < arrayObj2.length(); j++) {

                                        JSONObject obj = arrayObj2.getJSONObject(j);

                                        String id = obj.getString("id");
                                        String name = obj.getString("name");

                                        String area_id = obj.getString("area_id");
                                        String block = obj.getString("block");
                                        String street = obj.getString("street");


                                        addressListModule = new AddressListModule(id,area_id ,name, block, street);
                                        addressListModuleArrayList.add(addressListModule);
                                    }

                                    addressListAdapter = new AddressListAdapter(getActivity(), addressListModuleArrayList);
                                    listView_address.setAdapter(addressListAdapter);


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

        listView_address = (ListView) v.findViewById(R.id.listView_address);



    }

}
