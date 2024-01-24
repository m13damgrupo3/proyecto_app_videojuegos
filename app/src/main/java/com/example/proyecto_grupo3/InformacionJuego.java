package com.example.proyecto_grupo3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyecto_grupo3.modelos.Comentario;
import com.example.proyecto_grupo3.modelos.ComentariosAdapter;
import com.example.proyecto_grupo3.modelos.CustomObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class InformacionJuego extends AppCompatActivity {

    private ImageView imagenJuego;
    private ImageView tierListimg;
    private TextView nombreJuego;
    private TextView descripcionJuego;
    private RecyclerView recyclerViewComentarios;
    private DatabaseReference databaseReference;
    private String gameId;
    private List<Comentario> listaComentarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion_juego);

        // Obtener referencias a tus vistas
        imagenJuego = findViewById(R.id.imagenjuego);
        tierListimg = findViewById(R.id.tierlistimg);
        nombreJuego = findViewById(R.id.nombrejuego);
        descripcionJuego = findViewById(R.id.descripcionjuego);
        recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios);

        // Configurar la RecyclerView
        recyclerViewComentarios.setLayoutManager(new LinearLayoutManager(this));

        // Obtener el CustomObject del intent
        CustomObject selectedObject = getIntent().getParcelableExtra("selectedObject");

        // Inicializar la base de datos de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("2");

        // Inicializar la lista de comentarios
        listaComentarios = new ArrayList<>();

        // Cargar comentarios desde Firebase
        loadCommentsFromFirebase(selectedObject.getIdVideojuego());

        // Cargar novedades
        loadNovedadesFromFirebase(selectedObject.getIdVideojuego());

        // Rellenar las vistas con datos de CustomObject
        if (selectedObject != null) {
            nombreJuego.setText(selectedObject.getNombre());
            descripcionJuego.setText(selectedObject.getDescripcion());

            String url2 = selectedObject.getImageUrl2();
            String url3 = selectedObject.getImageUrl3();
            String url4 = selectedObject.getImageUrl4();

            String prefix = "value = ";
            int startIndex = url2.indexOf(prefix) + prefix.length();
            url2 = url2.substring(startIndex, url2.length() - 2); // -2 para eliminar el espacio y }
            url3 = url3.substring(startIndex, url3.length() - 2); // -2 para eliminar el espacio y }

            url4 = url4.substring(startIndex, url4.length() - 2); // -2 para eliminar el espacio y }

            // Cargar imagen usando Picasso u otra biblioteca de carga de imágenes
            Picasso.get().load(url2).into(imagenJuego);
            Picasso.get().load(url4).into(tierListimg);

            WebView webView = findViewById(R.id.videoView);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Reemplazar "YOUR_YOUTUBE_VIDEO_ID" con el ID real del video de YouTube
            String videoId = url3;
            String htmlContent = "<html><body style='margin:0px;padding:0px;'><iframe width='100%' height='100%' src='https://www.youtube.com/embed/" + videoId + "?autoplay=1' frameborder='0' allowfullscreen></iframe></body></html>";

            webView.loadData(htmlContent, "text/html", "utf-8");

            // Mostrar un Toast para pruebas
            // Toast.makeText(this, "Nombre: " + selectedObject.getNombre() + "\nDescripcion: " + selectedObject.getDescripcion(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadCommentsFromFirebase(String hello) {
        String numericValue = hello.replaceAll("[^0-9]+", "");
        databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaComentarios.clear(); // Limpiar lista antes de cargar nuevos comentarios

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String commentIdVideojuego = data.child("idVideojuego").getValue(String.class);

                    if (numericValue.equals(commentIdVideojuego)) {
                        Comentario comentario = new Comentario();
                        comentario.setIdUsuario(data.child("idUsuario").getValue(String.class));
                        comentario.setIdVideojuego(data.child("idVideojuego").getValue(String.class));
                        comentario.adComentario(data.child("comentario").getValue(String.class));
                        listaComentarios.add(comentario);
                    }
                }

                // Notificar al adaptador sobre el cambio en los datos
                ComentariosAdapter adapter = new ComentariosAdapter(listaComentarios);
                recyclerViewComentarios.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                // Mensaje de depuración
                // Log.d("FirebaseDebug", "Número de comentarios cargados: " + listaComentarios.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores si es necesario
                Toast.makeText(InformacionJuego.this, "Error de carga de comentarios: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNovedadesFromFirebase(String gameId) {
        // Después de cargar las novedades (o cualquier otro criterio que determines)
        TextView textViewNovedades = findViewById(R.id.novedades);

        // Muestra siempre el mensaje informativo
        String nuevoTexto = "Pronto más novedades para saber más acerca de este asombroso juego";
        textViewNovedades.setText(nuevoTexto);
        textViewNovedades.setVisibility(View.VISIBLE);
    }
}
