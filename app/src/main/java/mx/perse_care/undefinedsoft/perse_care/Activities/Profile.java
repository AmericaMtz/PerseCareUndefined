package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mx.perse_care.undefinedsoft.perse_care.Model.Pecera;
import mx.perse_care.undefinedsoft.perse_care.Model.Personas;
import mx.perse_care.undefinedsoft.perse_care.Model.Peces;
import mx.perse_care.undefinedsoft.perse_care.R;

public class Profile extends AppCompatActivity {
    TextView nombre, sexo, codigo, agua, cantPeces, litrosPecera, correo;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private String correoRecibido;
    private FloatingActionButton boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nombre=(TextView) findViewById(R.id.profile_txtnombre);
        sexo=(TextView) findViewById(R.id.profile_txtSexo);
        codigo=(TextView) findViewById(R.id.profile_txtCodigo);
        agua=(TextView) findViewById(R.id.profile_txtTipoAgua);
        cantPeces=(TextView) findViewById(R.id.profile_txtCantidadPeces);
        litrosPecera=(TextView) findViewById(R.id.profile_txtLitrosPecera);
        correo=(TextView) findViewById(R.id.profile_txtcorreo);
        boton= (FloatingActionButton) findViewById(R.id.profile_buttonEdit);
        mAuth= FirebaseAuth.getInstance();

        mRef=FirebaseDatabase.getInstance().getReference().child("Users");

        Bundle recibe= new Bundle();
        recibe= this.getIntent().getExtras();
        correoRecibido= recibe.getString("correo");
        colocaDatos();
        colocaDatos2();
        colocaDatos3();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Profile.this, EditProfile.class);
                Bundle datos= new Bundle();
                datos.putString("correo", correoRecibido);
                intent.putExtras(datos);
                startActivity(intent);

            }
        });
    }

    private void colocaDatos3() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mRef.child(user_id).child("InfoPecera").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                        Pecera pecera= dataSnapshot.getValue(Pecera.class);
                        String capacidad= pecera.getCapacidadPecera();
                        litrosPecera.setText(capacidad+ " litros");

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //para el agua
    private void colocaDatos2() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mRef.child(user_id).child("InformacionPreguntas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                        Peces pec=datasnapshot.getValue(Peces.class);
                            String tipoAgua= pec.getTipoAguaDePeces();
                            if (tipoAgua.equals("De agua dulce.")){
                                agua.setText("Dulce");
                            }else {
                                agua.setText("Salada");
                            }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//para los datos de la persona
    private void colocaDatos() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                        Personas personas= datasnapshot.getValue(Personas.class);
                        String correoFirebase= personas.getEmail();
                        if (correoFirebase.equals(correoRecibido)){
                            nombre.setText(personas.getNombre());
                            sexo.setText(personas.getSexo());
                            codigo.setText(personas.getCodigo());
                            cantPeces.setText(personas.getCantidadDePeces()+ " peces");
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
