package com.example.kartik_chauhan.zoohackathon.Seller;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

public class sellerDashBoardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton add;
    private AddItemAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;
    private List<RecyclerItems> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dash_board);


        recyclerView = (RecyclerView)this.findViewById(R.id.item_recycler);
        add = (FloatingActionButton)this.findViewById(R.id.add_item);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sellerDashBoardActivity.this,addItemActivity.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //insert your code here
        listItems = new ArrayList<>();
        databaseReference = database.getReference("Seller").child(user.getUid()).child("Items");


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot dp : dataSnapshot.getChildren()){
                        String item = dp.child("ItemName").getValue(String.class);
                        String price = dp.child("Price").getValue(String.class);


                        listItems.add(new RecyclerItems(item,price));

                }


                adapter = new AddItemAdapter(listItems,this);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addValueEventListener(eventListener);



    }
}
