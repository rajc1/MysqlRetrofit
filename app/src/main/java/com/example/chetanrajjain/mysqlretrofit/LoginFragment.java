package com.example.chetanrajjain.mysqlretrofit;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextView textView;
    private EditText username,password;
    private Button login_button;

    OnLoginFormActivityListener onLoginFormActivityListener;
    public interface  OnLoginFormActivityListener{

       public void performregisterations();
       public void performlogin(String name);

    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_login, container, false);

        textView = (TextView)v.findViewById(R.id.reg_textview);
        username = (EditText) v.findViewById(R.id.user_login_username);
        password = (EditText)v.findViewById(R.id.user_login_password);
        login_button = (Button)v.findViewById(R.id.login_bn);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginFormActivityListener.performregisterations();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onLoginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){

         String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performLogin(usernameString,passwordString);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("failed")){

                    MainActivity.prefconfig.displayToast("login failed please check credentials");

                } else if(response.body().getResponse().equals("ok")){
                    MainActivity.prefconfig.writeLoginStatus(true);
                    MainActivity.prefconfig.displayToast("nice to see you"+response.body().getName());
                        onLoginFormActivityListener.performlogin(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        username.setText("");
        password.setText("");

    }

}
