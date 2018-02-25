package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;
import mawaqaajo.com.coffeedistrict.model.OffersModel;

/**
 * Created by HP on 8/8/2017.
 */


public class OffersAdapter extends PagerAdapter {

    //private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<OffersModel> images;

    public OffersAdapter(Context context, ArrayList<OffersModel> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }

   /* public OffersAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images = images;
        inflater = LayoutInflater.from(context);
    }*/

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = inflater.inflate(R.layout.offers_image_tiem_fragment_layout, view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.offers_item_image);

        Picasso.with(context).load(urlClass.baseURL+images.get(position).getOfferImage()).into(myImage);

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
