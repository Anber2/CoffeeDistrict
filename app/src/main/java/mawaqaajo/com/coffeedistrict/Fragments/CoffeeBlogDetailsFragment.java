package mawaqaajo.com.coffeedistrict.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import mawaqaajo.com.coffeedistrict.OtherCLasses.sessionClass;
import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

/**
 * Created by HP on 8/7/2017.
 */

public class CoffeeBlogDetailsFragment extends Fragment {

    ImageView imageView_coffee_blog_details;
    TextView coffee_blog_details_title_textView, coffee_blog_description_title_textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.coffee_blog_details_fragment_layout, container, false);


        initView(contentView);

        getBolgListDetails();

        return contentView;

    }

    private void getBolgListDetails() {

        sessionClass.startSpinwheel(getActivity(), false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getBolgListDetailsReq(urlClass.getBlogDetailsURL + "/" + sessionClass.blogID + "?ajaxRequest=1");
                } catch (Exception xx) {
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                }
            }
        }).start();
    }


    private void getBolgListDetailsReq(String urlPost) {

        final RequestQueue queue;
        StringRequest stringRequest = null;

        try {

            queue = Volley.newRequestQueue(getActivity());

            stringRequest = new StringRequest(Request.Method.GET, urlPost,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                if (response != null) {

                                    JSONObject result = new JSONObject(response);

                                    JSONObject jsonObject = result.getJSONObject("artical");

                                    String img = jsonObject.getString("img");
                                    String title = jsonObject.getString("title");
                                    String description = jsonObject.getString("description");


                                    Picasso.with(getActivity()).load(urlClass.baseURL + img).into(imageView_coffee_blog_details);
                                    coffee_blog_details_title_textView.setText(title);
                                    coffee_blog_description_title_textView.setText(Html.fromHtml(description));

                                    sessionClass.stopSpinWheel();


                                }
                                sessionClass.stopSpinWheel();


                            } catch (Exception xx) {
                                xx.toString();
                                sessionClass.stopSpinWheel();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    String xx = error.toString();
                    Toast.makeText(getActivity(), "Failed : " + xx, Toast.LENGTH_SHORT).show();
                    sessionClass.stopSpinWheel();


                }


            })


            {


                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {

                        String jsonString = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers));

                        return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));


                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    }
                }


            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    sessionClass.appTimeOut,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            queue.add(stringRequest);


        } catch (Exception e) {
            sessionClass.stopSpinWheel();
            e.toString();
        }


    }


    void initView(View contentView) {

        imageView_coffee_blog_details = (ImageView) contentView.findViewById(R.id.imageView_coffee_blog_details);
        coffee_blog_details_title_textView = (TextView) contentView.findViewById(R.id.coffee_blog_details_title_textView);
        coffee_blog_description_title_textView = (TextView) contentView.findViewById(R.id.coffee_blog_description_title_textView);
    }


}
