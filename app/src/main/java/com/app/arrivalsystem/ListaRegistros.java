package com.app.arrivalsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Vector;

public class ListaRegistros extends AppCompatActivity {
    private ListView lv1;

    private Vector datosRegistro;
    private ArrayAdapter<String> adaptador1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registros);
        datosRegistro=consultaBD();
        adaptador1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datosRegistro);
        lv1=(ListView)findViewById(R.id.listView2);
        lv1.setAdapter(adaptador1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int i,
                                    long arg3) {

                String datosFoto[] =datosRegistro.elementAt(i).toString().split(" ");
                String idFoto = datosFoto[0]; // Obtiene ID del String


                ver(idFoto);

            }});
    }
    public void ver (String id) {
        Toast.makeText(this, "Registro: "+id,
                Toast.LENGTH_SHORT).show();
    }

    public Vector consultaBD(){

        Vector v = new Vector();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select * from datos", null);
        if(fila.moveToFirst()){
            do {
                // Obtiene 2 primeros campos de la Tabla
                String cadena = "";
                cadena+=fila.getString(0)+" ";
                cadena+=fila.getString(1)+" ";
                cadena+=fila.getString(2)+" ";
                cadena+=fila.getString(3);

                v.add(cadena);

            } while (fila.moveToNext());
            bd.close();


        } else {
            Toast.makeText(this, "No hay registros",
                    Toast.LENGTH_SHORT).show();
        }

        return v;

    }

}