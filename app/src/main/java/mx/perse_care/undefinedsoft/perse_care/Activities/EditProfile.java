package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import mx.perse_care.undefinedsoft.perse_care.Model.Pecera;
import mx.perse_care.undefinedsoft.perse_care.Model.Personas;
import mx.perse_care.undefinedsoft.perse_care.R;

public class EditProfile extends AppCompatActivity {
    private EditText editNombre;
    private EditText edtitLitros;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth mAuth;
    private FloatingActionButton botton;
    private EditText porcentajeAdornos;
    private String correoRecibido;
    private DatabaseReference mRef;
    private RadioButton hombre, mujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editNombre=(EditText) findViewById(R.id.Editprofile_txtNombre);
        edtitLitros=(EditText) findViewById(R.id.Editprofile_txtLitrosPecera);
        radioGroup=(RadioGroup) findViewById(R.id.Editprofile_radiogroup);
        botton=(FloatingActionButton) findViewById(R.id.Editprofile_buttonEdit);
        porcentajeAdornos=(EditText) findViewById(R.id.Editprofile_porcentajeAdornos);
        hombre=(RadioButton) findViewById(R.id.Editprofile_hombre);
        mujer=(RadioButton) findViewById(R.id.Editprofile_mujer);
        mRef=FirebaseDatabase.getInstance().getReference().child("Users");
        //recibimos lo que mandamos en el activity anterior
        mAuth= FirebaseAuth.getInstance();
        Bundle recibe= new Bundle();
        recibe= this.getIntent().getExtras();
        correoRecibido= recibe.getString("correo");
        
        //Llenamos los editText con la informacion
        LlenaInfoPersona();
        LlenaInfoPersona2();
        //accion para el boton
        botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name= editNombre.getText().toString();
                String sex;
                final String ador= porcentajeAdornos.getText().toString();
                final String litro= edtitLitros.getText().toString();
                final String user_id = mAuth.getCurrentUser().getUid();

                if (hombre.isChecked()){
                    sex="Hombre";
                }else {
                    sex= "Mujer";
                }
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                mRef.child(user_id).child("nombre").setValue(name);
                mRef.child(user_id).child("sexo").setValue(sex);
                mRef.child(user_id).child("InfoPecera").child("capacidadPecera").setValue(litro);
                mRef.child(user_id).child("InfoPecera").child("porcentajePecera").setValue(ador);
                Snackbar.make(v, "Datos modificados", Snackbar.LENGTH_LONG).setAction("Action", null).show();


            }
        });
    }

    private void LlenaInfoPersona2() {
        final String user_id = mAuth.getCurrentUser().getUid();
        mRef.child(user_id).child("InfoPecera").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                        Pecera pecera= dataSnapshot.getValue(Pecera.class);
                        String capacidad= pecera.getCapacidadPecera();
                        String porcentaje= pecera.getPorcentajePecera();
                        edtitLitros.setText(capacidad);
                        porcentajeAdornos.setText(porcentaje);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void LlenaInfoPersona() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                        Personas personas= datasnapshot.getValue(Personas.class);
                        String correoFirebase= personas.getEmail();
                        if (correoFirebase.equals(correoRecibido)){
                            editNombre.setText(personas.getNombre());
                            if (personas.getSexo().equals("Hombre")){
                                hombre.isChecked();
                            }else {
                                mujer.isChecked();
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

    public void onRadioButtonClicked(View view) {

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }
}
