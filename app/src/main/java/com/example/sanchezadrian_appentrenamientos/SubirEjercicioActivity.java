package com.example.sanchezadrian_appentrenamientos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubirEjercicioActivity extends AppCompatActivity {
    private static final int CODIGO_IMAGEN = 1;
    private Uri imageUri;
    EditText textTitulo;
    EditText textCategoria;
    EditText textDescripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subir_ejercicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Subir entrenamiento");

        textTitulo = findViewById(R.id.text_titulo);
        textCategoria = findViewById(R.id.text_categoria);
        textDescripcion = findViewById(R.id.text_descripcion);
        imagen = findViewById(R.id.imageView4);
        Button subirFoto = findViewById(R.id.subirImagen);
        Button subirEjercicio = findViewById(R.id.subirEjercicio);

        subirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        subirEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = textTitulo.getText().toString().trim();
                String categoria = textCategoria.getText().toString().trim();
                String descripcion = textDescripcion.getText().toString().trim();

                if (titulo.isEmpty() || categoria.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(SubirEjercicioActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Aqui iria la logica para guardar el ejercicio en una base de datos.
                    Ejercicio nuevoEjercicio = new Ejercicio(titulo, categoria, descripcion, R.drawable.entrenamiento, "1");
                    MainActivity.agregarEjercicio(nuevoEjercicio);
                    // Lo mostrate en un toast para simular que se guardo
                    Toast.makeText(SubirEjercicioActivity.this, "Ejercicio Subido: " + titulo, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, CODIGO_IMAGEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_IMAGEN && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);  // Convertir la URI a un Bitmap
                imagen.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}