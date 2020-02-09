package com.example.schooltime.network;

import com.example.schooltime.model.LoginDTO;
import com.example.schooltime.model.UserTO;
import com.example.schooltime.model.UserInfoTO;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SchoolTimeService {
    @Headers({"Content-Type:application/json"})

    @POST("/login")
    Call<UserInfoTO> login(@Body LoginDTO loginDTO);

    @POST("/users")
    Call<UserTO> register(@Body UserTO userTO);

    @POST("/users/timetable")
    Call<Map<String, List<String>>> ttRegister(@Body String userId, List<String> timetable);
}
