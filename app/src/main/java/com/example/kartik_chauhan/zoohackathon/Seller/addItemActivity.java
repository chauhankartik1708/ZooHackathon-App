package com.example.kartik_chauhan.zoohackathon.Seller;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kartik_chauhan.zoohackathon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addItemActivity extends AppCompatActivity {

    TextView close;
    Button submit;
    TextInputEditText t;
    EditText p,d;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference,databaseName;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
//        try{
//            databaseName = database.getReference("Seller").child(user.getUid()).child("Items");
//
//            ValueEventListener eventListener = new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    String name = dataSnapshot.child("Item").getValue(String.class);
//                    Log.d("TAG","Name is: " + name);
//                    username = name;
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//            databaseName.addListenerForSingleValueEvent(eventListener);
//            databaseReference.child("Name").setValue(username);
//        }catch(Exception e){
//
//        }

        t = (TextInputEditText)findViewById(R.id.add_item_name);
        p = (EditText)findViewById(R.id.add_item_price);
        d = (EditText)findViewById(R.id.add_item_desc);

        close = (TextView)findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        submit = (Button)findViewById(R.id.add_item_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = t.getText().toString().trim();
                String price = p.getText().toString().trim();
                String desc = d.getText().toString().trim();

                if (TextUtils.isEmpty(item)) {
                    Toast.makeText(getApplicationContext(), "Enter Item name !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(price)) {
                    Toast.makeText(getApplicationContext(), "Enter Price !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(desc)) {
                    Toast.makeText(getApplicationContext(), "Enter Description !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user = mAuth.getCurrentUser();
                databaseReference = database.getReference("Seller").child(user.getUid()).child("Items").child(item);
                databaseReference.child("ItemName").setValue(item);
                databaseReference.child("Price").setValue(price);
                databaseReference.child("Description").setValue(desc);

//                String usernamefirebase = user.getDisplayName();
//                if(usernamefirebase.isEmpty()){
//                    databaseReference.child("Name").setValue(username);
//                }else{
//                    databaseReference.child("Name").setValue(usernamefirebase);
//                }

                Toast.makeText(getApplicationContext(),"Item Added !!",Toast.LENGTH_SHORT).show();

                startActivity(new Intent(addItemActivity.this,sellerDashBoardActivity.class));
                finish();

            }
        });


    }
}
