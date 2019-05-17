package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

import mx.perse_care.undefinedsoft.perse_care.R;

public class HorariosDeComida extends Fragment {
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

    public HorariosDeComida() {
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        mAuth= FirebaseAuth.getInstance();
        View view= inflater.inflate(R.layout.activity_horarios_de_comida, container, false);
        imagen=(ImageView) view.findViewById(R.id.horariosComidaImagen);
        int resource= imagenesID[rgenerador.nextInt(imagenesID.length)];
        imagen.setImageResource(resource);
        return view;
    }
}
