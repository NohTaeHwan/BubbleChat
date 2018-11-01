package com.example.taehwan.bubblechat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_pw);

    }

    public void onClickLogin(View v){
        String loginId = et_id.getText().toString();
        String loginPassword = et_password.getText().toString();

    }

    public void onClickSignUp(View v){
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }
}
