package                             com.c.realtimecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.c.realtimecrud.Pojo.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputLayout name, contact, email;
    Button mbtnInsert,mbtnUpdate,mbtnShow;

    List<User> users;

    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);

        getSupportActionBar().hide();
        getSupportActionBar().setTitle("LoginPage");


        name=findViewById(R.id.nametext);
        contact=findViewById(R.id.contacttext);
        email=findViewById(R.id.emailtext);
        mbtnInsert=findViewById(R.id.btnInsert);
        mbtnUpdate=findViewById(R.id.btnUpdate);
        mbtnShow=findViewById(R.id.btnShow);


        users = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference( "Users" );


        mbtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                processinsert(name.getEditText().getText().toString(),contact.getEditText().getText().toString(),email.getEditText().getText().toString());

            }
        });

        mbtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Update.class));

            }
        });

        mbtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ListActivity.class));
            }
        });

    }

    private void processinsert(String toString, String toString1, String toString2) {

       String Name = toString.toString().trim();
        String Email = toString1.toString().trim();
        String Id = toString2.toString().trim();

        if (!TextUtils.isEmpty( Name )) {
            User user = new User();
            user.setId(Id);
            user.setName(Name);
            user.setEmail(Email);

            databaseReference.child( "user" ).push();
            databaseReference.setValue( user );

            name.getEditText().setText("");
            contact.getEditText().setText("");
            email.getEditText().setText("");


            Toast.makeText(MainActivity.this, "Data Uploaded........", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText( this, "Please Enter Data", Toast.LENGTH_SHORT ).show();
        }



    }
}