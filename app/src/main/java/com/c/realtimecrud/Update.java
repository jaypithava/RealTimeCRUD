package com.c.realtimecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.c.realtimecrud.Pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Update extends AppCompatActivity {
    EditText name,id,email;
    Button update,search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name=findViewById(R.id.okboy);
        id=findViewById(R.id.pname);
        email=findViewById(R.id.ename);
        update=findViewById(R.id.btnUpdateall);
        search=findViewById(R.id.btnSearch);

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String n = name.getText().toString().trim();
               String i = id.getText().toString().trim();
               String e = email.getText().toString().trim();
               if(!TextUtils.isEmpty(n)){
                   updateUser(i,n,e);

               }
           }
       });

       search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

            ArrayList<User> userArrayList=new ArrayList<>();
            Map<String,String> doc=new HashMap<>();
            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Users");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    doc.putAll((Map<? extends String, ? extends String>) dataSnapshot.getValue());
                    Iterator myVeryOwnIterator=doc.keySet().iterator();
                    while (myVeryOwnIterator.hasNext()){
                        String key=(String)myVeryOwnIterator.next();
                        if(key.equalsIgnoreCase("name")){
                            User user=new User();
                            user.setName(doc.get(key));
                            userArrayList.add(user);

                        }else if(key.equalsIgnoreCase("id")){
                            User user=new User();
                            user.setId(doc.get(key));
                            userArrayList.add(user);
                        }else if(key.equalsIgnoreCase("email")){
                            User user=new User();
                            user.setEmail(doc.get(key));
                            userArrayList.add(user);
                        }
                    }

                    name.setText(userArrayList.get(0).getName());
                    id.setText(userArrayList.get(1).getId());
                    email.setText(userArrayList.get(2).getEmail());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
           }
       });



    }


    private boolean updateUser(String i, String n, String e) {


        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Users");

        User user = new User(i, n, e);
        dR.setValue(user);
        Toast.makeText(getApplicationContext(), "Updated......", Toast.LENGTH_LONG).show();
        return true;
    }
}