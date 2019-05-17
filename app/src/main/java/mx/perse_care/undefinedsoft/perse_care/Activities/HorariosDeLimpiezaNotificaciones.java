package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mx.perse_care.undefinedsoft.perse_care.Model.Personas;
import mx.perse_care.undefinedsoft.perse_care.R;

import static mx.perse_care.undefinedsoft.perse_care.R.drawable.mayov;

public class HorariosDeLimpiezaNotificaciones extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private ImageView mayo, junio, julio, agosto, septiembre, octubre, noviembre,
    diciembre;
    private  String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_de_limpieza_notificaciones);

        mAuth= FirebaseAuth.getInstance();
        mRef=FirebaseDatabase.getInstance().getReference().child("Users");
        user_id = mAuth.getCurrentUser().getUid();
        mayo=(ImageView) findViewById(R.id.mayo);
        junio=(ImageView) findViewById(R.id.junio);
        julio=(ImageView) findViewById(R.id.julio);
        agosto=(ImageView) findViewById(R.id.agosto);
        septiembre=(ImageView) findViewById(R.id.septiembre);
        octubre=(ImageView) findViewById(R.id.octubre);
        noviembre=(ImageView) findViewById(R.id.noviembre);
        diciembre=(ImageView) findViewById(R.id.diciembre);
        buscaDiaLibre();
        colocaImagenes();

    }

    private void buscaDiaLibre() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Personas personas= dataSnapshot1.getValue(Personas.class);
                        String usuario= personas.getUser();
                        if (user_id.equals(usuario)){
                            String DiaLibre= personas.getTiempoLibre();
                            if(DiaLibre.equals("Viernes")){
                                mayo.setImageResource(R.drawable.mayov);
                                junio.setImageResource(R.drawable.juniov);
                                julio.setImageResource(R.drawable.juliov);
                                agosto.setImageResource(R.drawable.agostov);
                                septiembre.setImageResource(R.drawable.septiembre);
                                octubre.setImageResource(R.drawable.octubrev);
                                noviembre.setImageResource(R.drawable.noviembrev);
                                diciembre.setImageResource(R.drawable.diciembrev);
                            }
                            else if (DiaLibre.equals("Domingo")){
                                mayo.setImageResource(R.drawable.mayod);
                                junio.setImageResource(R.drawable.juniod);
                                julio.setImageResource(R.drawable.juliod);
                                agosto.setImageResource(R.drawable.agostod);
                                septiembre.setImageResource(R.drawable.septiembred);
                                octubre.setImageResource(R.drawable.octubred);
                                noviembre.setImageResource(R.drawable.noviembred);
                                diciembre.setImageResource(R.drawable.diciembred);
                            }
                            else if (DiaLibre.equals("Lunes")){
                                mayo.setImageResource(R.drawable.mayol);
                                junio.setImageResource(R.drawable.juniol);
                                julio.setImageResource(R.drawable.juliol);
                                agosto.setImageResource(R.drawable.agostol);
                                septiembre.setImageResource(R.drawable.septiembrel);
                                octubre.setImageResource(R.drawable.octubrel);
                                noviembre.setImageResource(R.drawable.noviembrel);
                                diciembre.setImageResource(R.drawable.diciembrel);
                            }
                            else if (DiaLibre.equals("Martes")){
                                mayo.setImageResource(R.drawable.mayoma);
                                junio.setImageResource(R.drawable.junioma);
                                julio.setImageResource(R.drawable.julioma);
                                agosto.setImageResource(R.drawable.agostoma);
                                septiembre.setImageResource(R.drawable.septiembrema);
                                octubre.setImageResource(R.drawable.octubrema);
                                noviembre.setImageResource(R.drawable.noviembrema);
                                diciembre.setImageResource(R.drawable.diciembrema);
                            }
                            else if (DiaLibre.equals("Miercoles")){
                                mayo.setImageResource(R.drawable.mayomi);
                                junio.setImageResource(R.drawable.juniomi);
                                julio.setImageResource(R.drawable.juliomi);
                                agosto.setImageResource(R.drawable.agostomi);
                                septiembre.setImageResource(R.drawable.septiembremi);
                                octubre.setImageResource(R.drawable.octubremi);
                                noviembre.setImageResource(R.drawable.noviembremi);
                                diciembre.setImageResource(R.drawable.diciembremi);
                            }
                            else if (DiaLibre.equals("Jueves")){
                                mayo.setImageResource(R.drawable.mayoj);
                                junio.setImageResource(R.drawable.junioj);
                                julio.setImageResource(R.drawable.julioj);
                                agosto.setImageResource(R.drawable.agostoj);
                                septiembre.setImageResource(R.drawable.septiembrej);
                                octubre.setImageResource(R.drawable.octubrej);
                                noviembre.setImageResource(R.drawable.noviembrej);
                                diciembre.setImageResource(R.drawable.diciembrej);
                            }
                            else if (DiaLibre.equals("Sabado")){
                                mayo.setImageResource(R.drawable.mayos);
                                junio.setImageResource(R.drawable.junios);
                                julio.setImageResource(R.drawable.julios);
                                agosto.setImageResource(R.drawable.agostos);
                                septiembre.setImageResource(R.drawable.septiembres);
                                octubre.setImageResource(R.drawable.octubres);
                                noviembre.setImageResource(R.drawable.noviembres);
                                diciembre.setImageResource(R.drawable.diciembres);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void colocaImagenes() {
    }
}
