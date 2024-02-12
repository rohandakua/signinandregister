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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    TextInputEditText tiet1,tiet2,tiet3;

    DatabaseReference database;

    private void findId(){
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        tiet1=findViewById(R.id.tiet1);
        tiet2=findViewById(R.id.tiet2);
        tiet3=findViewById(R.id.tiet3);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                //
                {
                    if(tiet1.getText().toString().isEmpty() || tiet2.getText().toString().isEmpty() || tiet3.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Enter data in all the fields",Toast.LENGTH_SHORT).show();
                    }else{
                        cd data=new cd(tiet1.getText().toString(),tiet2.getText().toString(),tiet3.getText().toString());
                        String ui=tiet2.getText().toString();
                        database=FirebaseDatabase.getInstance().getReference("user");
                        database.child(ui).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                tiet1.setText("");
                                tiet2.setText("");
                                tiet3.setText("");

                                Toast.makeText(getApplicationContext(),"registered successfully :) ",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Failed ,Sorry :(", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }}
        );


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1=new Intent(getApplicationContext(), signin.class);
                startActivity(int1);
            }
        });
    }
}