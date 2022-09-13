package com.app.arrivalsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class Fotografia extends AppCompatActivity {
    private ImageView imagen1;
    private String rutaImagen;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotografia);
        imagen1=(ImageView)findViewById(R.id.imageView);
        btn1=(Button) findViewById(R.id.tomar);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });
    }
    private void tomarFoto() {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if(intento1.resolveActivity(getPackageManager())!= null) {
            File imagenArchivo = null;
            try {
                imagenArchivo = crearImagen();
            } catch (IOException ex) {
                Log.e("Error", ex.toString());
            }
            if (imagenArchivo != null) {
                Uri fotoUri = FileProvider.getUriForFile(this, "com.app.arrivalsystem.FileProvider", imagenArchivo);
                intento1.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(intento1, 1);
            }
        //}
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            //Bundle extras = data.getExtras();
            Bitmap imgBitmap = BitmapFactory.decodeFile(rutaImagen);
            imagen1.setImageBitmap(imgBitmap);
        }
    }
    private File crearImagen() throws IOException {
        String nombreImagen ="foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen =File.createTempFile(nombreImagen, ".jpg", directorio);
        rutaImagen =imagen.getAbsolutePath();
        return imagen;
    }
}