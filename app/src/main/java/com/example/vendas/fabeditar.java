package com.example.vendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fabeditar extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabeditar);

        ed1 = findViewById(R.id.fabid);
        ed2 = findViewById(R.id.fabricante);
        ed3 = findViewById(R.id.fabricantedes);

        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();

        String id = i.getStringExtra("id").toString();
        String fabricante = i.getStringExtra("fabricante").toString();
        String des = i.getStringExtra("fabricantedes").toString();

        ed1.setText(id);
        ed2.setText(fabricante);
        ed3.setText(des);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Main.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletar();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editar();
            }
        });
    }
    public void Editar() {
        try {
            String fabid = ed1.getText().toString();
            String fabricantenome = ed2.getText().toString();
            String fabdescrição = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "update fabricante set fabricante =?, fabdes =? where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fabricantenome);
            statement.bindString(2, fabdescrição);
            statement.bindString(3, fabid);
            statement.execute();
            Toast.makeText(this, "fabricante atualizada com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(this, "fabricante invalida jovem ", Toast.LENGTH_SHORT).show();
        }
    }
    public void Deletar() {
        try {
            String fabid = ed1.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
            String sql = "delete from fabricante where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, fabid);
            statement.execute();
            Toast.makeText(this, "Fabricante deletado com sucesso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(), Main.class);
            startActivity(i);
        } catch (Exception ex) {
            Toast.makeText(this, "Fabricante invalida jovem ", Toast.LENGTH_SHORT).show();

        }
    }
}
