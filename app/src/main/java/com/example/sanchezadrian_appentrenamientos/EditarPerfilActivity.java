package com.example.sanchezadrian_appentrenamientos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditarPerfilActivity extends AppCompatActivity {
    private static final int CODIGO_IMAGEN = 1;
    private Uri imageUri;
    ImageView imagen;
    EditText editText;
    SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Editar perfil");

        imagen = findViewById(R.id.imageView3);
        editText = findViewById(R.id.editTextText);
        Button cambiarFoto = findViewById(R.id.cambiar_foto);
        Button guardarCambios = findViewById(R.id.guardar_cambios);

        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);

        //Cargar datos guardados previamente
        cargarDatosGuardados();

        cambiarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //Verifica si ya se tiene el permiso de llamada
//                if (ContextCompat.checkSelfPermission(EditarPerfilActivity.this, android.Manifest.permission.READ_MEDIA_IMAGES)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    // Si no se tiene el permiso, solicita el permiso
//                    ActivityCompat.requestPermissions(EditarPerfilActivity.this,
//                            new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, CODIGO_IMAGEN);
//                } else {
//                    //Si se tiene el permiso, abre la galeria
//                    abrirGaleria();
//                }
                abrirGaleria();
            }
        });

        guardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
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
        if (requestCode == CODIGO_IMAGEN) {
            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                //Si el usuario selecciona una imagen, la mostramos
                imageUri = data.getData();
                imagen.setImageURI(imageUri);
            } else if (resultCode == RESULT_CANCELED) {
                //Si el usuario cancela, volvemos a la imagen predeterminada
                imagen.setImageResource(R.drawable.bordalas);
            }
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == CODIGO_IMAGEN) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //Realizamos la acción
//                abrirGaleria();
//            } else {
//                Toast.makeText(this,"Sin el permiso, no puedo realizar la acción",Toast.LENGTH_LONG).show();
//            }
//        }
//    }

    private void guardarCambios() {
        String nuevoNombre = editText.getText().toString().trim();
        String nombreGuardado = prefe.getString("nombre_usuario", "");

        if (nuevoNombre.isEmpty() && imageUri == null) {
            Toast.makeText(this, "Debe introducir un nombre o cambiar la foto", Toast.LENGTH_SHORT).show();
            return;
        } else if (nombreGuardado.equals(nuevoNombre) && imageUri == null) {
            Toast.makeText(this, "El nombre es el mismo y no se ha cambiado la imagen", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = prefe.edit();

        if (!nuevoNombre.isEmpty()) {
            editor.putString("nombre_usuario", nuevoNombre);
        }

        if (imageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] imageBytes = byteArrayOutputStream.toByteArray();
                String imageString = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
                editor.putString("imagen_perfil", imageString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //Si NO hay imagen seleccionada, guardar bordalas.png como predeterminada
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bordalas);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            String imageString = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
            editor.putString("imagen_perfil", imageString);
        }

        editor.apply();
        Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show();
        finish(); //Cierra la actividad y vuelve al perfil
    }

    private void cargarDatosGuardados() {
        String nombreGuardado = prefe.getString("nombre_usuario", "");
        String imagenGuardada = prefe.getString("imagen_perfil", "");

        if (!nombreGuardado.isEmpty()) {
            editText.setText(nombreGuardado);
        }

        if (!imagenGuardada.isEmpty()) {
            byte[] imageBytes = android.util.Base64.decode(imagenGuardada, android.util.Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imagen.setImageBitmap(bitmap);
        } else {
            //Si NO hay imagen guardada, usar bordalas.png como predeterminada
            imagen.setImageResource(R.drawable.bordalas);
            imageUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.bordalas);
        }
    }
}