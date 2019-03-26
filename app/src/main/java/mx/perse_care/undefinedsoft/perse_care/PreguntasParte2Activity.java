package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PreguntasParte2Activity extends AppCompatActivity {
    Button finish;
    private EditText capacidad, porcentaje;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_parte2);

        radioGroup=(RadioGroup) findViewById(R.id.radiogroup);
        finish=(Button) findViewById(R.id.Terminar);
        porcentaje=(EditText) findViewById(R.id.porcentaje);
        capacidad=(EditText) findViewById(R.id.capacidad);
        mAuth= FirebaseAuth.getInstance();

        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if (user== null){
                    Intent intent= new Intent(PreguntasParte2Activity.this, PreguntasParte2Activity.class);
                    startActivity(intent);
                    finish();
                    return;

                }
            }
        };


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                if (porcentaje.getText().toString().length()>=1) {
                    if (capacidad.getText().toString().length()>=1) {

                     //   final DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
                        //final String user_id = mAuth.getCurrentUser().getUid();
                        //Map<String, Object> datosPreguntas = new HashMap<>();
                     //   datosPreguntas.put("capacidadPecera", capacidad.getText().toString() );
                     //   datosPreguntas.put("porcentajePecera", porcentaje.getText().toString() );
                     //   datosPreguntas.put("cuentaConAdornos", radioButton.getText());
                     //   mRootReference.child("Users").child(user_id).child("InformacionPreguntas").child("pecera").setValue(datosPreguntas);
                        Toast.makeText(PreguntasParte2Activity.this, "Gracias por registrarte", Toast.LENGTH_LONG).show();
                        Intent inten = new Intent(PreguntasParte2Activity.this, LoginActivity.class);
                        startActivity(inten);
                        finish();
                    }else{
                        Toast.makeText(PreguntasParte2Activity.this, "Por favor escribe la capacidad de la pecera", Toast.LENGTH_LONG);
                        capacidad.requestFocus();
                    }
                }else {
                    Toast.makeText(PreguntasParte2Activity.this, "Por favor escribe el porcentaje", Toast.LENGTH_LONG);
                    porcentaje.requestFocus();
                }
            }
        });

    }
}
