package com.example.chetanrajjain.mysqlretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener,WelcomeFragment.OnlogOutactivityListener {

    public static PrefConfig prefconfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefconfig = new PrefConfig(getApplicationContext());
        apiInterface = ApiClient.retrofit().create(ApiInterface.class);

        if(findViewById(R.id.fragment_container)!=null){

            if(savedInstanceState!=null){

                return;
            }

            if(prefconfig.readLoginStatus()){

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new WelcomeFragment()).commit();
            }
            else {

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();
            }


        }



    }

    @Override
    public void performregisterations() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performlogin(String name) {

        prefconfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new WelcomeFragment()).commit();
    }


    @Override
    public void onLogout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
    }
}
