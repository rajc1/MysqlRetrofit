package com.example.chetanrajjain.mysqlretrofit;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    private Button logout;
    private TextView textView;
    OnlogOutactivityListener onlogOutactivityListener;

    public interface  OnlogOutactivityListener{

        public void onLogout();

    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_welcome, container, false);
        logout = v.findViewById(R.id.logout_welcome);
        textView = v.findViewById(R.id.welcome_name);
        textView.setText(MainActivity.prefconfig.readName());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.prefconfig.writeLoginStatus(false);
               MainActivity.prefconfig.writeName("");
                onlogOutactivityListener.onLogout();
                Log.i("name existence",MainActivity.prefconfig.readName());
            }
        });
        return  v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        onlogOutactivityListener = (OnlogOutactivityListener) activity;


    }
}
