package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.zip.Inflater;

import mx.perse_care.undefinedsoft.perse_care.R;

public class AjustesActivity extends Fragment {
    LinearLayout perfil, notificaciones;

    public AjustesActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.activity_ajustes, container, false);
        perfil=(LinearLayout) view.findViewById(R.id.miPerfil);
        notificaciones=(LinearLayout)view.findViewById(R.id.notificaciones);

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), Profile.class);
                startActivity(intent);
            }
        });
        notificaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), ConfiguracionNotificaciones.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
