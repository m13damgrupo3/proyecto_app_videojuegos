package com.example.proyecto_grupo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PaginaPrincipal extends AppCompatActivity {

    private Button btnCooperativo;
    private Button btnIndividual;
    private Button btnPantallaDividida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_principal);

        // Inicializar botones
        btnCooperativo = findViewById(R.id.cooperativo);
        btnIndividual = findViewById(R.id.individual);
        btnPantallaDividida = findViewById(R.id.pantalla_dividida);

        // Configurar clics en botones
        btnCooperativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para abrir la página cooperativa
                abrirPaginaCooperativo();
            }
        });

        btnIndividual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para abrir la página individual
                abrirPaginaIndividual();
            }
        });

        btnPantallaDividida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para abrir la página de pantalla dividida
                abrirPaginaPantallaDividida();
            }
        });
    }

    private void abrirPaginaCooperativo() {
        // Crear un intent para abrir la página cooperativa
        Intent intent = new Intent(PaginaPrincipal.this, PaginaCooperativo.class);
        startActivity(intent);
    }

    private void abrirPaginaIndividual() {
        // Crear un Intent para abrir la página individual
        Intent intent = new Intent(PaginaPrincipal.this, PaginaIndividual.class);
        startActivity(intent);
    }

    private void abrirPaginaPantallaDividida() {
        // Crear un Intent para abrir la página de pantalla dividida
        Intent intent = new Intent(PaginaPrincipal.this, PaginaPantallaDividida.class);
        startActivity(intent);
    }
}
