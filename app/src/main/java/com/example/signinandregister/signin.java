package com.example.signinandregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class signin extends AppCompatActivity {
    Button btn1,btn2;
    TextInputEditText tiet2,tiet3;
    DatabaseReference database;
    cd c;

    private void findId(){
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        tiet2=findViewById(R.id.tiet2);
        tiet3=findViewById(R.id.tiet3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        findId();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(int1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid=tiet2.getText().toString();
                String paswd=tiet3.getText().toString();
                database=FirebaseDatabase.getInstance().getReference("user");
                if(uid.isEmpty()){
                    Toast.makeText(signin.this, "Enter ID first", Toast.LENGTH_SHORT).show();
                }else{



                    database.child(uid).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                        @Override
                        public void onSuccess(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                c=dataSnapshot.getValue(cd.class);


                                if((c.password).equals(paswd)){
                                    Intent int2=new Intent(getApplicationContext(), result.class);
                                    int2.putExtra("name",c.name);
                                    int2.putExtra("uid",c.uid);
                                    startActivity(int2);
                                }else{
                                    Toast.makeText(getApplicationContext(),"Password is not correct",Toast.LENGTH_SHORT).show();
                                    tiet3.setText("");
                                }
                            }else{
                                Toast.makeText(getApplicationContext(),"Sorry , UID not found ",Toast.LENGTH_SHORT).show();
                                tiet3.setText("");
                                tiet2.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(),"Sorry , UID not found ",Toast.LENGTH_SHORT).show();
                            tiet3.setText("");
                            tiet2.setText("");
                        }
                    });



                }


            }
        });
    }
}