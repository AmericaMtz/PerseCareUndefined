package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.Codigos;

public class CrateAccountActivity extends AppCompatActivity {

    Button siguiente;
    private EditText nombre, email, contrasenia, repcontra,
    codigo;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private DatabaseReference ref;
    ArrayList<Codigos> co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crate_account);
        mAuth= FirebaseAuth.getInstance();

        nombre=(EditText) findViewById(R.id.nusuario);
        email=(EditText) findViewById(R.id.email);
        contrasenia=(EditText) findViewById(R.id.password);
        repcontra=(EditText) findViewById(R.id.password2);
        codigo=(EditText) findViewById(R.id.codigo);
        radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
        co=new ArrayList<Codigos>();
        siguiente=(Button) findViewById(R.id.siguiente);



        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String contra= contrasenia.getText().toString();
                final String contra2= repcontra.getText().toString();
                final String name= nombre.getText().toString();
                final String correo= email.getText().toString();
                final String codig= codigo.getText().toString();


                ref=FirebaseDatabase.getInstance().getReference().child("Users");
                mRef=FirebaseDatabase.getInstance().getReference().child("CodigosProductos");

                if (name.isEmpty()){
                    Toast.makeText(CrateAccountActivity.this, "Escribe tu nombre", Toast.LENGTH_SHORT).show();
                    nombre.requestFocus();
                }else if (correo.isEmpty()){
                    Toast.makeText(CrateAccountActivity.this, "Escribe tu correo", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else if (contra.isEmpty()){
                    Toast.makeText(CrateAccountActivity.this, "Escribe tu contraseña", Toast.LENGTH_SHORT).show();
                    contrasenia.requestFocus();
                }else if (contra2.isEmpty()){
                    Toast.makeText(CrateAccountActivity.this, "Escribe tu contraseña", Toast.LENGTH_SHORT).show();
                    repcontra.requestFocus();
                }else if (codig.isEmpty()){
                    Toast.makeText(CrateAccountActivity.this, "Escribe el código", Toast.LENGTH_SHORT).show();
                    codigo.requestFocus();
                }else {
                            //validamos contraseña sea mayor a 6
                    if (contra.length() >= 6 || repcontra.length() >= 6) {
                                //que sean iguales
                                if (contra.equals(contra2)) {
                                    if (codig.length() == 10) {
                                           mAuth.createUserWithEmailAndPassword(correo, contra2).addOnCompleteListener(CrateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                               @Override
                                               public void onComplete(@NonNull Task<AuthResult> task) {
                                                   //subimos datos
                                                   final String user_id = mAuth.getCurrentUser().getUid();
                                                   Map<String,Object> datosPersona= new HashMap<>();
                                                   datosPersona.put("nombre",name);
                                                   datosPersona.put("email", correo);
                                                   datosPersona.put("sexo",radioButton.getText());
                                                   datosPersona.put("contra",contra);
                                                   datosPersona.put("codigo", codig);
                                                   datosPersona.put("TiempoLibre", "Viernes");
                                                   datosPersona.put("User", user_id);
                                                   ref.child(user_id).setValue(datosPersona);
                                                   Intent inte= new Intent(CrateAccountActivity.this, PreguntasParte1Activity.class);
                                                   Toast.makeText(CrateAccountActivity.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                                                   startActivity(inte);
                                                   finish();

                                               }
                                           });

                                    } else {
                                        Toast.makeText(CrateAccountActivity.this, "El código ingresado no existe", Toast.LENGTH_LONG).show();
                                        codigo.requestFocus();
                                    }

                                } else {
                                    Toast.makeText(CrateAccountActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_LONG).show();
                                    contrasenia.requestFocus();
                                }
                            } else {
                                Toast.makeText(CrateAccountActivity.this, "Las contraseñas deben tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                                contrasenia.requestFocus();
                            }
                }
            }
        });

    }
    public void onRadioButtonClicked(View view){

        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }
}
