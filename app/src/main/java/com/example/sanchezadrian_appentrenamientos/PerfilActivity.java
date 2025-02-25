package com.example.sanchezadrian_appentrenamientos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PerfilActivity extends AppCompatActivity {
    ImageView imagen;
    TextView nombre;
    SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil");

        Button editarPerfil = findViewById(R.id.editar_perfil);
        editarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, EditarPerfilActivity.class);
                startActivity(intent);
            }
        });

        imagen = findViewById(R.id.imageView2);
        nombre = findViewById(R.id.textView4);
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);

        cargarDatosPerfil();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Recargar los datos cuando la actividad vuelva a estar activa
        cargarDatosPerfil();
    }

    private void cargarDatosPerfil() {
        String nombreGuardado = prefe.getString("nombre_usuario", "Nombre de Usuario");
        String imagenGuardada = prefe.getString("imagen_perfil", "");

        nombre.setText(nombreGuardado);

        if (!imagenGuardada.isEmpty()) {
            byte[] imageBytes = Base64.decode(imagenGuardada, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imagen.setImageBitmap(bitmap);
        } else {
            //Si no hay imagen guardada, usa la imagen predeterminada
            imagen.setImageResource(R.drawable.bordalas);
        }
    }
}