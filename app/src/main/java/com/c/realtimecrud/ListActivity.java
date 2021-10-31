package com.c.realtimecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.c.realtimecrud.Adapter.Data_Adapter;
import com.c.realtimecrud.Pojo.User2;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListActivity extends AppCompatActivity {
    private RecyclerView rv;
    Data_Adapter data_Adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mbase = FirebaseDatabase.getInstance().getReference();


        rv = (RecyclerView) findViewById( R.id.recview );

        rv.setLayoutManager( new LinearLayoutManager( this ) );

        FirebaseRecyclerOptions<User2> options
                = new FirebaseRecyclerOptions.Builder<User2>()
                .setQuery( mbase, User2.class )
                .build();

        data_Adapter = new Data_Adapter( options );
        rv.setAdapter( data_Adapter );
    }

    @Override
    protected void onStart() {
        super.onStart();
        data_Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        data_Adapter.stopListening();
    }
}
