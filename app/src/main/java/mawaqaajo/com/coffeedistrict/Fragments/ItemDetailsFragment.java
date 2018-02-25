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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.Adapters.ItemDetailsAdapter;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.ItemSizeModel;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;
import static mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment.haveItem;

public class ItemDetailsFragment extends Fragment {
    View v;
    private static MainActivity myContext;
    RecyclerView item_size_list;
    String[] price = {"2.50 KD", "3.50 KD"};
    String[] name_size = {"14 OZ", "16 OZ"};
    List<ItemSizeModel> allItems = new ArrayList<>();
    private ItemDetailsAdapter sizeAdapter;
    int count = 0;
    ImageView add;
    TextView addTxt, haveItemTxt, minusTxt, view_order_txt;
    LinearLayout minus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.item_details_fragment, container, false);
        toolbar_title.setText("Item Name");
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);
        view_order_txt = (TextView) v.findViewById(R.id.view_order_txt);
        view_order_txt.setText(getString(R.string.add_to_cart));
        item_size_list = (RecyclerView) v.findViewById(R.id.item_size_list);
        add = (ImageView) v.findViewById(R.id.add);
        addTxt = (TextView) v.findViewById(R.id.added);
        minus = (LinearLayout) v.findViewById(R.id.minus);
        haveItemTxt = (TextView) v.findViewById(R.id.haveItem);
        minusTxt = (TextView) v.findViewById(R.id.minusTxt);
        minusTxt.setVisibility(View.GONE);
        fillDate();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minusTxt.setVisibility(View.VISIBLE);
                count++;
                haveItem++;
                add.setImageResource(R.drawable.black_round);
                addTxt.setText("" + count);
                haveItemTxt.setText("" + count);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count--;
                    haveItem--;
                    if (count == 0) {
                        minusTxt.setVisibility(View.GONE);
                        add.setImageResource(R.drawable.plus_icon);
                        addTxt.setText("");
                        haveItemTxt.setText("" + count);
                    } else {
                        addTxt.setText("" + count);
                        haveItemTxt.setText("" + count);
                    }
                }
            }
        });

        view_order_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                mFragmentTransaction.replace(R.id.main_container, new CafeDetailsFragment(), "CafeDetailsFragment");
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }
        });

        return v;
    }

    private void fillDate() {
        allItems.clear();
        for (int x = 0; x < name_size.length; x++) {
            ItemSizeModel current = new ItemSizeModel();
            current.nameSize = name_size[x];
            current.priceItem = price[x];
            allItems.add(current);
        }
        sizeAdapter = new ItemDetailsAdapter(getActivity(), allItems);
        item_size_list.setAdapter(sizeAdapter);
        item_size_list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }
}