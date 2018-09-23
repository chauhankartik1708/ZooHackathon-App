package com.example.kartik_chauhan.zoohackathon.Seller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartik_chauhan.zoohackathon.Authentication.LoginActivity;
import com.example.kartik_chauhan.zoohackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerRegisActivity extends AppCompatActivity {

    Button signup,cancel;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText name,emailtxt,passtxt,contact,address;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_regis);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(SellerRegisActivity.this,AddItemActivity.class));
                }
            }
        };

        name = (EditText)findViewById(R.id.fullName);
        emailtxt = (EditText)findViewById(R.id.email);
        passtxt = (EditText)findViewById(R.id.signup_pass);
        contact = (EditText)findViewById(R.id.contact);
        address = (EditText)findViewById(R.id.address);
        signup = (Button)findViewById(R.id.signup_signup);
        cancel = (Button)findViewById(R.id.signup_cancel);

        final Intent i = new Intent(this,LoginActivity.class);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailtxt.getText().toString().trim();
                String password = passtxt.getText().toString().trim();
                String nametxt = name.getText().toString().trim();
                String contacttxt = contact.getText().toString().trim();
                final String add = address.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(nametxt)){
                    Toast.makeText(SellerRegisActivity.this,"Name can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(contacttxt)){
                    Toast.makeText(SellerRegisActivity.this,"Contact No. can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(add)){
                    Toast.makeText(SellerRegisActivity.this,"Address can't be empty",Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SellerRegisActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SellerRegisActivity.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SellerRegisActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SellerRegisActivity.this,"Registration Successful !",Toast.LENGTH_SHORT).show();


                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String nametxt = name.getText().toString().trim();
                                    String email = emailtxt.getText().toString().trim();
                                    String contacttxt = contact.getText().toString().trim();
                                    databaseReference = database.getReference("Seller").child(user.getUid());
                                    databaseReference.child("email").setValue(email);
                                    databaseReference.child("name").setValue(nametxt);
                                    databaseReference.child("contact").setValue(contacttxt);
                                    databaseReference.child("address").setValue(add);

                                    startActivity(new Intent(SellerRegisActivity.this, SellerDashboard.class));
                                    finish();
                                }
                            }
                        });

            }
        });


    }
}
