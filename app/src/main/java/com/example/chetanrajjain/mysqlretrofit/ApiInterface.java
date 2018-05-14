package com.example.chetanrajjain.mysqlretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("register.php")
    Call<User> performregisteration(@Query("name") String name, @Query("user_name") String userName, @Query("password") String password);

    @GET("login.php")
    Call<User> performLogin(@Query("user_name") String userName, @Query("password") String password);
}
