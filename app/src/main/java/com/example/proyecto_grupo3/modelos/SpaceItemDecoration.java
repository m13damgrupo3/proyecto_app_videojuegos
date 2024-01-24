package com.example.proyecto_grupo3.modelos;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    // Constructor que recibe el espacio entre elementos como parámetro
    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    // Método que establece los desplazamientos entre elementos
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;   // Espacio a la izquierda del elemento
        outRect.right = space;  // Espacio a la derecha del elemento
        outRect.bottom = space; // Espacio en la parte inferior del elemento

        // Agregar margen superior solo al primer elemento para evitar espacio doble entre elementos
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;  // Espacio en la parte superior del primer elemento
        } else {
            outRect.top = 0;      // Sin espacio superior para los demás elementos
        }
    }
}
