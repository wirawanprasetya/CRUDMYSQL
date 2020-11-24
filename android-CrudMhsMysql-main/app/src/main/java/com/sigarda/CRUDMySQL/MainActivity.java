package com.sigarda.CRUDMySQL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button lihat, tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lihat = findViewById(R.id.lihat);
        tambah = findViewById(R.id.tambah);
        Toast.makeText(this,"OK unit",Toast.LENGTH_SHORT).show();
        tambah.setOnClickListener(tambah->{
            Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,AddMhs.class);
            startActivity(i);
        });
        lihat.setOnClickListener(lihat->{
            Toast.makeText(this,"OK",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,ListMhs.class);
            startActivity(i);
        });
    }
}