package com.example.chetanrajjain.mysqlretrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class PrefConfig {

    private Context context;
    private SharedPreferences sharedPreferences;

    public PrefConfig(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);

    }

    public boolean readLoginStatus() {

        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }
    public void writeLoginStatus(boolean b){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status),b);
    }

    public void writeName(String name) {


        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name), name);
        editor.commit();

    }

    public String readName() {

        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "user");

    }

    public void displayToast(String message) {

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
