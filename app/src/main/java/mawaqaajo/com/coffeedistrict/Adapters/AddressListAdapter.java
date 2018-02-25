package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.AddressListModule;

/**
 * Created by HP on 12/31/2017.
 */

public class AddressListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<AddressListModule> addressListModuleArrayList;
    AddressListModule addressListModule;

    public AddressListAdapter(Context context, ArrayList<AddressListModule> addressListModuleArrayList) {
        this.context = context;
        this.addressListModuleArrayList = addressListModuleArrayList;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return addressListModuleArrayList.size();
    }

    @Override
    public AddressListModule getItem(int position) {

        AddressListModule addressListModule = addressListModuleArrayList.get(position);
        return addressListModule;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.address_list_item, parent, false);
            vh = new ViewHolder();

            vh.textView_addressList_Name = (TextView) convertView.findViewById(R.id.textView_addressList_Name);
            vh.textView_addressList_block = (TextView) convertView.findViewById(R.id.textView_addressList_block);
            vh.textView_addressList_street = (TextView) convertView.findViewById(R.id.textView_addressList_street);


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textView_addressList_Name.setText(addressListModuleArrayList.get(position).getAddressName());
        vh.textView_addressList_block.setText(addressListModuleArrayList.get(position).getAddressBlock());
        vh.textView_addressList_street.setText(addressListModuleArrayList.get(position).getAddressStreet());



        return convertView;
    }


    private class ViewHolder {

        TextView textView_addressList_Name, textView_addressList_block, textView_addressList_street;




    }

}
