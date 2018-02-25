package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.Collections;
import java.util.List;

import mawaqaajo.com.coffeedistrict.OtherCLasses.CustomVolleyRequest;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.CartItemModel;

/**
 * Created by Ayadi on 8/17/2017.
 */

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    List<CartItemModel> data = Collections.emptyList();
    private ImageLoader imageLoader;
    Context context;
    Integer[] num_item;

    public MyCartAdapter(Context context, List<CartItemModel> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_mycart_row, parent, false);
        MyCartAdapter.MyViewHolder viewholder = new MyCartAdapter.MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyCartAdapter.MyViewHolder holder, final int position) {
        CartItemModel info = data.get(position);
        holder.addImg.setImageResource(R.drawable.black_round);
        holder.numberAdded.setText("" + info.NumItem);
        holder.itemName.setText(info.nameItem);
        holder.price.setText(info.priceItem);
        if (info.extraItem.isEmpty()) {
            holder.extra.setVisibility(View.GONE);
        } else {
            holder.extra.setVisibility(View.VISIBLE);
            holder.extra.setText(info.extraItem);
        }

        imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
        holder.itemImg.setDefaultImageResId(R.mipmap.ic_launcher);
        holder.itemImg.setImageUrl(info.imageItem, imageLoader);

        holder.size.setText(info.sizeItem);
    }

    @Override
    public int getItemCount() {
        return data.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, price, numberAdded, minus, size, extra;
        NetworkImageView itemImg;
        ImageView addImg, delete_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            size = (TextView) itemView.findViewById(R.id.size);
            extra = (TextView) itemView.findViewById(R.id.extra);
            itemName = (TextView) itemView.findViewById(R.id.nameItem);
            price = (TextView) itemView.findViewById(R.id.priceItem);
            itemImg = (NetworkImageView) itemView.findViewById(R.id.imageItem);
            numberAdded = (TextView) itemView.findViewById(R.id.added);
            minus = (TextView) itemView.findViewById(R.id.minus);
            addImg = (ImageView) itemView.findViewById(R.id.add);
        }
    }
}
