package com.example.chetanrajjain.mysqlretrofit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText name, username, password;
    private Button button;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        name = v.findViewById(R.id.user_register_name);
        username = v.findViewById(R.id.user_register_username);
        password = v.findViewById(R.id.user_register_password);
        button = v.findViewById(R.id.user_register_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 performregisteration();
            }
        });


        return v;

    }


    public void performregisteration() {

        String nameString = name.getText().toString();
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();
        Call<User> call = MainActivity.apiInterface.performregisteration(nameString, usernameString, passwordString);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {

                    MainActivity.prefconfig.displayToast("Registeration success...");

                } else if (response.body().getResponse().equals("exist")) {

                    MainActivity.prefconfig.displayToast("User already exists");

                } else if (response.body().getResponse().equals("error inserting into login_info table")) {

                    MainActivity.prefconfig.displayToast("error please try again");

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MainActivity.prefconfig.displayToast("failed to  update");
            }
        });
        name.setText("");
        username.setText("");
        password.setText("");

    }

}
