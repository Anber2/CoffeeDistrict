package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Fragments.ScheduleFragment;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.ScheduleModle;

/**
 * Created by HP on 12/31/2017.
 */

public class ScheduleAdapter extends BaseAdapter {

    ScheduleModle scheduleModle;
    String[] timelist;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<ScheduleModle> scheduleModleArrayList;


    public ScheduleAdapter(Context context, ArrayList<ScheduleModle> scheduleModleArrayList) {
        this.context = context;
        this.scheduleModleArrayList = scheduleModleArrayList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return scheduleModleArrayList.size();
    }

    @Override
    public ScheduleModle getItem(int position) {

        ScheduleModle scheduleModle = scheduleModleArrayList.get(position);
        return scheduleModle;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.schedule_blog_list_item, parent, false);
            vh = new ViewHolder();

            vh.textView_ScheduleDate = (TextView) convertView.findViewById(R.id.textView_ScheduleDate);
            vh.spinner_Time = (Spinner) convertView.findViewById(R.id.spinner_Time);


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textView_ScheduleDate.setText(scheduleModleArrayList.get(position).getScheduleDate());

        try {
            List<String> time = new ArrayList<String>();

            for (int i = 0; i < ScheduleFragment.timesArr.length(); i++) {

                time.add("" + ScheduleFragment.timesArr.get(i));

            }

            timelist = new String[time.size()];

            time.toArray(timelist);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, timelist);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            vh.spinner_Time.setAdapter(dataAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    private class ViewHolder {

        TextView textView_ScheduleDate;
        Spinner spinner_Time;


    }
}
