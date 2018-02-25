package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Activities.CurrentOrderDetailsActivity;
import mawaqaajo.com.coffeedistrict.DataClasses.CurrentOrderData;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.R.id.textView_currentOrder_ViewInfo;

/**
 * Created by HP on 8/9/2017.
 */

public class CurrentOrderAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    String TAG = "CurrentOrderAdapter";
    CurrentOrderData currentOrderData;
    ArrayList<CurrentOrderData> currentOrderDataArrayList;
    Context context;

    public CurrentOrderAdapter(ArrayList<CurrentOrderData> currentOrderDataArrayList, Context context) {
        this.currentOrderDataArrayList = currentOrderDataArrayList;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CurrentOrderData getItem(int position) {
        CurrentOrderData currentOrderData = currentOrderDataArrayList.get(position);
        return currentOrderData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.current_order_list_item_layout, parent, false);
            vh = new ViewHolder();

            vh.textView_currentOrder_orderID = (TextView) convertView.findViewById(R.id.textView_currentOrder_orderID);
            vh.textView_currentOrder_date = (TextView) convertView.findViewById(R.id.textView_currentOrder_date);
            vh.textView_currentOrder_cafeName = (TextView) convertView.findViewById(R.id.textView_currentOrder_cafeName);
            vh.textView_currentOrder_ViewInfo = (TextView) convertView.findViewById(textView_currentOrder_ViewInfo);

            vh.textView_currentOrder_ViewInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, CurrentOrderDetailsActivity.class));
                }
            });

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

        TextView textView_currentOrder_orderID, textView_currentOrder_date, textView_currentOrder_cafeName, textView_currentOrder_ViewInfo;

    }
}
