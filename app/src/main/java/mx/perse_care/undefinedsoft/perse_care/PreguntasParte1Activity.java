package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PreguntasParte1Activity extends AppCompatActivity {
    Button siguiente;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas_parte1);

        radioGroup=(RadioGroup)findViewById(R.id.tipoDeAgua);
        siguiente= (Button) findViewById(R.id.siguiente);
        mAuth=FirebaseAuth.getInstance();

        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if (user== null){
                    Intent intent= new Intent(PreguntasParte1Activity.this, PreguntasParte1Activity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };



        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                final DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
                final String user_id = mAuth.getCurrentUser().getUid();
                Map<String, Object> datosPreguntas = new HashMap<>();
                datosPreguntas.put("TipoAguaDePeces", radioButton.getText());
                mRootReference.child("Users").child(user_id).child("InformacionPreguntas").child("agua").setValue(datosPreguntas);
                Intent intent = new Intent(PreguntasParte1Activity.this, PreguntasParte3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
