package com.app.arrivalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Llegada extends AppCompatActivity {
    private EditText h1;
    private Object ConexionSQLiteHelper;
    EditText campoId,campoNombre,campoTelefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llegada);
        campoId = (EditText)findViewById(R.id.campoId);
        campoNombre = (EditText)findViewById(R.id.campoNombre);
        campoTelefono = (EditText)findViewById(R.id.campoTelefono);


        ConexionSQLiteHelper = new ConexionSQLiteHelper(this, "bd_usuarios", null,1);
    }
    public void onClick(View view){
        Intent miIntent=null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(Llegada.this,Enviando.class);
                break;
        }
        if(miIntent!=null){
            startActivity(miIntent);
        }
    }

    public void llegar(View view) {
        //grabar datos
        String hora = h1.getText().toString();

        //metodo shared pref
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(hora, hora);
        editor.commit();
        Toast.makeText(this, " ¡ - LLEGADA GUARDADA CON ÉXITO - ! ", Toast.LENGTH_SHORT).show();
    }

    public void lista(View view) {
        Intent i = new Intent(this, Lista.class );
        startActivity(i);
    }
    public void Fotografia(View view) {
        Intent i = new Intent(this, Fotografia.class );
        startActivity(i);
    }

    public void regresar(View view){

        finish();
    }


    //ENVIA MSJ DE LLEGADA A CONTACTO
    public void whatsapp(View v) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "DATOS DE LLEGADA GUARDADOS CORRECTAMENTE -->  "
                + " / ID DE EMPLEADO : " + campoId.getText()
                + " / FECHA DEL DIA : " + currentDate
                + "/ HORA EXACTA  : " + currentTime);

        sendIntent.setType("text/plain");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(sendIntent, ""));
        sendIntent.putExtra(Intent.EXTRA_TEXT, new String[] {""});
        startActivity(sendIntent);
        alta(campoId.getText().toString(), currentDate,currentTime);
        //opens the portfolio details class


    }
    public void alta(String numeroempleado, String fecha, String hora) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("numeroempleado", numeroempleado);
        registro.put("fecha", fecha);
        registro.put("hora", hora);
        bd.insert("datos", null, registro);
        bd.close();
        Toast.makeText(this, "datos guardados correctamente",
                Toast.LENGTH_SHORT).show();
    }

    public void listarRegistos(View v){
        Intent i=new Intent(this,ListaRegistros.class);
        startActivity(i);
    }
}