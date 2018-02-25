package mawaqaajo.com.coffeedistrict.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.DataClasses.CoffeeBlogData;
import mawaqaajo.com.coffeedistrict.Fragments.CoffeeBlogFragment;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.sharedPrefsUtils;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/7/2017.
 */


public class CoffeeBlogAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    String TAG = "CoffeeBlogAdapter";
    CoffeeBlogData coffeeBlogData;
    ArrayList<CoffeeBlogData> coffeeBlogDataArrayList;
    Context context;
    sharedPrefsUtils sharedPref;

    public CoffeeBlogAdapter(ArrayList<CoffeeBlogData> coffeeBlogDataArrayList, Context context) {
        this.coffeeBlogDataArrayList = coffeeBlogDataArrayList;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return coffeeBlogDataArrayList.size();

    }

    @Override
    public CoffeeBlogData getItem(int position) {
        CoffeeBlogData coffeeBlogData = coffeeBlogDataArrayList.get(position);

        return coffeeBlogData;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.coffee_blog_list_item, parent, false);
            vh = new ViewHolder();

            vh.coffeeBlog_ListItem_imageView = (ImageView) convertView.findViewById(R.id.coffeeBlog_ListItem_imageView);

            vh.coffeeBlog_listItem_textView = (TextView) convertView.findViewById(R.id.coffeeBlog_listItem_textView);
            vh. linearLayOutBlogItem= (LinearLayout) convertView.findViewById(R.id.linearLayOutBlogItem);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.coffeeBlog_listItem_textView.setText(Html.fromHtml(coffeeBlogDataArrayList.get(position).getCoffeeBlogDescription()) );
        Picasso.with(context).load(urlClass.baseURL + coffeeBlogDataArrayList.get(position).getCoffeeBlogImage()).into(vh            .coffeeBlog_ListItem_imageView);

        vh.linearLayOutBlogItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                sessionClass.blogID=coffeeBlogDataArrayList.get(position).getCoffeeBlogID();
                CoffeeBlogFragment.goToBlogDetails();

            }
        });


        return convertView;
    }

    private class ViewHolder {

        ImageView coffeeBlog_ListItem_imageView;
        TextView coffeeBlog_listItem_textView;
        LinearLayout linearLayOutBlogItem;

    }
}
