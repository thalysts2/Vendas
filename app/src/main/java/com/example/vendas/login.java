package com.example.vendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class login extends AppCompatActivity {
EditText ed1,ed2;
Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ed1=findViewById(R.id.user);
        ed2=findViewById(R.id.pass);

        b1=findViewById(R.id.btn1);
        b2=findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });
    }
    public void Login(){
        String username=ed1.getText().toString();
        String password=ed2.getText().toString();

        if(username.equals("")|| password.equals("")){
            Toast.makeText(this,"FAVOR DIGITAR AS INFORMAÇÕES CORRETAS",Toast.LENGTH_SHORT).show();
        }else if(username.equals("san") && password.equals("123")){
            Intent i = new Intent(login.this,Main.class);
            startActivity(i);
        }else{
            Toast.makeText(this,"Usuario ou senha incorreta",Toast.LENGTH_SHORT).show();
        }
    }
}
