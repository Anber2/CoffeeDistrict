package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment;
import mawaqaajo.com.coffeedistrict.Fragments.HomeFragment;
import mawaqaajo.com.coffeedistrict.OtherCLasses.CustomVolleyRequest;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.coffeeModel;

/**
 * Created by Ayadi on 8/7/2017.
 */

public class CafeListAdapter extends RecyclerView.Adapter<CafeListAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    ArrayList<coffeeModel> datalist_;
    private ImageLoader imageLoader;
    Context context;
    Integer[] num_item;

    public CafeListAdapter(Context context, ArrayList<coffeeModel> datalist) {
        inflater = LayoutInflater.from(context);
        this.datalist_ = datalist;
        this.context = context;
    }

    @Override
    public CafeListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cafe_list_row, parent, false);
        MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final CafeListAdapter.MyViewHolder holder, final int position)
    {

        holder.cafeName.setText(datalist_.get(position).getName());
        holder.cafeStatus.setText(datalist_.get(position).getStatus());
        Picasso.with(context).load(datalist_.get(position).getImg()).resize(350,400).into(holder.itemImg);


        holder.card_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sessionClass.cafeID=datalist_.get(position).getId();
                CafeDetailsFragment.name=datalist_.get(position).getName();
                CafeDetailsFragment.address=datalist_.get(position).getAddress();
                CafeDetailsFragment.time=datalist_.get(position).getWorking_hour_from() +" - "+datalist_.get(position).getWorking_hour_to();
                CafeDetailsFragment.website=datalist_.get(position).getWebsite();
                try
                {
                    CafeDetailsFragment.ratingInt = Integer.parseInt(datalist_.get(position).getRating());
                }
                catch (Exception xx){}

                HomeFragment.goToDetails();


            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist_.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cafeName, cafeStatus;
        ImageView itemImg;
        CardView card_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            cafeName = (TextView) itemView.findViewById(R.id.name);
            cafeStatus = (TextView) itemView.findViewById(R.id.status);
            itemImg = (ImageView) itemView.findViewById(R.id.imagecafe);
            card_view = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
