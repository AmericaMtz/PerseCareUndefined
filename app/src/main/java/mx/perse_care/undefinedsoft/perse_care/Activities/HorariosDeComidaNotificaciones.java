package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import mx.perse_care.undefinedsoft.perse_care.R;

public class HorariosDeComidaNotificaciones extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private ImageView imagen;
    private static final Random rgenerador= new Random();
    private static final Integer[] imagenesID={
            R.drawable.horariocomida1,
            R.drawable.horariocomida2,
            R.drawable.horariocomida3,
            R.drawable.horariocomida4,
            R.drawable.horariocomida5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_horarios_de_comida_notificaciones);
        mRef=FirebaseDatabase.getInstance().getReference().child("Users");
        String user_id = mAuth.getCurrentUser().getUid();
        imagen=(ImageView) findViewById(R.id.horariosComidaNotificacionesImagen);
        int resource= imagenesID[rgenerador.nextInt(imagenesID.length)];
        imagen.setImageResource(resource);

    }
}
