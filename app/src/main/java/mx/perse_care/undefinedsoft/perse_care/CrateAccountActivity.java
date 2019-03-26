package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrateAccountActivity extends AppCompatActivity {

    Button siguiente;
    private EditText nombre, email, contrasenia, repcontra,
    codigo;
    private RadioButton hombre, mujer;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crate_account);
        mAuth= FirebaseAuth.getInstance();

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user == null){
                    Intent intent = new Intent(CrateAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };


        nombre=(EditText) findViewById(R.id.nusuario);
        email=(EditText) findViewById(R.id.email);
        contrasenia=(EditText) findViewById(R.id.password);
        repcontra=(EditText) findViewById(R.id.password2);
        codigo=(EditText) findViewById(R.id.codigo);
        hombre=(RadioButton) findViewById(R.id.hombre);
        mujer=(RadioButton) findViewById(R.id.mujer);


        siguiente=(Button) findViewById(R.id.siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String contra= contrasenia.getText().toString();
                final String contra2= repcontra.getText().toString();
                final String name= nombre.getText().toString();
                final String correo= email.getText().toString();
                final String codig= codigo.getText().toString();


            mAuth.createUserWithEmailAndPassword(correo,contra2).addOnCompleteListener(CrateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                        //validamos contraseña sea mayor a 6
                        if (contra.length() >= 6 || repcontra.length() >= 6) {
                            //que sean iguales
                            if (contra.equals(contra2)) {
                                if (codig.length() == 10) {
                                    //creamoos el id del usuario
                                    String user_id = mAuth.getCurrentUser().getUid();
                                    //guardamos el correo en esta "dirrecion"
                                    DatabaseReference current_correo_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("email");
                                    current_correo_db.setValue(correo);
                                    //Guardamos lo de mas
                                    DatabaseReference current_contrasenia_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("contraseña");
                                    current_contrasenia_db.setValue(contra);
                                    DatabaseReference current_name_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("nombre");
                                    current_name_db.setValue(name);
                                    Intent intent = new Intent(CrateAccountActivity.this, PreguntasParte1Activity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(CrateAccountActivity.this, "Código mal", Toast.LENGTH_LONG).show();
                                    codigo.requestFocus();
                                }

                            } else {
                                Toast.makeText(CrateAccountActivity.this, "Las contraseñas deben ser iguales", Toast.LENGTH_LONG).show();
                                contrasenia.requestFocus();
                                repcontra.requestFocus();
                            }
                        } else {
                            Toast.makeText(CrateAccountActivity.this, "Las contraseñas deben tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                            contrasenia.requestFocus();
                            repcontra.requestFocus();
                        }
                    }
                 });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);

    }
}
