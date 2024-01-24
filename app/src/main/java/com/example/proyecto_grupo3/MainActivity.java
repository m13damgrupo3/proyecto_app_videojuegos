package com.example.proyecto_grupo3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private Button inicioSesion;
    private Button crearUsuario;
    private EditText usuarioEditText;
    private EditText contrasenaEditText;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas y Firebase
        inicioSesion = findViewById(R.id.inicio_sesion);
        crearUsuario = findViewById(R.id.crearUsuario);
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");

        // Configurar clics de botones
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Método para manejar el inicio de sesión exitoso
                inicioSesionExitoso();
            }
        });

        crearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad para crear un nuevo usuario (CrearCuentaActivity)
                Intent intent = new Intent(MainActivity.this, CrearCuenta.class);
                startActivity(intent);
            }
        });
    }

    // Método para manejar el inicio de sesión exitoso
    private void inicioSesionExitoso() {
        // Usar mAuth para iniciar sesión con el usuario
        String username = usuarioEditText.getText().toString().trim();
        String password = contrasenaEditText.getText().toString().trim();

        mDatabase.child(username).child("password").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String storedPasswordHash = String.valueOf(task.getResult().getValue());

                // Hashear la contraseña ingresada para comparación
                String enteredPasswordHash = hashPassword(password);
                // Verificar si la contraseña ingresada coincide con la contraseña almacenada
                if (enteredPasswordHash != null && enteredPasswordHash.equals(storedPasswordHash)) {
                    // Inicio de sesión exitoso, abrir PaginaPrincipal
                    Intent intent = new Intent(MainActivity.this, PaginaPrincipal.class);
                    startActivity(intent);
                    // Cerrar la actividad actual si es necesario
                    finish();
                } else {
                    // Las contraseñas no coinciden, mostrar un mensaje de error
                    Toast.makeText(MainActivity.this, "Inicio de sesión fallido. La cuenta no existe.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Manejar errores
            }
        });
    }

    // Método para hashear la contraseña usando SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejar errores de hasheo
            return null;
        }
    }
}
