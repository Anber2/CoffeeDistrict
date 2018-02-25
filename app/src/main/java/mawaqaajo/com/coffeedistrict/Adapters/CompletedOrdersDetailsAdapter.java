package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.DataClasses.CompletedOrdersDetailsData;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/10/2017.
 */

public class CompletedOrdersDetailsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    CompletedOrdersDetailsData completedOrdersDetailsData;
    ArrayList<CompletedOrdersDetailsData> completedOrdersDetailsDataArrayList;
    Context context;
    String TAG = "CompletedOrdersAdapter";

    public CompletedOrdersDetailsAdapter(ArrayList<CompletedOrdersDetailsData> completedOrdersDetailsDataArrayList, Context context) {
        this.completedOrdersDetailsDataArrayList = completedOrdersDetailsDataArrayList;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        //return completedOrdersDetailsDataArrayList.size();

        return 5;
    }

    @Override
    public CompletedOrdersDetailsData getItem(int position) {
        CompletedOrdersDetailsData completedOrdersDetailsData = completedOrdersDetailsDataArrayList.get(position);
        return completedOrdersDetailsData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.complete_orders_details_list_item, parent, false);
            vh = new ViewHolder();

            vh.textView_CompletedOrdersDetails_name = (TextView) convertView.findViewById(R.id.textView_CompletedOrdersDetails_name);
            vh.textView_CompletedOrdersDetails_price = (TextView) convertView.findViewById(R.id.textView_CompletedOrdersDetails_price);
            vh.textView_CompletedOrdersDetails_description = (TextView) convertView.findViewById(R.id.textView_CompletedOrdersDetails_description);


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private class ViewHolder {

        TextView textView_CompletedOrdersDetails_name, textView_CompletedOrdersDetails_price, textView_CompletedOrdersDetails_description;

    }
}
