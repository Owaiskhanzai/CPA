package com.example.cpa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends Activity {

    EditText emaillogin;
    EditText passwordlogin;
    Button loginbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        emaillogin.findViewById(R.id.emaillogin);
        passwordlogin.findViewById(R.id.passwordlogin);
        loginbtn=findViewById(R.id.loginbtn);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();

            }
        });



    }
    public void loginUser(){

mAuth.signInWithEmailAndPassword(emaillogin.getText().toString().trim(),passwordlogin.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

        if(task.isSuccessful()){

            Toast.makeText(Signin.this,"Login Successfully",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));

        }
        else{

            Toast.makeText(Signin.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
        }


    }
});

    }

}

//emaillogin.getText().toString().trim(),passwordlogin.getText().toString().trim()