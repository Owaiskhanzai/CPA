package com.example.cpa;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mfirstname,memail,mpassword,mphone;
    Button registerbtn;
    TextView loginhere;
    FirebaseAuth fauth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mfirstname=findViewById(R.id.mfirstname);
        memail=findViewById(R.id.memail);
        mpassword=findViewById(R.id.mpassword);
        mphone=findViewById(R.id.mphone);
        registerbtn=findViewById(R.id.registerbtn);
        loginhere=findViewById(R.id.loginhere);

        fauth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        if(fauth.getCurrentUser() !=null){

            startActivity(new Intent(getApplicationContext(),Register.class));
            finish();
        }

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email =  memail.getText().toString().trim();
                String password= mpassword.getText().toString().trim();


                if(TextUtils.isEmpty(email)){

                    memail.setError("email is required");

                    return;
                }
                if(TextUtils.isEmpty(password)){

                    mpassword.setError("password is required");

                    return;
                }

                if(password.length() <6)

                {
                    mpassword.setError("Password must be => 6 character");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(Register.this,"User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));


                        }else{

                            Toast.makeText(Register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }
}