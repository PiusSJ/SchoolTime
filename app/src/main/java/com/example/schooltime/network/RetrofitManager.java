package com.example.schooltime.network;

import android.util.Log;
import android.widget.Toast;

import com.example.schooltime.GlobalApplication;
import com.example.schooltime.listener.SuccessLoginListener;
import com.example.schooltime.listener.SuccessRegistrationListener;
import com.example.schooltime.model.LoginDTO;
import com.example.schooltime.model.RegisterTO;
import com.example.schooltime.model.UserInfoTO;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static String TAG = "Retrofit";
    final private String requestURL = "https://10.0.2.2:8080";
    private static RetrofitManager retrofitManager;
    private Retrofit retrofit;
    private SchoolTimeService service;
    private SuccessLoginListener mSuccessLoginListener;
    private SuccessRegistrationListener mSuccessRegistrationListener;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder().baseUrl(requestURL).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        service = retrofit.create(SchoolTimeService.class);
    }

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) retrofitManager = new RetrofitManager();
        return retrofitManager;
    }

    public void setOnSuccessLoginListener(SuccessLoginListener mSuccessLoginListener){
        this.mSuccessLoginListener = mSuccessLoginListener;
    }

    public void removeSuccessLoginListener() {
        this.mSuccessLoginListener = null;
    }

    public void setOnSuccessRegistrationListener(SuccessRegistrationListener mSuccessRegisterationListener){
        this.mSuccessRegistrationListener = mSuccessRegistrationListener;
    }

    public void removeSuccessRegistrationListener() {
        this.mSuccessRegistrationListener = null;
    }

    private void logForErrorResponse(int errorCode, String errorMessage, String methodName) {
        Log.e(TAG, methodName + " Error Code: " + errorCode);
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    private void logForFailureConnection(String errorMessage, String methodName) {
        Toast.makeText(GlobalApplication.getGlobalContext(),"네트워크 연결이 불안정합니다. 나중에 다시 시도해주세요.", Toast.LENGTH_LONG).show();
        Log.e(TAG, methodName + ": " + errorMessage);
    }

    public void login(LoginDTO loginDTO) {
        final String methodName = "login";
        Call<UserInfoTO> req = service.login(loginDTO);
        req.enqueue(new Callback<UserInfoTO>() {
            @Override
            public void onResponse(Call<UserInfoTO> call, Response<UserInfoTO> response) {
                if(response.isSuccessful()){
                    Toast.makeText(GlobalApplication.getGlobalContext(),"로그인에 성공하였습니다.",Toast.LENGTH_LONG).show();
                    Log.i(TAG, methodName + ": loginSuccess, user_id = " + response.body().getName());
                    if(mSuccessLoginListener != null){
                        mSuccessLoginListener.onSuccessLogin(response.body().getUserId(), response.body().getName());
                    }
                } else {
                    Toast.makeText(GlobalApplication.getGlobalContext(), "로그인에 실패하였습니다. 아이디 혹은 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                    logForErrorResponse(response.code(),response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<UserInfoTO> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }

    public void register(RegisterTO registerTO) {
        final String methodName = "register";
        Call<RegisterTO> req = service.register(registerTO);
        req.enqueue(new Callback<RegisterTO>(){
            @Override
            public void onResponse(Call<RegisterTO> call, Response<RegisterTO> response){
                if(response.isSuccessful()) {
                    if(mSuccessRegistrationListener != null) {
                        mSuccessRegistrationListener.onSuccessRegister();
                    }
                } else {
                    logForErrorResponse(response.code(), response.errorBody().toString(), methodName);
                }
            }

            @Override
            public void onFailure(Call<RegisterTO> call, Throwable t) {
                logForFailureConnection(t.getMessage(), methodName);
            }
        });
    }
}
