package mawaqaajo.com.coffeedistrict.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import mawaqaajo.com.coffeedistrict.OtherCLasses.urlClass;
import mawaqaajo.com.coffeedistrict.R;

import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filter_drawer;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.filterimg;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.imgmenu;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.searchView;
import static mawaqaajo.com.coffeedistrict.Activities.MainActivity.toolbar_title;

/**
 * Created by Ayadi on 8/8/2017.
 */

public class About extends Fragment {
    public WebView mWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.about_fragment_layout, container, false);
        toolbar_title.setText(getString(R.string.about_us));
        filterimg.setVisibility(View.GONE);
        searchView.setVisibility(View.GONE);
        filter_drawer.setVisibility(View.GONE);
        imgmenu.setVisibility(View.VISIBLE);
        mWebView = (WebView) v.findViewById(R.id.webview);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(PaymentUrl.this, description, Toast.LENGTH_SHORT).show();
                Log.e("onReceivedError", "  " + failingUrl);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("onPageStarted", "  " + url);

            }


            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("onPageFinished", "  " + url);

            }

        });
        mWebView.loadUrl(urlClass.aboutUSURL);


        return v;
    }
}


