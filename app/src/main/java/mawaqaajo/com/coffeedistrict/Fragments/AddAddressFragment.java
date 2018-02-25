package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment.v;

/**
 * Created by HP on 12/31/2017.
 */

public class AddAddressFragment extends Fragment {

    static String areaID = "";
    static String titleId = "";
    private static MainActivity mContext;
    EditText addAddress_mobile, addAddress_phone, addAddress_addressTitle, addAddress_block, addAddress_street, addAddress_Avenue, addAddress_building, addAddress_Floor, addAddress_office, addAddress_direction;

    Button button_addAddress_Submit;

    Spinner spinner_addAddress_Area, spinner_addAddress_title;

    String[] location_list, titleList;
    String[] location_list_Id, titleId_;


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
        v = inflater.inflate(R.layout.add_address_fragment, container, false);

        defineView(v);

        getAllArea();

        getTitleList();
        return v;
    }

    private void getTitleList() {

        List<String> titleId = new ArrayList<String>();
        List<String> title = new ArrayList<String>();

        titleId.add("0");
        titleId.add("1");
        titleId.add("2");

        title.add("Apartment");
        title.add("House");
        title.add("Office");


        titleList = new String[title.size()];
        title.toArray(titleList);

        titleId_ = new String[titleId.size()];
        titleId.toArray(titleId_);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, titleList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_addAddress_title.setAdapter(dataAdapter);

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
                                    spinner_addAddress_Area.setAdapter(dataAdapter);

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


    private void defineView(View v) {

        addAddress_mobile = (EditText) v.findViewById(R.id.addAddress_mobile);
        addAddress_phone = (EditText) v.findViewById(R.id.addAddress_phone);
        addAddress_addressTitle = (EditText) v.findViewById(R.id.addAddress_addressTitle);
        addAddress_block = (EditText) v.findViewById(R.id.addAddress_block);
        addAddress_street = (EditText) v.findViewById(R.id.addAddress_street);
        addAddress_Avenue = (EditText) v.findViewById(R.id.addAddress_Avenue);
        addAddress_building = (EditText) v.findViewById(R.id.addAddress_building);
        addAddress_Floor = (EditText) v.findViewById(R.id.addAddress_Floor);
        addAddress_office = (EditText) v.findViewById(R.id.addAddress_office);
        addAddress_direction = (EditText) v.findViewById(R.id.addAddress_direction);

        spinner_addAddress_Area = (Spinner) v.findViewById(R.id.spinner_addAddress_Area);
        spinner_addAddress_title = (Spinner) v.findViewById(R.id.spinner_addAddress_title);

        button_addAddress_Submit = (Button) v.findViewById(R.id.button_addAddress_Submit);

        button_addAddress_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addAddressAuthorization()) {

                    sendAddressData();


                }
            }
        });

        spinner_addAddress_Area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaID = location_list_Id[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_addAddress_title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                titleId = titleId_[position].toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void sendAddressData() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject obj = new JSONObject();

                    obj.putOpt("users_id", sessionClass.user_id);
                    obj.putOpt("area_id", areaID);
                    obj.putOpt("name", addAddress_addressTitle.getText().toString());
                    obj.putOpt("type", titleId);
                    obj.putOpt("block", addAddress_block.getText().toString());
                    obj.putOpt("street", addAddress_street.getText().toString());
                    obj.putOpt("avenue", addAddress_Avenue.getText().toString());
                    obj.putOpt("building", addAddress_building.getText().toString());
                    obj.putOpt("floor", addAddress_Floor.getText().toString());
                    obj.putOpt("apartment_number", "1");
                    obj.putOpt("directions", addAddress_direction.getText().toString());
                    obj.putOpt("long", sessionClass.curentLongtit);
                    obj.putOpt("lat", sessionClass.curentLat);
                    obj.putOpt("judda", "a");
                    obj.putOpt("mobile", addAddress_mobile.getText().toString());
                    obj.putOpt("phone", addAddress_phone.getText().toString());


                    postAddressData(urlClass.addAddressURL, obj);
                } catch (Exception xx) {
                }
            }
        }).start();

    }

    private void postAddressData(String urlPost, final JSONObject jsonObject) {

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
                                        Toast.makeText(getActivity(), result.get("message").toString(), Toast.LENGTH_SHORT).show();

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


    private boolean addAddressAuthorization() {

        if (addAddress_mobile.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Mobile is empty", Toast.LENGTH_LONG).show();
            addAddress_mobile.requestFocus();
            return false;
        }
        if (addAddress_phone.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Phone is empty", Toast.LENGTH_LONG).show();
            addAddress_phone.requestFocus();
            return false;
        }
        if (addAddress_addressTitle.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Address title is empty", Toast.LENGTH_LONG).show();
            addAddress_addressTitle.requestFocus();
            return false;
        }

        if (addAddress_block.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Block title is empty", Toast.LENGTH_LONG).show();
            addAddress_block.requestFocus();
            return false;
        }
        if (addAddress_street.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Street title is empty", Toast.LENGTH_LONG).show();
            addAddress_street.requestFocus();
            return false;
        }
        if (addAddress_Avenue.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Avenue title is empty", Toast.LENGTH_LONG).show();
            addAddress_Avenue.requestFocus();
            return false;
        }
        if (addAddress_building.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Building title is empty", Toast.LENGTH_LONG).show();
            addAddress_building.requestFocus();
            return false;
        }
        if (addAddress_Floor.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Floor title is empty", Toast.LENGTH_LONG).show();
            addAddress_Floor.requestFocus();
            return false;
        }
        if (addAddress_office.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Office title is empty", Toast.LENGTH_LONG).show();
            addAddress_office.requestFocus();
            return false;
        }
        if (addAddress_direction.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Direction title is empty", Toast.LENGTH_LONG).show();
            addAddress_direction.requestFocus();
            return false;
        }

        return true;
    }
}
