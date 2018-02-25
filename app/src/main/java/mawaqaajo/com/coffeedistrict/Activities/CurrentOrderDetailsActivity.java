package mawaqaajo.com.coffeedistrict.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/10/2017.
 */

public class CurrentOrderDetailsActivity extends Activity {

    ImageView imageView_currentOrderDetails_OrderReceived, imageView_currentOrderDetails_OrderPrepared, imageView_currentOrderDetails_OrderPicked, imageView_currentOrderDetails_OnTheWay, imageView_currentOrderDetails_Delivered;

    Button button_currentOrderDetails_track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order_details_activity);

        imageView_currentOrderDetails_OrderReceived = (ImageView) findViewById(R.id.imageView_currentOrderDetails_OrderReceived);
        imageView_currentOrderDetails_OrderPrepared = (ImageView) findViewById(R.id.imageView_currentOrderDetails_OrderPrepared);
        imageView_currentOrderDetails_OrderPicked = (ImageView) findViewById(R.id.imageView_currentOrderDetails_OrderPicked);
        imageView_currentOrderDetails_OnTheWay = (ImageView) findViewById(R.id.imageView_currentOrderDetails_OnTheWay);
        imageView_currentOrderDetails_Delivered = (ImageView) findViewById(R.id.imageView_currentOrderDetails_Delivered);

        button_currentOrderDetails_track = (Button) findViewById(R.id.button_currentOrderDetails_track);

    }
}
