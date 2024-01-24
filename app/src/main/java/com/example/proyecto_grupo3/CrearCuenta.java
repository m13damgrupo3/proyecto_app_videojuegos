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

public class CrearCuenta extends AppCompatActivity {

    // Declaraciones de variables para elementos de la interfaz de usuario
    private EditText usuarioEditText;
    private EditText contrasenaEditText, contrasenaEditText2;
    private Button crearCuentaButton;
    private Button botonAtras;

    // Instancias de FirebaseAuth y DatabaseReference
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta);

        // Inicialización de elementos de la interfaz de usuario y Firebase
        usuarioEditText = findViewById(R.id.usuario);
        contrasenaEditText = findViewById(R.id.contrasena);
        contrasenaEditText2 = findViewById(R.id.contrasena2);
        crearCuentaButton = findViewById(R.id.crear_cuenta);
        botonAtras = findViewById(R.id.botonAtras);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");

        // Configuración del botón "Atrás"
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para manejar el clic del botón "Atrás"
                onBackPressed();
            }
        });

        // Configuración del botón "Crear Cuenta"
        crearCuentaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el contenido de los campos de usuario y contraseña
                String usuario = usuarioEditText.getText().toString().trim();
                String contrasena = contrasenaEditText.getText().toString().trim();
                String contrasena2 = contrasenaEditText2.getText().toString().trim();

                // Verificar si los campos están vacíos
                if (usuario.isEmpty() || contrasena.isEmpty()) {
                    // Mostrar un mensaje de error si algún campo está vacío
                    Toast.makeText(CrearCuenta.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (usuario.length() < 4 || contrasena.length() < 4) {
                    // Mostrar un mensaje de error si el usuario o la contraseña tienen menos de 4 caracteres
                    Toast.makeText(CrearCuenta.this, "Usuario y contraseña deben tener al menos 4 caracteres", Toast.LENGTH_SHORT).show();
                } else if (!contrasena.equals(contrasena2)) {
                    // Mostrar un mensaje de error si las contraseñas no coinciden
                    Toast.makeText(CrearCuenta.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                } else {
                    // Crear la cuenta
                    createAccount();
                    // Abrir la actividad CuentaCreada
                    Intent intent = new Intent(CrearCuenta.this, CuentaCreada.class);
                    startActivity(intent);
                }
            }
        });
    }

    // Método para crear una cuenta de usuario
    private void createAccount() {
        // Utilizar mAuth para crear una nueva cuenta de usuario
        String username = usuarioEditText.getText().toString().trim();
        String password = contrasenaEditText.getText().toString().trim();
        String hashedPassword = hashPassword(password);

        // Verificar si el nombre de usuario ya existe
        mDatabase.child(username).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().exists()) {
                    // El nombre de usuario ya existe, mostrar un mensaje de error
                    Toast.makeText(CrearCuenta.this, "El nombre de usuario ya existe.", Toast.LENGTH_SHORT).show();
                } else {
                    // El nombre de usuario no existe, crear una nueva cuenta
                    mDatabase.child(username).child("password").setValue(hashedPassword);
                    // Proceder a tu actividad CuentaCreada o cualquier otra lógica
                    Toast.makeText(CrearCuenta.this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show();
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
            // Manejar error de hash
            return null;
        }
    }
}
