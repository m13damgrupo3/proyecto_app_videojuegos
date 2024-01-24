package com.example.proyecto_grupo3;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyecto_grupo3.modelos.CustomListAdapter;
import com.example.proyecto_grupo3.modelos.CustomObject;
import com.example.proyecto_grupo3.modelos.SpaceItemDecoration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class PaginaIndividual extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomListAdapter customListAdapter;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_individual);

        // Referencia al RecyclerView en el diseño
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Configuración del administrador de diseño lineal
        recyclerView.addItemDecoration(new SpaceItemDecoration(18)); // Ajusta el espacio entre los elementos del RecyclerView

        // Inicializa la base de datos Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference().child("4");

        // Recupera datos de la base de datos Firebase
        getDataFromDatabase();
    }

    // Método para recuperar datos de la base de datos Firebase
    private void getDataFromDatabase() {
        final ArrayList<CustomObject> dataList = new ArrayList<>();

        // Escucha eventos de cambio en los datos de la base de datos
        databaseReference.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CustomObject customObject = snapshot.getValue(CustomObject.class);
                    if (customObject != null && "individual".equals(customObject.getTipo())) {
                        // Configura atributos adicionales del objeto CustomObject
                        customObject.setIdVideojuego(String.valueOf(snapshot.child("idVideojuego")));
                        customObject.setImageUrl1(String.valueOf(snapshot.child("url1")));
                        customObject.setImageUrl2(String.valueOf(snapshot.child("url2")));
                        customObject.setImageUrl3(String.valueOf(snapshot.child("url3")));
                        customObject.setImageUrl4(String.valueOf(snapshot.child("url4")));

                        String url1 = customObject.getImageUrl1();
                        String prefix = "value = ";
                        int startIndex = url1.indexOf(prefix) + prefix.length();
                        url1 = url1.substring(startIndex, url1.length() - 2); // -2 para eliminar el espacio y }

                        // Registro de información en el Logcat
                        // Log.d("FirebaseDebug individual", "Videojuego: "+customObject.getNombre()+" ImageUrl1 " + url1);

                        dataList.add(customObject);
                    }
                }

                // Configura el adaptador personalizado para el RecyclerView
                customListAdapter = new CustomListAdapter(PaginaIndividual.this, dataList);
                recyclerView.setAdapter(customListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Maneja errores si es necesario
            }
        });
    }
}
