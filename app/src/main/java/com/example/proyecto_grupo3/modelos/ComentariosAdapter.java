package com.example.proyecto_grupo3.modelos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_grupo3.R;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {
    private List<Comentario> comentarios;

    // Constructor del adaptador que recibe la lista de comentarios
    public ComentariosAdapter(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada elemento de comentario
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        // Obtener el comentario en la posición actual y vincularlo al ViewHolder
        Comentario comentario = comentarios.get(position);
        holder.bind(comentario);
    }

    @Override
    public int getItemCount() {
        // Devolver la cantidad total de comentarios en la lista
        return comentarios.size();
    }

    // Clase interna que representa el ViewHolder para cada elemento de comentario
    static class ComentarioViewHolder extends RecyclerView.ViewHolder {
        TextView textNombreUsuario;
        TextView textContenidoComentario;

        // Constructor del ViewHolder que inicializa los elementos de la interfaz de usuario
        ComentarioViewHolder(View itemView) {
            super(itemView);
            textNombreUsuario = itemView.findViewById(R.id.textNombreUsuario);
            textContenidoComentario = itemView.findViewById(R.id.textContenidoComentario);
        }

        // Método para enlazar datos de un comentario específico al ViewHolder
        void bind(Comentario comentario) {
            textNombreUsuario.setText(comentario.getIdUsuario());
            textContenidoComentario.setText(comentario.getComentarios().toString());
        }
    }
}
