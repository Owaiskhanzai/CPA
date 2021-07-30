package com.example.cpa;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signin extends Activity {

    EditText emaillogin;
    EditText passwordlogin;
    Button loginbtn;
    TextView registerhere;
    FirebaseAuth mAuth;
    private FirebaseFirestore fstore;
     Object Buyer="Buyer";
     Object Seller="Seller";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fstore=FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        emaillogin=findViewById(R.id.emaillogin);
        passwordlogin=findViewById(R.id.passwordlogin);
        registerhere=findViewById(R.id.registerhere);
        loginbtn=findViewById(R.id.loginbtn);

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                loginUser();

            }
        });



    }
    public void loginUser() {

        mAuth.signInWithEmailAndPassword(emaillogin.getText().toString().trim(), passwordlogin.getText().toString().trim()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                checkuserAccesslevel(mAuth.getCurrentUser().getUid());
                Toast.makeText(Signin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Signin.this, "Error!" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }//loginuser




    private void checkuserAccesslevel(String uid) {

        DocumentReference df=fstore.collection("user").document(uid);

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Log.d("TAG","Success"+documentSnapshot.getData());

                if(documentSnapshot.getString("usertype").equals(Seller) ){

                    startActivity(new Intent(getApplicationContext(), DashActivity.class));
                    finish();
                }
                if(documentSnapshot.getString("usertype").equals(Buyer)){

                    startActivity(new Intent(getApplicationContext(), userActivity.class));
                    finish();
                }

            }
        });

    }


    protected void onStart() {


        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

        DocumentReference df=FirebaseFirestore.getInstance().collection("user").document(FirebaseAuth.getInstance().getCurrentUser().getUid());

        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.getString("usertype").equals(Seller) ){

                    startActivity(new Intent(getApplicationContext(), DashActivity.class));
                    finish();
                }
                if(documentSnapshot.getString("usertype").equals(Buyer)){

                    startActivity(new Intent(getApplicationContext(), userActivity.class));
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Signin.class));
                finish();

            }
        });


        }
    }

    protected void onDestroy() {


        super.onDestroy();

        

    }

}

//emaillogin.getText().toString().trim(),passwordlogin.getText().toString().trim()



//
//
//mAuth.signInWithEmailAndPassword(emaillogin.getText().toString().trim(),passwordlogin.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//    @Override
//    public void onComplete(@NonNull Task<AuthResult> task) {
//
//        if(task.isSuccessful()){
//
//            Toast.makeText(Signin.this,"Login Successfully",Toast.LENGTH_SHORT).show();
//            checkuserAccesslevel(mAuth.getCurrentUser().getUid());
//
//
//
//        }
//        else{
//
//            Toast.makeText(Signin.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//});
