package com.example.cpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class userActivity extends AppCompatActivity {
    private View AuctionView;

    private RecyclerView Auction_list;

    private FirebaseFirestore db;

    private FirestoreRecyclerAdapter adapter;
    Button signoutbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);




        signoutbtn2=findViewById(R.id.signoutbtn2);
        db=FirebaseFirestore.getInstance();
        Auction_list=findViewById(R.id.Auction_list2);
        //query
        CollectionReference query=db.collection("crops");
        //recycler options

        signoutbtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getApplicationContext(), Signin.class);
                finish();
                startActivity(intent);


            }
        });
        FirestoreRecyclerOptions<cropsmodel> options=new FirestoreRecyclerOptions.Builder<cropsmodel>().setQuery(query,cropsmodel.class).build();

        adapter= new FirestoreRecyclerAdapter<cropsmodel, userActivity.cropsviewholder>(options) {
            @NonNull
            @Override
            public userActivity.cropsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single2,parent,false);

                return new cropsviewholder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull userActivity.cropsviewholder holder, int position, @NonNull cropsmodel model) {

                holder.nameofcrop.setText(model.getNameofcrop());
                holder.croptypes.setText(model.getCroptypes());
                holder.quantity.setText(model.getQuantity());
                holder.priceperunit.setText(model.getPriceperunit());
                holder.expirydate.setText(model.getExpirydate());
                holder.quality.setText(model.getQuality());
                holder.phonenumber.setText(model.getPhonenumber());
                holder.address.setText(model.getAddress());



            }


        };


        Auction_list.setHasFixedSize(true);
        Auction_list.setLayoutManager(new LinearLayoutManager(this));
        Auction_list.setAdapter(adapter);



    }

    private class cropsviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nameofcrop;
        private TextView quantity;
        private TextView priceperunit;
        private TextView expirydate;
        private TextView phonenumber;
        private TextView address;
        private TextView quality;
        private TextView croptypes;

        public cropsviewholder(@NonNull View itemView) {
            super(itemView);

            nameofcrop=itemView.findViewById(R.id.nameofcrop);
            croptypes=itemView.findViewById(R.id.croptypes);
            quantity=itemView.findViewById(R.id.quantity);
            priceperunit=itemView.findViewById(R.id.priceperunit);
            expirydate=itemView.findViewById(R.id.expirydate);
            quality=itemView.findViewById(R.id.quality);
            phonenumber=itemView.findViewById(R.id.phonenumber);
            address=itemView.findViewById(R.id.address);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {



        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }




    //create
}//class