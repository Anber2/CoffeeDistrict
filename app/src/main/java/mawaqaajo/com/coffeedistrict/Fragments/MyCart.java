package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.Adapters.MyCartAdapter;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.CartItemModel;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.search_fake;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

public class MyCart extends Fragment {
    View v;
    private static MainActivity myContext;
    RecyclerView ItemMyCart;
    private MyCartAdapter myCartadapter;
    String[] img_item = {"http://dxwx7wlnpbc9u.cloudfront.net/s3/beverage/iced-caramel-corretto/detailImage/IcedCaramelCorretto-Details.jpg",
            "http://dxwx7wlnpbc9u.cloudfront.net/s3/beverage/iced-caramel-corretto/detailImage/IcedCaramelCorretto-Details.jpg",
            "http://dxwx7wlnpbc9u.cloudfront.net/s3/beverage/iced-caramel-corretto/detailImage/IcedCaramelCorretto-Details.jpg"};
    String[] price = {"2.50 KD", "3.50 KD", "5.00 KD"};
    String[] name_item = {"Choco Frappe", "Caramel Jelly", "Choco Banana"};
    String[] size_item = {"16 OZ", "14 OZ", "16 OZ"};
    String[] extra_details = {"less suger , more ice", "", ""};
    int haveItemS = 11;
    public static int count[] = {3, 2, 6};
    List<CartItemModel> allItems = new ArrayList<>();
    TextView haveItem, purchase_txt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        toolbar_title.setText(getString(R.string.my_cart));
        searchView.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);
        v = inflater.inflate(R.layout.my_cart_fragment, container, false);
        haveItem = (TextView) v.findViewById(R.id.haveItem);
        haveItem.setText("" + haveItemS);
        ItemMyCart = (RecyclerView) v.findViewById(R.id.rec_my_cart);
        fillDate();

        purchase_txt = (TextView) v.findViewById(R.id.purchase_txt);

        purchase_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new PaymentFragment(), "PaymentFragment");
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        return v;
    }

    private void fillDate() {
        allItems.clear();
        for (int x = 0; x < name_item.length; x++) {
            CartItemModel current = new CartItemModel();
            current.nameItem = name_item[x];
            current.extraItem = extra_details[x];
            current.NumItem = count[x];
            current.priceItem = price[x];
            current.imageItem = img_item[x];
            current.sizeItem = size_item[x];
            allItems.add(current);
        }
        myCartadapter = new MyCartAdapter(getActivity(), allItems);
        ItemMyCart.setAdapter(myCartadapter);
        ItemMyCart.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}