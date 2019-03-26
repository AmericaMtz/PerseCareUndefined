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

import mx.perse_care.undefinedsoft.perse_care.Activities.FAQsActivity;
import mx.perse_care.undefinedsoft.perse_care.Activities.MainActivity;
import mx.perse_care.undefinedsoft.perse_care.Activities.OvidasteActivity;
import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;

public class LoginActivity extends AppCompatActivity {
    EditText correo, contrasenia;
    TextView click, mensaje;
    Button registro, login;
    ImageView faqs;
    TextInputLayout tilmEmail,tilmPassword;



    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        click= (TextView) findViewById(R.id.clickAqui);
        registro=(Button) findViewById(R.id.registro);
        faqs=(ImageView) findViewById(R.id.faqs);
        correo= (EditText)findViewById(R.id.email);
        contrasenia=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        mensaje=(TextView)findViewById(R.id.mensaje);

        tilmEmail = (TextInputLayout) findViewById(R.id.tilArea);
        tilmPassword = (TextInputLayout) findViewById(R.id.tilArea2);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        inicializa();
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CrateAccountActivity.class);
                startActivity(intent);
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OvidasteActivity.class);
                startActivity(intent);
            }
        });
    }
    public void IniciaSesion(View view){
        firebaseAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasenia.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                    Toast.makeText(LoginActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                    mensaje.setText("Error");
                }
            }
        });
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

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }

}
