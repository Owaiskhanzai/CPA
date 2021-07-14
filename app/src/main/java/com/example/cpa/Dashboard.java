package com.example.cpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    FloatingActionButton mmanagebtn;
    Button logoutbtn;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        fauth=FirebaseAuth.getInstance();
        mmanagebtn=findViewById(R.id.managebtn);

        logoutbtn=findViewById(R.id.logoutbtn);


        mmanagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              //  startActivity(new Intent(getApplicationContext(),Menage.class));


            }
        });



        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fauth.signOut();
                startActivity(new Intent(getApplicationContext(), Signin.class));
                finish();

            }
        });


    }


}