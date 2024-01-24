package com.example.proyecto_grupo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CuentaCreada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuenta_creada);

        // Obtener referencia al botón "Volver" en el diseño
        Button volverButton = findViewById(R.id.volver);

        // Configurar un OnClickListener para el botón "Volver"
        volverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la pantalla de inicio de sesión (MainActivity)
                Intent intent = new Intent(CuentaCreada.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
