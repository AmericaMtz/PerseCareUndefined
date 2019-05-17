package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;

import mx.perse_care.undefinedsoft.perse_care.R;

public class FAQs1 extends AppCompatActivity {

    FloatingActionButton faqs, borrar, editar, subir;
    EditText pregunta;
    ListView muestraPregunta;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private int contador;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs1);

        subir=(FloatingActionButton) findViewById(R.id.enviarPregunta);
        pregunta=(EditText)findViewById(R.id.txtPregunta);
        muestraPregunta=(ListView) findViewById(R.id.listView);
        borrar=(FloatingActionButton)findViewById(R.id.eliminarPregunta);
        editar=(FloatingActionButton)findViewById(R.id.editarPregunta);
        faqs=(FloatingActionButton)findViewById(R.id.mandaFAQs);


        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.FAQss));
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado= new ArrayList<String>();

               for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){

                  FAQs faQss=  datasnapshot.getValue(FAQs.class);

                  String preguntax= faQss.getPregunta();
                  String respuestax= faQss.getRespuesta();
                  listado.add(preguntax);
                  listado.add(respuestax);
               }
               adaptador= new ArrayAdapter<String>(FAQs1.this, android.R.layout.simple_list_item_1, listado);
               muestraPregunta.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAuth= FirebaseAuth.getInstance();

        final DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();


        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             String tomaPregunta= pregunta.getText().toString();
                if(tomaPregunta.isEmpty()){
                    Toast.makeText(FAQs1.this, "Escribe primero tu pregunta", Toast.LENGTH_SHORT).show();
                    pregunta.requestFocus();
                }else {
                    //tomamos el id del usuario
                    final String user_id = mAuth.getCurrentUser().getUid();
                    //Guardamos aqui
                    guardamosAqui(tomaPregunta, user_id, mRootReference);

                }

            }
        });

        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FAQs1.this, MuestraFAQs.class);
                startActivity(intent);

            }
        });


    }

    private void guardamosAqui(String tomaPregunta, String user_id, DatabaseReference mRootReference) {
        Map<String, Object> datosPreguntas= new HashMap<>();
        datosPreguntas.put("pregunta", tomaPregunta);
        datosPreguntas.put("respuesta", "...");
        mRootReference.child("FAQs").push().setValue(datosPreguntas);
        Toast.makeText(FAQs1.this, "Pregunta enviada correctamente", Toast.LENGTH_LONG).show();
        pregunta.setText("");
        contador++;
    }
}
