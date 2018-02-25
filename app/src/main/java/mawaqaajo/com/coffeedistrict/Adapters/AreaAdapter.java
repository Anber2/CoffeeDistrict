package mawaqaajo.com.coffeedistrict.Adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/6/2017.
 */

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    private ArrayList<String> countries;
    public static int row_selected = -1;
    public AreaAdapter(ArrayList<String> countries) {
        this.countries = countries;
    }

    @Override
    public AreaAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.area_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AreaAdapter.ViewHolder viewHolder,final int position) {
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_selected=position;
                notifyDataSetChanged();
            }
        });
        if(row_selected==position){
            viewHolder.card.setBackgroundColor(Color.parseColor("#567845"));
            viewHolder.tv_country.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            viewHolder.card.setBackgroundColor(Color.parseColor("#ffffff"));
            viewHolder.tv_country.setTextColor(Color.parseColor("#000000"));
        }

        viewHolder.tv_country.setText(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        CardView card;
        public ViewHolder(View view) {
            super(view);
            card= (CardView) view.findViewById(R.id.card_area);
            tv_country = (TextView)view.findViewById(R.id.tv_country);
        }
    }
}