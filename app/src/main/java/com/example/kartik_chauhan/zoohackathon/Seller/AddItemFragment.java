package com.example.kartik_chauhan.zoohackathon.Seller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.kartik_chauhan.zoohackathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddItemFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton add;
    private AddItemAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private List<RecyclerItems> listItems;
//    private ProgressBar spinner;
    private LinearLayout pb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        pb = (LinearLayout)view.findViewById(R.id.linlaHeaderProgress);
        pb.setVisibility(View.VISIBLE);
//        spinner=(ProgressBar)view.findViewById(R.id.progressBar);
//        spinner.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView)view.findViewById(R.id.item_recycler);
        add = (FloatingActionButton)view.findViewById(R.id.add_item);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddItemFragment.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //insert your code here
        listItems = new ArrayList<>();
        databaseReference = database.getReference("Seller").child(user.getUid()).child("Items");


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    for(DataSnapshot dp : ds.getChildren()){
                        String item = dp.child("Name").getValue(String.class);
                        String price = dp.child("Title").getValue(String.class);
                        String desc = dp.child("Description").getValue(String.class);

                        listItems.add(new RecyclerItems(item,price,desc));
                    }
                }


                adapter = new AddItemAdapter(listItems,getContext());
                recyclerView.setAdapter(adapter);
//                spinner.setVisibility(View.GONE);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);
        return view;
    }

}
