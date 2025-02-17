package com.example.sanchezadrian_appentrenamientos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EjercicioAdapter adapter;
    private List<Ejercicio> listaEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("App Entrenamientos");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Datos metidos a mano (se deberian cargar desde una base de datos o API)
        listaEjercicios = new ArrayList<>();
        listaEjercicios.add(new Ejercicio("8x8 con transiciones + doble finalizacion",
                "Salida de balon", "Ejercicio para trabajar la salida de balon", R.drawable.entrenamiento, "1"));
        listaEjercicios.add(new Ejercicio("Juego de posicion 9x6",
                "Ataque Posicional", "Ejercicio para mejorar el ataque Posicional", R.drawable.entrenamiento, "2"));
        listaEjercicios.add(new Ejercicio("Finalizaciones con busqueda de 3er hombre",
                "Finalizacion", "Ejercicio de finalizaciones a porteria", R.drawable.entrenamiento, "3"));
        listaEjercicios.add(new Ejercicio("8x8 con transiciones + doble finalizacion",
                "Salida de balon", "Ejercicio para trabajar la salida de balon", R.drawable.entrenamiento, "1"));
        listaEjercicios.add(new Ejercicio("Juego de posicion 9x6",
                "Ataque Posicional", "Ejercicio para mejorar el ataque Posicional", R.drawable.entrenamiento, "2"));
        listaEjercicios.add(new Ejercicio("Finalizaciones con busqueda de 3er hombre",
                "Finalizacion", "Ejercicio de finalizaciones a porteria", R.drawable.entrenamiento, "3"));
        listaEjercicios.add(new Ejercicio("8x8 con transiciones + doble finalizacion",
                "Salida de balon", "Ejercicio para trabajar la salida de balon", R.drawable.entrenamiento, "1"));
        listaEjercicios.add(new Ejercicio("Juego de posicion 9x6",
                "Ataque Posicional", "Ejercicio para mejorar el ataque Posicional", R.drawable.entrenamiento, "2"));
        listaEjercicios.add(new Ejercicio("Finalizaciones con busqueda de 3er hombre",
                "Finalizacion", "Ejercicio de finalizaciones a porteria", R.drawable.entrenamiento, "3"));

        adapter = new EjercicioAdapter(listaEjercicios, this);
        recyclerView.setAdapter(adapter);

        Button buscar = findViewById(R.id.buscar_ejercicios);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarActivity.class);
                startActivity(intent);
            }
        });

        Button perfil = findViewById(R.id.perfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        });

    }
}