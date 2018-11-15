package com.example.taehwan.bubblechat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_pw);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null)
                    Log.d("MainActivity", "signed_in : " + user.getUid());
                else Log.d("MainActivity", "signed_out");
            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void onClickLogin(View v){
        final String loginId = et_id.getText().toString();
        final String loginPassword = et_password.getText().toString();
        String phoneNum = "010-2705-4616";

        mAuth.signInWithEmailAndPassword(loginId, loginPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("MainActivity", "signed:success", task.getException());
                            Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_SHORT).show();
                            // 로그인 시 화면 전환
                            Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                            startActivity(intent);
                            Log.d("MainActivity", "login!!");
                        }
                        else{
                            Log.d("MainActivity", "signed:failed", task.getException());
                            Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        Log.d("MainActivity", "clicked: " + loginId + ", " + loginPassword);

    }

    public void onClickSignUp(View v){
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }
}
