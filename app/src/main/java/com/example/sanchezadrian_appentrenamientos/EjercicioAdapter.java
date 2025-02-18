package com.example.sanchezadrian_appentrenamientos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder> {
    private List<Ejercicio> ejercicios;
    private Context context;


    public EjercicioAdapter(List<Ejercicio> ejercicios, Context context) {
        this.ejercicios = ejercicios;
        this.context = context;
    }

    @NonNull
    @Override
    public EjercicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicio, parent, false);
        return new EjercicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EjercicioViewHolder holder, int position) {
        Ejercicio ejercicio = ejercicios.get(position);
        holder.titulo.setText(ejercicio.getTitulo());
        holder.categoria.setText(ejercicio.getCategoria());
        holder.imagen.setImageResource(ejercicio.getImagen());

        //Si necesitas cargar imágenes, usa Glide o Picasso
        //Glide.with(context).load(ejercicio.getImagen()).into(holder.imagen);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EjercicioActivity.class);
                intent.putExtra("titulo", ejercicio.getTitulo());
                intent.putExtra("categoria", ejercicio.getCategoria());
                intent.putExtra("descripcion", ejercicio.getDescripcion());
                intent.putExtra("imagen", ejercicio.getImagen());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    public static class EjercicioViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView categoria;
        ImageView imagen;

        public EjercicioViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            categoria = itemView.findViewById(R.id.textCategoria);
            imagen = itemView.findViewById(R.id.imageEjercicio);
        }
    }
}
