package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.perse_care.undefinedsoft.perse_care.Activities.FAQs1;
import mx.perse_care.undefinedsoft.perse_care.Activities.LoginHelpDesk;
import mx.perse_care.undefinedsoft.perse_care.Activities.MainActivity;
import mx.perse_care.undefinedsoft.perse_care.Activities.NotificationsCreate;
import mx.perse_care.undefinedsoft.perse_care.Activities.OvidasteActivity;
import mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento.LoginMantenimiento;

public class LoginActivity extends AppCompatActivity {
    EditText correo, contrasenia;
    TextView  mensaje;
    Button registro, login;
    ImageView HelpDesk, mantenimiento, logo;
    TextInputLayout tilmEmail,tilmPassword;



    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth mAuth;


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logo=(ImageView) findViewById(R.id.logo);
        mantenimiento=(ImageView) findViewById(R.id.irLoginmantenimiento);

        registro=(Button) findViewById(R.id.registro);
        HelpDesk=(ImageView) findViewById(R.id.faqs);
        correo= (EditText)findViewById(R.id.email);
        contrasenia=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        mensaje=(TextView)findViewById(R.id.mensaje);
        tilmEmail = (TextInputLayout) findViewById(R.id.tilArea);
        tilmPassword = (TextInputLayout) findViewById(R.id.tilArea2);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "le puchaste", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(LoginActivity.this, NotificationsCreate.class);
                startActivity(intent);
            }
        });
        inicializa();
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, CrateAccountActivity.class);
                startActivity(intent2);
                finish();
            }
        });


        HelpDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginHelpDesk.class);
                startActivity(intent);
            }
        });
        mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, LoginMantenimiento.class);
                startActivity(intent);
            }
        });
    }
    public void IniciaSesion(View view){
        if (correo.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Es nesesario escribir tu correo", Toast.LENGTH_SHORT).show();
        }else
        if (contrasenia.getText().toString().equals("")){
            Toast.makeText(LoginActivity.this, "Escribe tu contrasenia", Toast.LENGTH_SHORT).show();
        }

        else if (correo.getText().toString().isEmpty() && contrasenia.getText().toString().isEmpty()){
            Toast.makeText(LoginActivity.this, "Es nesesario llenar los campos", Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasenia.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle datos= new Bundle();
                        datos.putString("nombre", correo.getText().toString().trim());
                        intent.putExtras(datos);
                        startActivity(intent);
                        finish();
                    } else {

                        mensaje.setText("Usuario o contraseña incorrecta");
                    }
                }
            });
        }
    }

    private void inicializa() {

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w("LoginActivity", "onAuthStateChanged - signed_in " + firebaseUser.getUid());
                    Log.w("LoginActivity", "onAuthStateChanged - signed_in " + firebaseUser.getEmail());
                }else
                    Log.w("LoginActivity", "onAuthStateChanged - signed_out");
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }


}
