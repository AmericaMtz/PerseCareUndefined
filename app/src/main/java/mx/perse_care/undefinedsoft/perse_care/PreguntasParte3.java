package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.peces;

public class PreguntasParte3 extends AppCompatActivity {

    private EditText cantidadPeces;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference bbdd;
    private DatabaseReference mRef;
    private Button listo, siguiente;
    private EditText p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    private String[] pez= new String[10];
    private String[] spinner= new String[10];
    private String[] spinnerHint= new String[10];
    private LinearLayout P1,P2,P3,P4,P5,P6,P7,P8,P9,P10;
    private String[] SPINNERLISTDulce ={"Carpas","Desconocido", "Killis", "Pez Ángel", "Pez Arcoiris", "Pez Betta", "Pez Disco", "Pez Espiga", "Pez Gato", "Pez Guppy", "Pez Molly"};
    private String[] SPINNERLISTSalada= {"Desconocido","Labrido Rayado", "Pez Cirujano", "Pez Damisela", "Pez Globo", "Pez León", "Pez Loro", "Pez Navaja", "Pez Payaso", "Pez Toro"};
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_preguntas_parte3);
         cantidadPeces=(EditText) findViewById(R.id.cantidadPeces);
         listo=(Button) findViewById(R.id.listo);
         siguiente=(Button) findViewById(R.id.siguiente);
         P1=(LinearLayout) findViewById(R.id.layoutPez1);
         P2=(LinearLayout) findViewById(R.id.layoutPez2);
         P3=(LinearLayout) findViewById(R.id.layoutPez3);
         P4=(LinearLayout) findViewById(R.id.layoutPez4);
         P5=(LinearLayout) findViewById(R.id.layoutPez5);
         P6=(LinearLayout) findViewById(R.id.layoutPez6);
         P7=(LinearLayout) findViewById(R.id.layoutPez7);
         P8=(LinearLayout) findViewById(R.id.layoutPez8);
         P9=(LinearLayout) findViewById(R.id.layoutPez9);
         P10=(LinearLayout) findViewById(R.id.layoutPez10);
         mRef= FirebaseDatabase.getInstance().getReference().child("Users");

         p1=(EditText) findViewById(R.id.Pez1);
         p2=(EditText) findViewById(R.id.Pez2);
         p3=(EditText) findViewById(R.id.Pez3);
         p4=(EditText) findViewById(R.id.Pez4);
         p5=(EditText) findViewById(R.id.Pez5);
         p6=(EditText) findViewById(R.id.Pez6);
         p7=(EditText) findViewById(R.id.Pez7);
         p8=(EditText) findViewById(R.id.Pez8);
         p9=(EditText) findViewById(R.id.Pez9);
         p10=(EditText) findViewById(R.id.Pez10);

        mAuth= FirebaseAuth.getInstance();

         firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
             @Override
             public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                 FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                 if (user== null){
                     Intent intent= new Intent(PreguntasParte3.this, PreguntasParte3.class);
                     startActivity(intent);
                     finish();
                     return;
                 }
             }
         };
         final ArrayAdapter<String> arrayAdapterDulce= new ArrayAdapter<String>(PreguntasParte3.this, android.R.layout.simple_dropdown_item_1line, SPINNERLISTDulce);
        final ArrayAdapter<String> arrayAdapterSalada=new ArrayAdapter<String>(PreguntasParte3.this, android.R.layout.simple_dropdown_item_1line, SPINNERLISTSalada);
        final MaterialBetterSpinner spinnerPez1=(MaterialBetterSpinner) findViewById(R.id.spinnerPez1);
        final MaterialBetterSpinner spinnerPez2=(MaterialBetterSpinner) findViewById(R.id.spinnerPez2);
        final MaterialBetterSpinner spinnerPez3=(MaterialBetterSpinner) findViewById(R.id.spinnerPez3);
        final MaterialBetterSpinner spinnerPez4=(MaterialBetterSpinner) findViewById(R.id.spinnerPez4);
        final MaterialBetterSpinner spinnerPez5=(MaterialBetterSpinner) findViewById(R.id.spinnerPez5);
        final MaterialBetterSpinner spinnerPez6=(MaterialBetterSpinner) findViewById(R.id.spinnerPez6);
        final MaterialBetterSpinner spinnerPez7=(MaterialBetterSpinner) findViewById(R.id.spinnerPez7);
        final MaterialBetterSpinner spinnerPez8=(MaterialBetterSpinner) findViewById(R.id.spinnerPez8);
        final MaterialBetterSpinner spinnerPez9=(MaterialBetterSpinner) findViewById(R.id.spinnerPez9);
        final MaterialBetterSpinner spinnerPez10=(MaterialBetterSpinner) findViewById(R.id.spinnerPez10);
        inicio();
        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = mAuth.getCurrentUser().getUid();
                bbdd = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("InformacionPreguntas");
               bbdd.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                           peces pe = datasnapshot.getValue(peces.class);
                           String tipoAgua = pe.getTipoAguaDePeces();
                           if(tipoAgua.equals("De agua dulce.")){
                               switch (cantidadPeces.getText().toString()){
                                   case "1":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.GONE);
                                       P3.setVisibility(View.GONE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "2":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.GONE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "3":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "4":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "5":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);

                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "6":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);

                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "7":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);

                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);

                                       break;
                                   case "8":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);

                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "9":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.VISIBLE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   default:
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.VISIBLE);
                                       P10.setVisibility(View.VISIBLE);
                                       SpinnerDulce(spinnerPez1, arrayAdapterDulce, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                               }
                           }else {
                               switch (cantidadPeces.getText().toString()) {
                                   case "1":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.GONE);
                                       P3.setVisibility(View.GONE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "2":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.GONE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "3":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.GONE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "4":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.GONE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "5":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.GONE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "6":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.GONE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "7":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.GONE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "8":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.GONE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   case "9":
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.VISIBLE);
                                       P10.setVisibility(View.GONE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                                   default:
                                       P1.setVisibility(View.VISIBLE);
                                       P2.setVisibility(View.VISIBLE);
                                       P3.setVisibility(View.VISIBLE);
                                       P4.setVisibility(View.VISIBLE);
                                       P5.setVisibility(View.VISIBLE);
                                       P6.setVisibility(View.VISIBLE);
                                       P7.setVisibility(View.VISIBLE);
                                       P8.setVisibility(View.VISIBLE);
                                       P9.setVisibility(View.VISIBLE);
                                       P10.setVisibility(View.VISIBLE);
                                       SpinnerSalada(spinnerPez1, arrayAdapterSalada, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9, spinnerPez10);
                                       break;
                               }
                           }
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });

            }
        });

 siguiente.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         pez[0]= p1.getText().toString();
         pez[1]= p2.getText().toString();
         pez[2]= p3.getText().toString();
         pez[3]= p4.getText().toString();
         pez[4]= p5.getText().toString();
         pez[5]= p6.getText().toString();
         pez[6]= p7.getText().toString();
         pez[7]= p8.getText().toString();
         pez[8]= p9.getText().toString();
         pez[9]= p10.getText().toString();
         spinner[0]= spinnerPez1.getText().toString();
         spinner[1]= spinnerPez2.getText().toString();
         spinner[2]= spinnerPez3.getText().toString();
         spinner[3]= spinnerPez4.getText().toString();
         spinner[4]= spinnerPez5.getText().toString();
         spinner[5]= spinnerPez6.getText().toString();
         spinner[6]= spinnerPez7.getText().toString();
         spinner[7]= spinnerPez8.getText().toString();
         spinner[8]= spinnerPez9.getText().toString();
         spinner[9]= spinnerPez10.getText().toString();

         final String user_id = mAuth.getCurrentUser().getUid();
         int cant=Integer.parseInt(cantidadPeces.getText().toString());
         for (int i=0; i<cant; i++){
             if (spinner[i].equals("")){
                 Toast.makeText(PreguntasParte3.this, "Porfavor selecciona la especie de tu pez", Toast.LENGTH_LONG).show();
             }else {
                 Map<String, Object> datosPez= new HashMap<>();
                 datosPez.put("nombre", pez[i]);
                 datosPez.put("especie", spinner[i]);
                 mRef.child(user_id).child("InfoPeces").child(Integer.toString(i+1)).setValue(datosPez);
                 Toast.makeText(PreguntasParte3.this, "datos guardados" , Toast.LENGTH_SHORT).show();
                 Intent cambia= new Intent(PreguntasParte3.this, PreguntasParte2Activity.class);
                 startActivity(cambia);
                 finish();
             }
         }
     }
 });
 }
    private void inicio() {
        P1.setVisibility(View.INVISIBLE);
        P2.setVisibility(View.GONE);
        P3.setVisibility(View.GONE);
        P4.setVisibility(View.GONE);
        P5.setVisibility(View.GONE);
        P6.setVisibility(View.GONE);
        P7.setVisibility(View.GONE);
        P8.setVisibility(View.GONE);
        P9.setVisibility(View.GONE);
        P10.setVisibility(View.GONE);
    }

    private void SpinnerSalada(MaterialBetterSpinner spinnerPez1, ArrayAdapter<String> arrayAdapterSalada, MaterialBetterSpinner spinnerPez2, MaterialBetterSpinner spinnerPez3, MaterialBetterSpinner spinnerPez4, MaterialBetterSpinner spinnerPez5, MaterialBetterSpinner spinnerPez6, MaterialBetterSpinner spinnerPez7, MaterialBetterSpinner spinnerPez8, MaterialBetterSpinner spinnerPez9, MaterialBetterSpinner spinnerPez10) {
        spinnerPez1.setAdapter(arrayAdapterSalada);
        spinnerPez2.setAdapter(arrayAdapterSalada);
        spinnerPez3.setAdapter(arrayAdapterSalada);
        spinnerPez4.setAdapter(arrayAdapterSalada);
        spinnerPez5.setAdapter(arrayAdapterSalada);
        spinnerPez6.setAdapter(arrayAdapterSalada);
        spinnerPez7.setAdapter(arrayAdapterSalada);
        spinnerPez8.setAdapter(arrayAdapterSalada);
        spinnerPez9.setAdapter(arrayAdapterSalada);
        spinnerPez10.setAdapter(arrayAdapterSalada);
    }

    private void SpinnerDulce(MaterialBetterSpinner spinnerPez1, ArrayAdapter<String> arrayAdapterDulce, MaterialBetterSpinner spinnerPez2, MaterialBetterSpinner spinnerPez3, MaterialBetterSpinner spinnerPez4, MaterialBetterSpinner spinnerPez5, MaterialBetterSpinner spinnerPez6, MaterialBetterSpinner spinnerPez7, MaterialBetterSpinner spinnerPez8, MaterialBetterSpinner spinnerPez9, MaterialBetterSpinner spinnerPez10) {
        SpinnerSalada(spinnerPez1, arrayAdapterDulce, spinnerPez1, spinnerPez2, spinnerPez3, spinnerPez4, spinnerPez5, spinnerPez6, spinnerPez7, spinnerPez8, spinnerPez9);
        spinnerPez10.setAdapter(arrayAdapterDulce);
    }
}
