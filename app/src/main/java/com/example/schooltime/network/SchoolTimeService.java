package com.example.schooltime.network;

import com.example.schooltime.model.LoginDTO;
import com.example.schooltime.model.RegisterTO;
import com.example.schooltime.model.UserInfoTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SchoolTimeService {
    @Headers({"Content-Type:application/json"})

    @POST("/login")
    Call<UserInfoTO> login(@Body LoginDTO loginDTO);

    @POST("/users")
    Call<RegisterTO> register(@Body RegisterTO registerTO);
}
