package com.example.sanchezadrian_appentrenamientos;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class BuscarActivity extends AppCompatActivity {
    private EjercicioAdapter adapter;
    private List<Ejercicio> listaEjercicios;
    private List<Ejercicio> listaFiltrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Buscar");

        EditText busqueda = findViewById(R.id.search_bar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Lista de ejercicios
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

        listaFiltrada = new ArrayList<>(listaEjercicios);
        adapter = new EjercicioAdapter(listaFiltrada, this);
        recyclerView.setAdapter(adapter);

        //Configurar búsqueda en tiempo real
        busqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filtrarEjercicios(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Botones de filtro por categoría
        Button salidaBalon = findViewById(R.id.salida_balon);
        Button ataquePosicional = findViewById(R.id.ataque_posicional);
        Button finalizacion = findViewById(R.id.finalizacion);

        salidaBalon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarPorCategoria("Salida de balon");
            }
        });

        ataquePosicional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarPorCategoria("Ataque Posicional");
            }
        });

        finalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarPorCategoria("Finalizacion");
            }
        });
    }

    private void filtrarEjercicios(String texto) {
        listaFiltrada.clear();
        for (Ejercicio ejercicio : listaEjercicios) {
            if (ejercicio.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                listaFiltrada.add(ejercicio);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void filtrarPorCategoria(String categoria) {
        listaFiltrada.clear();
        for (Ejercicio ejercicio : listaEjercicios) {
            if (ejercicio.getCategoria().equalsIgnoreCase(categoria)) {
                listaFiltrada.add(ejercicio);
            }
        }
        adapter.notifyDataSetChanged();
    }
}