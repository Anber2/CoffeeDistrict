package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment;
import mawaqaajo.com.coffeedistrict.OtherCLasses.CustomVolleyRequest;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.CafeitemsModel;

import static mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment.count;
import static mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment.haveItem;
import static mawaqaajo.com.coffeedistrict.Fragments.CafeDetailsFragment.showSnack;

/**
 * Created by Ayadi on 8/9/2017.
 */

public class CafeItemAdapter extends RecyclerView.Adapter<CafeItemAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    ArrayList<CafeitemsModel> cafeitemsModelArrayList_ ;
    Context context;
    Integer[] num_item;

    public CafeItemAdapter(Context context, ArrayList<CafeitemsModel> dataList) {
        inflater = LayoutInflater.from(context);
        this.cafeitemsModelArrayList_ = dataList;
        this.context = context;
    }

    @Override
    public CafeItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cafe_row, parent, false);
        MyViewHolder viewholder = new MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(CafeItemAdapter.MyViewHolder holder, final int position) {
        showSnack();
        holder.itemName.setText(cafeitemsModelArrayList_.get(position).getNameItem());
        holder.desc.setText(cafeitemsModelArrayList_.get(position).getShortDescItem());
        holder.price.setText(cafeitemsModelArrayList_.get(position).getPriceItem());
        Picasso.with(context).load(cafeitemsModelArrayList_.get(position).getImageItem()).resize(350,400).into(holder.itemImg);


        if (count[position] == 0)
        {
            holder.addImg.setImageResource(R.drawable.plus_icon);
            holder.numberAdded.setText("");
            holder.minus.setVisibility(View.GONE);
        } else {
            holder.addImg.setImageResource(R.drawable.black_round);
            holder.numberAdded.setText("" + count[position]);
            holder.minus.setVisibility(View.VISIBLE);
        }

        holder.addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[position] = count[position] + 1;
                haveItem++;
                notifyDataSetChanged();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count[position] = count[position] - 1;
                notifyDataSetChanged();
                haveItem--;
            }
        });

        holder.goToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CafeDetailsFragment.goToItemDetails();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cafeitemsModelArrayList_.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, desc, price, numberAdded, minus;
        ImageView itemImg;
        ImageView addImg;
        LinearLayout goToDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.nameItem);
            desc = (TextView) itemView.findViewById(R.id.descItem);
            price = (TextView) itemView.findViewById(R.id.priceItem);
            itemImg = (ImageView) itemView.findViewById(R.id.imageItem);
            goToDetail = (LinearLayout) itemView.findViewById(R.id.goToDetail);
            numberAdded = (TextView) itemView.findViewById(R.id.added);
            minus = (TextView) itemView.findViewById(R.id.minus);
            addImg = (ImageView) itemView.findViewById(R.id.add);
        }
    }
}
