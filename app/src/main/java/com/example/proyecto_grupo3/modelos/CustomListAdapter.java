package com.example.proyecto_grupo3.modelos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto_grupo3.InformacionJuego;
import com.example.proyecto_grupo3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CustomObject> customObjectList;

    // Constructor del adaptador que recibe el contexto y la lista de objetos personalizados
    public CustomListAdapter(Context context, ArrayList<CustomObject> customObjectList) {
        this.context = context;
        this.customObjectList = customObjectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de cada elemento de la lista
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtener el objeto personalizado en la posición actual y vincularlo al ViewHolder
        CustomObject selectedObject = customObjectList.get(position);

        // Obtener y cargar la URL de la imagen utilizando Picasso
        String url1 = selectedObject.getImageUrl1();
        String prefix = "value = ";
        int startIndex = url1.indexOf(prefix) + prefix.length();
        url1 = url1.substring(startIndex, url1.length() - 2);
        Log.d("UrlImage", "Videojuego: "+selectedObject.getNombre()+" ImageUrl1 " + url1);
        Picasso.get().load(url1).into(holder.imageView);

        // Establecer el texto de nombre y descripción en el ViewHolder
        holder.textViewName.setText(selectedObject.getNombre());
        holder.textViewDescription.setText(selectedObject.getDescription());

        // Configurar el clic del elemento para abrir la actividad de InformacionJuego
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InformacionJuego.class);
                intent.putExtra("selectedObject", selectedObject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Devolver la cantidad total de objetos personalizados en la lista
        return customObjectList.size();
    }

    // Clase interna que representa el ViewHolder para cada elemento de la lista
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDescription;

        // Constructor del ViewHolder que inicializa los elementos de la interfaz de usuario
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewListaJuegos);
            textViewName = itemView.findViewById(R.id.textViewNombre);
            textViewDescription = itemView.findViewById(R.id.textViewDescripcion);
        }
    }
}
