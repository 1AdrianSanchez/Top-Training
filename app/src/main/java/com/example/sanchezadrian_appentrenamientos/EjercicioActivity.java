package com.example.sanchezadrian_appentrenamientos;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EjercicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ejercicio");

        TextView textTitulo = findViewById(R.id.textView);
        TextView textCategoria = findViewById(R.id.textView2);
        TextView textDescripcion = findViewById(R.id.textView3);
        ImageView imageEjercicio = findViewById(R.id.imageView);

        //Obtener datos del intent
        String titulo = getIntent().getStringExtra("titulo");
        String categoria = getIntent().getStringExtra("categoria");
        String descripcion = getIntent().getStringExtra("descripcion");
        String imagenUrl = getIntent().getStringExtra("imagen");

        //Mostrar los datos en la interfaz del usuario
        textTitulo.setText(titulo);
        textCategoria.setText(categoria);
        textDescripcion.setText(descripcion);

        //Cargar imagen (si tienes una URL válida)
        //Glide.with(this).load(imagenUrl).into(imageEjercicio);
    }
}