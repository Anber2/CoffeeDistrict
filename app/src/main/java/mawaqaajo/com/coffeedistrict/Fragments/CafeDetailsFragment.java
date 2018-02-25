package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
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
import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.Adapters.CafeItemAdapter;
import mawaqaajo.com.coffeedistrict.Adapters.CafeListAdapter;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.CafeitemsModel;
import mawaqaajo.com.coffeedistrict.model.coffeeModel;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

public class CafeDetailsFragment extends Fragment
{
    static View v;
    private static MainActivity myContext;
    TabLayout tabLayout;
    RecyclerView ItemDetailList;
    static TextView haveItemTxt;
    TextView view_order_txt,nameTXT,addressTXT,timeTXT,websiteTXT;
    RatingBar rating;

    CafeitemsModel cafeitemsModel_;
    ArrayList<CafeitemsModel> cafeitemsModelArrayList_;
    private CafeItemAdapter cafeItemAdapter_;

    static LinearLayout linear;
    public static  String name="",address="",time="",website="";
    public static  int ratingInt=0;

    public static int count[] = {0, 0, 0, 0};
    public static int haveItem = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.cafe_details_fragment, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        filterimg.setVisibility(View.GONE);
        toolbar_title.setText("Cafe Name");
        ItemDetailList = (RecyclerView) v.findViewById(R.id.itemDetail_list);
        linear = (LinearLayout) v.findViewById(R.id.linearSnack);
        haveItemTxt = (TextView) v.findViewById(R.id.haveItem);
        view_order_txt = (TextView) v.findViewById(R.id.view_order_txt);

        nameTXT= (TextView) v.findViewById(R.id.cafeNameTXT);
        addressTXT= (TextView) v.findViewById(R.id.adress);
        timeTXT= (TextView) v.findViewById(R.id.time);
        websiteTXT= (TextView) v.findViewById(R.id.website);
        rating= (RatingBar) v.findViewById(R.id.rating);

        view_order_txt.setText(getString(R.string.view_order));
        nameTXT.setText(name);
        addressTXT.setText(address);
        timeTXT.setText(time);
        websiteTXT.setText(website);
        rating.setProgress(ratingInt);
        //rating.setRating(ratingInt);

        sessionClass.startSpinwheel(getActivity(),false,true);
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    String shop_id=sessionClass.cafeID;
                    String categoryIndx="1";

                    String url= urlClass.getCofeCategoryWithItemsURL+"&shop_id="+shop_id+"&menu_section="+categoryIndx+" ";
                    getCafeItemAndCategoryData(url);
                }
                catch (Exception xx)
                {}
            }
        }).start();


        tabview();


        view_order_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveItem == 0) {
                    Toast.makeText(myContext, getString(R.string.plz_select_item), Toast.LENGTH_LONG).show();
                } else {
                    FragmentManager fragManager = myContext.getSupportFragmentManager();
                    FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                    mFragmentTransaction.replace(R.id.main_container, new MyCart(), "MyCart");
                    mFragmentTransaction.addToBackStack("CafeDetailsFragment");
                    mFragmentTransaction.commit();
                }
            }
        });
        return v;
    }




    private void tabview()
    {
        RelativeLayout relativeLayout = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabOne = (TextView) relativeLayout.findViewById(R.id.tab);
        tabOne.setText(R.string.all);
        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout));

        RelativeLayout relativeLayout1 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabTow = (TextView) relativeLayout1.findViewById(R.id.tab);
        tabTow.setText(R.string.coffee);
        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout1));

        RelativeLayout relativeLayout2 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabThree = (TextView) relativeLayout2.findViewById(R.id.tab);
        tabThree.setText(R.string.freeze);
        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout2));

        RelativeLayout relativeLayout3 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabFour = (TextView) relativeLayout3.findViewById(R.id.tab);
        tabFour.setText(R.string.juices);
        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout3));

        RelativeLayout relativeLayout4 = (RelativeLayout) myContext.getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tabFive = (TextView) relativeLayout4.findViewById(R.id.tab);
        tabFive.setText(R.string.smoothies);
        tabLayout.addTab(tabLayout.newTab().setCustomView(relativeLayout4));

        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sessionClass.startSpinwheel(getActivity(),false,true);
                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try
                        {
                            String shop_id=sessionClass.cafeID;
                            String categoryIndx=String.valueOf(tabLayout.getSelectedTabPosition()+1);

                            String url= urlClass.getCofeCategoryWithItemsURL+"&shop_id="+shop_id+"+&menu_section="+categoryIndx+" ";
                            getCafeItemAndCategoryData(url);
                        }
                        catch (Exception xx)
                        {}
                    }
                }).start();

            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }

    public static void showSnack() {

        if (haveItem > 0)
        {
            linear.setVisibility(View.VISIBLE);
            haveItemTxt.setText("" + haveItem);
        }
        else {
            linear.setVisibility(View.GONE);
        }
    }


    public static void goToItemDetails() {
        FragmentManager fragManager = myContext.getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
        mFragmentTransaction.replace(R.id.main_container, new ItemDetailsFragment(), "ItemDetailsFragment");
        mFragmentTransaction.addToBackStack("CafeDetailsFragment");
        mFragmentTransaction.commit();
    }


//    cafeitemsModelArrayList_=new ArrayList<>();
//        cafeitemsModelArrayList_.clear();
//
//        for (int x = 0; x < name_item.length; x++)
//    {
//        CafeitemsModel current = new CafeitemsModel();
//        current.nameItem = name_item[x];
//        current.DescItem = desc_item[x];
//        current.priceItem = price[x];
//        current.imageItem = img_coffee[x];
//        cafeitemsModelArrayList_.add(current);
//    }
//    cafeItemAdapter_ = new CafeItemAdapter(getActivity(), cafeitemsModelArrayList_);
//        ItemDetailList.setAdapter(cafeItemAdapter_);
//        ItemDetailList.setLayoutManager(new LinearLayoutManager(getActivity()));


    private void getCafeItemAndCategoryData(String urlPost)
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
                                    cafeitemsModelArrayList_ =new ArrayList<>();
                                    JSONArray arrayObj=new JSONArray(response);

                                    for(int i=0;i<arrayObj.length();i++)
                                    {
                                        JSONObject obj=new JSONObject(arrayObj.getJSONObject(i).toString());

                                        String id=obj.getString("id");
                                        String menu_section_id=obj.getString("menu_section_id");
                                        String category_id=obj.getString("category_id");
                                        String quantity=obj.getString("quantity");
                                        String img=obj.getString("img");
                                        img=urlClass.imageBaseURL+img;
                                        String name_en=obj.getString("name_en");
                                        String description_en=obj.getString("description_en");
                                        String price=obj.getString("price");
                                        String description_short=obj.getString("description_en");
                                        String status=obj.getString("status");
                                        String rating=obj.getString("rating");


                                        cafeitemsModel_=new CafeitemsModel(id,menu_section_id,category_id,quantity,img,name_en,description_en,price,description_short,status,rating);
                                        cafeitemsModelArrayList_.add(cafeitemsModel_);



                                    }

                                    cafeItemAdapter_=new CafeItemAdapter(getActivity(),cafeitemsModelArrayList_);
                                    ItemDetailList.setAdapter(cafeItemAdapter_);

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