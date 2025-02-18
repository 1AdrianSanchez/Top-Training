package com.example.sanchezadrian_appentrenamientos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EjercicioAdapter adapter;
    private static List<Ejercicio> listaEjercicios;

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

        BottomNavigationView navigator = findViewById(R.id.bottom_navigation);
        navigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_buscar) {
                    startActivity(new Intent(MainActivity.this, BuscarActivity.class));
                    return true;
                } else if (itemId == R.id.nav_perfil) {
                    startActivity(new Intent(MainActivity.this, PerfilActivity.class));
                    return true;
                } else if (itemId == R.id.nav_subir) {
                    startActivity(new Intent(MainActivity.this, SubirEjercicioActivity.class));
                    return true;
                }

                return false;
            }
        });
    }

    public static void agregarEjercicio(Ejercicio ejercicio) {
        listaEjercicios.add(ejercicio);
    }
}