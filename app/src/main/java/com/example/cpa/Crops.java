package com.example.cpa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Crops#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Crops extends Fragment {

    private Context myContext = null;

    public Crops(Context ctx){
        myContext = ctx;
    }
    EditText _nameofcrop, _quantity, _priceperunit, _expirydate, _phonenumber, _address, _quality;
//    Spinner _typesofcropspinner;
    Button submitbtn;
    View parentHolder;
    Activity referenceActivity;


    private FirebaseFirestore db;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Crops() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment one.
     */
    // TODO: Rename and change types and number of parameters
    public static Crops newInstance(String param1, String param2) {
        Crops fragment = new Crops();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        referenceActivity = getActivity();
        parentHolder = inflater.inflate(R.layout.fragment_one, container,
                false);

        _nameofcrop = (EditText) parentHolder.findViewById(R.id._nameofcrop);
        _quantity =  (EditText)parentHolder.findViewById(R.id._quantity);
        _priceperunit = (EditText) parentHolder.findViewById(R.id._priceperunit);
        _expirydate =(EditText)  parentHolder.findViewById(R.id._expirydate);
        _phonenumber =  (EditText)parentHolder.findViewById(R.id._phonenumber);
        _address =  (EditText)parentHolder.findViewById(R.id._address);
        _quality = (EditText) parentHolder.findViewById(R.id._quality);

        String [] values =
                {"Agronomy","Horticulture","Seeds","Pulses","Fruits","Vegetables","Spices"};
        Spinner _typesofcropspinner = (Spinner) parentHolder.findViewById(R.id._typesofcropspinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        _typesofcropspinner.setAdapter(adapter);







//........
//        List<String> croptypes= Arrays.asList("Agronomy","Horticulture","Seeds","Pulses","Fruits","Vegetables","Spices");
//
//        final Spinner _typesofcropspinner=(Spinner) parentHolder.findViewById(R.id._typesofcropspinner);
//
////
////       ArrayAdapter arrayAdapter=new ArrayAdapter(referenceActivity.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,croptypes);
////        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
////
////        _typesofcropspinner.setAdapter(arrayAdapter);
//......

        submitbtn =  (Button) parentHolder.findViewById(R.id.submitbtn);


        db = FirebaseFirestore.getInstance();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cropid = UUID.randomUUID().toString();
                String nameofcrop = _nameofcrop.getText().toString().trim();
                String quantity = _quantity.getText().toString().trim();
                String priceperunit = _priceperunit.getText().toString().trim();
                String expirydate = _expirydate.getText().toString().trim();
                String phonenumber = _phonenumber.getText().toString().trim();
                String address = _address.getText().toString().trim();
                String quality = _quality.getText().toString().trim();
                String croptypes=_typesofcropspinner.getSelectedItem().toString();

                saveToFirestore(cropid,nameofcrop,croptypes,quantity,priceperunit,expirydate,quality,phonenumber,address);

                _nameofcrop.setText("");
                _quantity .setText("");
                _priceperunit .setText("");
                _expirydate .setText("");
                _phonenumber .setText("");
                _address .setText("");
                _quality .setText("");

            }
        });



        return parentHolder;
    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//
//
//       return inflater.inflate(R.layout.fragment_one, container, false);
//    }}

    private void saveToFirestore(String cropid,String nameofcrop, String croptypes, String quantity, String priceperunit, String expirydate, String phonenumber, String address, String quality) {


        if (!nameofcrop.isEmpty() && !quantity.isEmpty() && !priceperunit.isEmpty() && !expirydate.isEmpty() && !phonenumber.isEmpty() && !address.isEmpty() && !quality.isEmpty()) {


            HashMap<String,Object> map=new HashMap<>();
            map.put("nameofcrop",nameofcrop);
            map.put("croptypes",croptypes);
            map.put("quantity",quantity);
            map.put("priceperunit",priceperunit);
            map.put("expirydate",expirydate);
            map.put("quality",quality);
            map.put("phonenumber",phonenumber);
            map.put("address",address);



            db.collection("crops").document(cropid).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Added Successfully!", Toast.LENGTH_SHORT).show();
                  //  referenceActivity.recreate();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();

                }
            });



        } else {
            Toast.makeText(getActivity(), "Failed!", Toast.LENGTH_SHORT).show();
        }


    }
}