package com.example.cpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText mfirstname,memail,mpassword,mphone;
    Button registerbtn;
    TextView loginhere;
    FirebaseAuth fauth;
    ProgressBar progressBar;
    Spinner mspinner;
    FirebaseFirestore fstore;
    String userID;
    mem mem;
    String item;
    DatabaseReference databaseReference;

    FirebaseDatabase database=FirebaseDatabase.getInstance();



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
        fstore=FirebaseFirestore.getInstance();
        progressBar=findViewById(R.id.progressBar);
        String[]usertype={"Chose User Type","Seller","Buyer"};



        FirebaseDatabase database=FirebaseDatabase.getInstance();

        databaseReference=database.getReference("Value");
        mspinner=findViewById(R.id.mspinner);
        mspinner.setOnItemSelectedListener(this);

        mem=new mem();

        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,usertype);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mspinner.setAdapter(arrayAdapter);



 // Create an ArrayAdapter using the string array and a default spinner layout
//        Spinner spinner = (Spinner) findViewById(R.id.mspinner);
// Create an ArrayAdapter using the string array and a default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.usertype, android.R.layout.simple_spinner_item);
//// Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//// Apply the adapter to the spinner
//        spinner.setAdapter(adapter);


        if(fauth.getCurrentUser() !=null){

            startActivity(new Intent(getApplicationContext(),Register.class));
            finish();
        }

        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signin.class));

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname=mfirstname.getText().toString().trim();
                String email =  memail.getText().toString().trim();
                String password= mpassword.getText().toString().trim();
                String phone=mphone.getText().toString().trim();


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
                            userID=fauth.getCurrentUser().getUid();
                            DocumentReference documentReference=fstore.collection("user").document(userID);


                            Map<String, Object> user = new HashMap<>();
                            user.put("fname", firstname);
                            user.put("email", email);
                            user.put("password", password);
                            user.put("phone", phone);
                            user.put("usertype",item);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG,"On Success:user profile is created for"+ userID);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);

                                };


                                });
                           startActivity(new Intent(getApplicationContext(), Signin.class));


                        }
                        else{

                            Toast.makeText(Register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

item=mspinner.getSelectedItem().toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}