package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.DataClasses.CoffeeShopData;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/6/2017.
 */

public class CoffeeShopAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    String TAG = "CoffeeShopAdapter";
    Context context;
    ArrayList<CoffeeShopData> coffeeShopDataArrayList;
    CoffeeShopData coffeeShopData;

    public CoffeeShopAdapter(Context context, ArrayList<CoffeeShopData> coffeeShopDataArrayList) {
        this.context = context;
        this.coffeeShopDataArrayList = coffeeShopDataArrayList;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return coffeeShopDataArrayList.size();
    }

    @Override
    public CoffeeShopData getItem(int position) {
        CoffeeShopData coffeeShopData = coffeeShopDataArrayList.get(position);

        return coffeeShopData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.coffee_shope_item_list, parent, false);
            vh = new ViewHolder();


            vh.textView_cofee_shop_item = (TextView) convertView.findViewById(R.id.textView_cofee_shop_item);


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textView_cofee_shop_item.setText(coffeeShopDataArrayList.get(position).getCoffeeShopName());


        return convertView;
    }

    private class ViewHolder {
        // TextView tv;
        TextView textView_cofee_shop_item;

    }
}
