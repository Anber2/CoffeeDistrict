package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Activities.CompletedOrdersDetailsActivity;
import mawaqaajo.com.coffeedistrict.DataClasses.CompletedOrdersData;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/10/2017.
 */

public class CompletedOrdersAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    String TAG = "CompletedOrdersAdapter";
    CompletedOrdersData completedOrdersData;
    ArrayList<CompletedOrdersData> completedOrdersDataArrayList;
    Context context;

    public CompletedOrdersAdapter(ArrayList<CompletedOrdersData> completedOrdersDataArrayList, Context context) {
        this.completedOrdersDataArrayList = completedOrdersDataArrayList;
        this.context = context;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //return completedOrdersDataArrayList.size();
        return 5;
    }

    @Override
    public CompletedOrdersData getItem(int position) {
        CompletedOrdersData completedOrdersData = completedOrdersDataArrayList.get(position);
        return completedOrdersData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.completed_orders_list_item, parent, false);
            vh = new ViewHolder();

            vh.textView_completedOrder_Price = (TextView) convertView.findViewById(R.id.textView_completedOrder_Price);
            vh.textView_completedOrder_address = (TextView) convertView.findViewById(R.id.textView_completedOrder_address);
            vh.textView__completedOrder_orderID_ViewInfo = (TextView) convertView.findViewById(R.id.textView__completedOrder_orderID_ViewInfo);
            vh.textView_completedOrder_orderStatus = (TextView) convertView.findViewById(R.id.textView_completedOrder_orderStatus);
            vh.textView_completedOrder_orderDate = (TextView) convertView.findViewById(R.id.textView_completedOrder_orderDate);
            vh.textView_completedOrder_orderID = (TextView) convertView.findViewById(R.id.textView_completedOrder_orderID);
            vh.imageView_completedOrder_ReOrder = (ImageView) convertView.findViewById(R.id.imageView_completedOrder_ReOrder);

            vh.textView__completedOrder_orderID_ViewInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, CompletedOrdersDetailsActivity.class));

                }
            });
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

        TextView textView_completedOrder_Price, textView_completedOrder_address, textView__completedOrder_orderID_ViewInfo, textView_completedOrder_orderStatus, textView_completedOrder_orderDate, textView_completedOrder_orderID;

        ImageView imageView_completedOrder_ReOrder;

    }
}
