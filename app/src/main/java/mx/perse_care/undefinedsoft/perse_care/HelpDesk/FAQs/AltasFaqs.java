package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class AltasFaqs extends Fragment {

    private FloatingActionButton enviar;
    EditText pregunt, respuesta;
    private DatabaseReference databaseReference;
    DatabaseReference bbdd;
    int maxim=0;
    FAQs faQs;

    public AltasFaqs() {
        //requiere un constructor vacio
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.activity_altas_faqs, container, false);
       pregunt=(EditText)v.findViewById(R.id.txtPregunta);
       respuesta=(EditText)v.findViewById(R.id.txtRespuesta);
       enviar=(FloatingActionButton)v.findViewById(R.id.enviarPregunta);
        faQs= new FAQs();
       bbdd=FirebaseDatabase.getInstance().getReference().child("FAQs");
       bbdd.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()){
                   maxim= (int) dataSnapshot.getChildrenCount()+1;
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       final DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();

       enviar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Map<String,Object> datosPreguntas= new HashMap<>();
               datosPreguntas.put("pregunta", pregunt.getText().toString() );
               datosPreguntas.put("respuesta", respuesta.getText().toString());
               datosPreguntas.put("position", maxim);
               mRootReference.child("FAQs").child(Integer.toString(maxim)).setValue(datosPreguntas);
               Toast.makeText(v.getContext(), "Pregunta dada de alta correctamente", Toast.LENGTH_SHORT).show();
               pregunt.setText("");
               respuesta.setText("");

           }
       });

       return v;
    }
}
