package mawaqaajo.com.coffeedistrict.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import mawaqaajo.com.coffeedistrict.Activities.MainActivity;
import mawaqaajo.com.coffeedistrict.R;


public class CreditCardFragment extends Fragment {
    View v;
    Button proceed;
    Dialog dialog;
    private static MainActivity myContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.credit_card_fragment, container, false);
        proceed = (Button) v.findViewById(R.id.proceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(myContext, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_success_payment);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button close = (Button) dialog.findViewById(R.id.proceed);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        FragmentManager fragManager = myContext.getSupportFragmentManager();
                        FragmentTransaction mFragmentTransaction = fragManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.main_container, new MyOrder(), "MyOrder");
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();
                    }
                });
                dialog.show();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (MainActivity) activity;
        super.onAttach(activity);
    }
}