package com.example.cpa;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Auctions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Auctions extends Fragment {


    private View AuctionView;

    private RecyclerView Auction_list;

    private FirebaseFirestore db;

    private FirestoreRecyclerAdapter adapter;
   // CollectionReference query = db.collection("crops");

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Auctions() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment two.
     */
    // TODO: Rename and change types and number of parameters
    public static Auctions newInstance(String param1, String param2) {
        Auctions fragment = new Auctions();
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
        // Inflate the layout for this fragment
         AuctionView= inflater.inflate(R.layout.fragment_two, container, false);


         db=FirebaseFirestore.getInstance();
        Auction_list=(RecyclerView)AuctionView.findViewById(R.id.auction_list);
        //query
        CollectionReference query=db.collection("crops");
        //recycler options


        FirestoreRecyclerOptions<cropsmodel> options=new FirestoreRecyclerOptions.Builder<cropsmodel>().setQuery(query,cropsmodel.class).build();

         adapter= new FirestoreRecyclerAdapter<cropsmodel, cropsviewholder>(options) {
            @NonNull
            @Override
            public cropsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);

                return new cropsviewholder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull cropsviewholder holder, int position, @NonNull cropsmodel model) {

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
        Auction_list.setLayoutManager(new LinearLayoutManager(getContext()));
        Auction_list.setAdapter(adapter);



//        Auction_list=(RecyclerView)AuctionView.findViewById(R.id.auction_list);
//        Auction_list.setLayoutManager(new LinearLayoutManager(getContext()));




    return AuctionView;
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

}