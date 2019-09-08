package com.example.schooltime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schooltime.listener.SuccessLoginListener;
import com.example.schooltime.model.LoginDTO;
import com.example.schooltime.network.RetrofitManager;

public class LoginActivity extends AppCompatActivity implements SuccessLoginListener {
    EditText idInput, pwInput;
    Button confirmBtn, signupBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        idInput = findViewById(R.id.idInput);
        pwInput = findViewById(R.id.PWInput);
        confirmBtn = findViewById(R.id.loginConfirmBtn);
        signupBtn = findViewById(R.id.SignupButton);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        RetrofitManager.getInstance().setOnSuccessLoginListener(this);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager.getInstance().login(new LoginDTO(idInput.getText().toString(), pwInput.getText().toString()));
            }
        });
    }

    @Override
    public void onSuccessLogin(Long userId, String userName) {
        Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessLoginListener();
        super.onDestroy();
    }
}
