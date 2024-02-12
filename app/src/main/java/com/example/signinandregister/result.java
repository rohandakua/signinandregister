package com.example.signinandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {
    Button btn1;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        btn1=findViewById(R.id.btn1);
        tv1=findViewById(R.id.tv1);
        Bundle bundle=getIntent().getExtras();

        String name=bundle.getString("name");
        String uid=bundle.getString("uid");
        tv1.setText("Welcome "+name+"\nYour UID is = "+ uid);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3=new Intent(getApplicationContext(), signin.class);
                Toast.makeText(result.this, "Signing Out ....", Toast.LENGTH_SHORT).show();
                startActivity(int3);

            }
        });



    }

}