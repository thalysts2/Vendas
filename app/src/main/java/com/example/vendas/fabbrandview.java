package com.example.vendas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class fabbrandview extends AppCompatActivity {
    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabbrandview);
        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("supervenda", Context.MODE_PRIVATE, null);
        final Cursor c = db.rawQuery("select * from fabricante", null);
        int id = c.getColumnIndex("id");
        final int fabricante = c.getColumnIndex("fabricante");
        int fabdes = c.getColumnIndex("fabdes");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, titles);

        lst1.setAdapter(arrayAdapter);
        final ArrayList<fab> brann = new ArrayList<fab>();

        if (c.moveToNext()) {
            do {
                fab br = new fab();
                br.id = c.getString(id);
                br.fabricante = c.getString(fabricante);
                br.des = c.getString(fabdes);
                brann.add(br);

                titles.add(c.getString(id) + "\t" + c.getString(fabricante) + "\t" + c.getString(fabdes) + "\t");


            } while (c.moveToNext());
            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }
        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aaa = titles.get(position).toString();
                fab br = brann.get((position));
                Intent i = new Intent(getApplicationContext(), fabeditar.class);
                i.putExtra("id", br.id);
                i.putExtra("fabricante", br.fabricante);
                i.putExtra("des", br.des);

                startActivity(i);
            }
        });

    }
}
