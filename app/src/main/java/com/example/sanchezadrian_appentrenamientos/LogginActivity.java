package com.example.sanchezadrian_appentrenamientos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogginActivity extends AppCompatActivity {
    private EditText correo;
    private EditText contraseña;
    private Button loggin;
    private SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loggin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio Sesion");

        correo = findViewById(R.id.loggin_correo);
        contraseña = findViewById(R.id.loggin_contraseña);
        loggin = findViewById(R.id.loggin);

        prefe = getSharedPreferences("sesion", Context.MODE_PRIVATE);

        //Comprobamos si ya hay una sesión iniciada
        if (prefe.getBoolean("isLoggedIn", false)) {
            // Si ya está logueado, saltamos a MainActivity
            startActivity(new Intent(LogginActivity.this, MainActivity.class));
            finish();
        }

        loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = correo.getText().toString().trim();
                String password = contraseña.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    //Validaciones
                    if (email.equals("prueba") && password.equals("123456")) {
                        //Guardamos que el usuario está logueado
                        SharedPreferences.Editor editor = prefe.edit();
                        editor.putBoolean("isLoggedIn", true);
                        editor.apply();

                        //Iniciar la actividad principal
                        startActivity(new Intent(LogginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LogginActivity.this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LogginActivity.this, "Introduce todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}