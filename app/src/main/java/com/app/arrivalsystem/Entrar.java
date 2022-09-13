package com.app.arrivalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Entrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

    }
    public void llegar(View view) {
        Intent i = new Intent(this, Llegada.class );
        startActivity(i);
    }
    public void regresar(View view){

        finish();
    }
}