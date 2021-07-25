package com.example.cpa;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.Query;
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
    CollectionReference query = db.collection("crops");

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

        Auction_list=(RecyclerView)AuctionView.findViewById(R.id.auction_list);
        Auction_list.setLayoutManager(new LinearLayoutManager(getContext()));


        FirestoreRecyclerOptions<Auctions> response = new FirestoreRecyclerOptions.Builder<FriendsResponse>()
                .setQuery(query, Auctions.class)
                .build();


    return AuctionView;
    }

}