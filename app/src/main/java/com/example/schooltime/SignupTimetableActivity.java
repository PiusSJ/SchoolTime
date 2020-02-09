package com.example.schooltime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.schooltime.listener.SuccessTimetableRegistrationListener;
import com.example.schooltime.network.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

public class SignupTimetableActivity extends AppCompatActivity implements SuccessTimetableRegistrationListener {
    List<String> timetableInputs = new ArrayList<>();
    private Button ttRegisterConfirmBtn;
    GridLayout rootView = findViewById(R.id.timetableGridLayout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_timetable);
        ttRegisterConfirmBtn = findViewById(R.id.ttRegisterConfirmBtn);
        Intent intent = getIntent();
        final String userId = intent.getExtras().getString("userId");
        RetrofitManager.getInstance().setOnSuccessTimetableRegistrationListener(this);
            ttRegisterConfirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitManager.getInstance().timetableRegister(userId, timetableInputs);
                }
            });
        for(int i = 0;i < rootView.getChildCount();i++){
            View child = rootView.getChildAt(i);
            if(child instanceof EditText){
                String text = ((EditText) child).getText().toString().trim();
                timetableInputs.add(text);
            }
        }

    }

    @Override
    public void onSuccessTimetableRegister() {
        Intent i = new Intent(SignupTimetableActivity.this,HomeScreenActivity.class);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessTimetableRegistrationListener();
        super.onDestroy();
    }
}
