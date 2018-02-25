package mawaqaajo.com.coffeedistrict.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;

import mawaqaajo.com.coffeedistrict.Adapters.CompletedOrdersDetailsAdapter;
import mawaqaajo.com.coffeedistrict.DataClasses.CompletedOrdersDetailsData;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/10/2017.
 */

public class CompletedOrdersDetailsActivity extends Activity {

    CompletedOrdersDetailsAdapter completedOrdersDetailsAdapter;
    ArrayList<CompletedOrdersDetailsData> completedOrdersDetailsDataArrayList;
    CompletedOrdersDetailsData completedOrdersDetailsData;
    ListView complet_orders_details_Listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_orders_details_activity_layout);

        complet_orders_details_Listview = (ListView) findViewById(R.id.complet_orders_details_Listview);

        completedOrdersDetailsDataArrayList = new ArrayList<CompletedOrdersDetailsData>();

        completedOrdersDetailsAdapter = new CompletedOrdersDetailsAdapter(completedOrdersDetailsDataArrayList,CompletedOrdersDetailsActivity.this);

        complet_orders_details_Listview.setAdapter(completedOrdersDetailsAdapter);






    }
}
