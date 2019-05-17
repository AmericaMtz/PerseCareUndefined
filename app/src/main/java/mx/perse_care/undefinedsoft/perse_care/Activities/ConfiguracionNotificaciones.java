package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mx.perse_care.undefinedsoft.perse_care.R;

public class ConfiguracionNotificaciones extends AppCompatActivity {

    FloatingActionButton boton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_notificaciones);
        radioGroup=(RadioGroup) findViewById(R.id.Rradiogroup);
        boton=(FloatingActionButton) findViewById(R.id.Rguarda);
        mAuth= FirebaseAuth.getInstance();
        ref=FirebaseDatabase.getInstance().getReference().child("Users");
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_id = mAuth.getCurrentUser().getUid();
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                ref.child(user_id).child("TiempoLibre").setValue(radioButton.getText());
                Snackbar.make(v, "Datos modificados", Snackbar.LENGTH_LONG).setAction("Action", null).show();

            }
        });
    }
    public void onRadioButtonClicked(View view){

        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

    }
}
