package com.app.arrivalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    DatabaseHelper db1;
    EditText et1,et2,et3,et4;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //base de datos
        db1= new DatabaseHelper(this);
        //num empleado
        et1=(EditText)findViewById(R.id.et1);
        //nombre user
        et2=(EditText)findViewById(R.id.et2);
        //contraseña
        et3=(EditText)findViewById(R.id.et3);
        //email
        et4=(EditText)findViewById(R.id.et4);
        //registro
        btn1=(Button)findViewById(R.id.btn1);
        //ya tengo una cuenta
        btn2=(Button)findViewById(R.id.btn2);
    }
    public void iniciarentrar(View view) {
        Intent i = new Intent(this, Entrar.class);
        startActivity(i);


    }
    public void registrar(View view) {
        //grabar datos
        String numEm = et1.getText().toString();
        String usuario = et2.getText().toString();
        String pass = et3.getText().toString();
        String email = et4.getText().toString();
        //metodo shared pref
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(usuario, usuario);
        editor.putString(pass, pass);
        editor.putString(email, email);
        editor.commit();

        Toast.makeText(this, " ¡ - GUARDADO CON ÉXITO - ! ", Toast.LENGTH_SHORT).show();

    }
    public void regresar(View view){

        finish();
    }
}