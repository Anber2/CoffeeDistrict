package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.ItemSizeModel;

/**
 * Created by Ayadi on 8/10/2017.
 */

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<ItemSizeModel> data = Collections.emptyList();
    Context context;
    private int selectedPosition = -1;

    public ItemDetailsAdapter(Context context, List<ItemSizeModel> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public ItemDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_size_row, parent, false);
        ItemDetailsAdapter.MyViewHolder viewholder = new ItemDetailsAdapter.MyViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final ItemDetailsAdapter.MyViewHolder holder, final int position) {
        final ItemSizeModel info = data.get(position);
        holder.size_name.setText(info.nameSize);
        holder.size_price.setText(info.priceItem);

        if (position == selectedPosition) {
            holder.check_size.setChecked(true);
        }else{
            holder.check_size.setChecked(false);
        }

        holder.check_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView size_name, size_price;
        CheckBox check_size;

        public MyViewHolder(View itemView) {
            super(itemView);
            size_name = (TextView) itemView.findViewById(R.id.size_name);
            size_price = (TextView) itemView.findViewById(R.id.size_price);
            check_size = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }
}