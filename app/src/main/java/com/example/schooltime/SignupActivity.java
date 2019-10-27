package com.example.schooltime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.schooltime.listener.SuccessRegistrationListener;
import com.example.schooltime.model.UserTO;
import com.example.schooltime.network.RetrofitManager;

public class SignupActivity extends AppCompatActivity implements SuccessRegistrationListener {
    private Button signupBtn;
    private ImageView helpBtn;
    private EditText nameInput, idInput, passwordInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupBtn = findViewById(R.id.signupConfirmBtn);
        helpBtn = findViewById(R.id.helpBtn);
        nameInput = findViewById(R.id.NameInput);
        idInput = findViewById(R.id.idInput);
        passwordInput = findViewById(R.id.PWInput);
        RetrofitManager.getInstance().setOnSuccessRegistrationListener(this);
            signupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAcceptable(nameInput.getText().toString(), idInput.getText().toString(), passwordInput.getText().toString())) {
                        RetrofitManager.getInstance().register(new UserTO(idInput.getText().toString(), passwordInput.getText().toString(),nameInput.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호는 8자리 이상이어야 합니다. 이름과 이메일을 확인해주세요.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "비밀번호는 8자리 이상이어야 합니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean isAcceptable(String name, String id, String pw){
        if(name.length() != 0 && id.length() != 0 && pw.length() > 7){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onSuccessRegister(String userId, String userPassword, String userName) {
        Intent i = new Intent(SignupActivity.this, SignupInfoActivity.class);
        i.putExtra("userName", userName);
        i.putExtra("userId", userId);
        i.putExtra("userPassword", userPassword);
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        RetrofitManager.getInstance().removeSuccessRegistrationListener();
        super.onDestroy();
    }
}
