package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.Peces;
import mx.perse_care.undefinedsoft.perse_care.PreguntasParte3;
import mx.perse_care.undefinedsoft.perse_care.R;

public class AltaPez extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference bbdd;
    private DatabaseReference mRef;
    private EditText nombrePez;
    private int maxim=0;
    private FloatingActionButton guarda;
    private String[] SPINNERLISTDulce ={"Pez ángel", "Pez Betta", "Pez cebra", "Pez dorado", "Pez guppy", "Pez gurami", "Pez Koi", "Pez Limpia peceras", "Desconocido"};
    private String[] SPINNERLISTSalada= {"Desconocido", "Pez cirujano", "Pez Damisela", "Pez Payaso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_pez);
        mAuth= FirebaseAuth.getInstance();

        mRef= FirebaseDatabase.getInstance().getReference().child("Users");
        nombrePez=(EditText) findViewById(R.id.altaPez_nombre);
        guarda=(FloatingActionButton) findViewById(R.id.altaPez_guarda);
        final ArrayAdapter<String> arrayAdapterDulce= new ArrayAdapter<String>(AltaPez.this, android.R.layout.simple_dropdown_item_1line, SPINNERLISTDulce);
        final ArrayAdapter<String> arrayAdapterSalada=new ArrayAdapter<String>(AltaPez.this, android.R.layout.simple_dropdown_item_1line, SPINNERLISTSalada);
        final MaterialBetterSpinner spinnerPez1=(MaterialBetterSpinner) findViewById(R.id.altaPez_especie);


        bbdd = FirebaseDatabase.getInstance().getReference().child("Users");
        String user_id = mAuth.getCurrentUser().getUid();
        bbdd.child(user_id).child("InformacionPreguntas").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                            Peces pe = datasnapshot.getValue(Peces.class);
                            String tipoAgua = pe.getTipoAguaDePeces();
                            if(tipoAgua.equals("De agua dulce.")){
                                spinnerPez1.setAdapter(arrayAdapterDulce);
                            }else {
                                spinnerPez1.setAdapter(arrayAdapterSalada);
                            }
                            }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
        });

        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = mAuth.getCurrentUser().getUid();
                String name= nombrePez.getText().toString();
                maxim= (int) (Math.random()*300);
                if (name.isEmpty()){
                    Snackbar.make(v, "Por favor escribe un nombre", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }else {
                 Map<String, Object> datosPez= new HashMap<>();
                datosPez.put("nombre", name);
                datosPez.put("especie",spinnerPez1.getText().toString());
                datosPez.put("posicion", Integer.toString(maxim));
                mRef.child(user_id).child("InfoPeces").child(Integer.toString(maxim)).setValue(datosPez);
                Snackbar.make(v, "Datos Guardados", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            //    nombrePez.setText("");
             //   Snackbar.make(v, "nombre: "+ name+ " especie "+ spinnerPez1.getText()+ "posicion: "+ Integer.toString(maxim)+" " +id_user, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });
    }
}
