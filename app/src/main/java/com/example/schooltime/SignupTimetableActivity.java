package com.example.schooltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class SignupTimetableActivity extends AppCompatActivity {
    List<String> timetableInputs = new ArrayList<>();
    GridLayout rootView = findViewById(R.id.timetableGridLayout);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_timetable);
        for(int i = 0;i < rootView.getChildCount();i++){
            View child = rootView.getChildAt(i);
            if(child instanceof EditText){
                String text = ((EditText) child).getText().toString().trim();
                timetableInputs.add(text);
            }
        }

    }
}
