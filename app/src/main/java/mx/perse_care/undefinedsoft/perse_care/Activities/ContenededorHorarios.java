package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import mx.perse_care.undefinedsoft.perse_care.R;

public class ContenededorHorarios extends Fragment {
    private BottomNavigationView bottomNavigationView;
    LinearLayout texto;

    public ContenededorHorarios() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                             Bundle savedInstanceState) {


        final View view= inflater.inflate(R.layout.activity_horarios_de_limpieza, container, false);
        bottomNavigationView=(BottomNavigationView)view.findViewById(R.id.bottombar1);
        texto= (LinearLayout) view.findViewById(R.id.textoHorarios);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()== R.id.limpieza){
                    texto.setVisibility(View.INVISIBLE);
                    HorariosLimpieza2 horariosLimpieza2= new HorariosLimpieza2();
                    getChildFragmentManager().beginTransaction().setCustomAnimations( R.anim.anim_slide_in_from_left,R.anim.anim_slide_from_out_left)
                            .replace(R.id.contenedor3, horariosLimpieza2)
                            .addToBackStack(null).commit();

                }else if (item.getItemId()==R.id.comida){

                    texto.setVisibility(View.INVISIBLE);
                    HorariosDeComida horariosDeComida= new HorariosDeComida();
                    getChildFragmentManager().beginTransaction().setCustomAnimations( R.anim.anim_slide_in_from_left,R.anim.anim_slide_from_out_left)
                            .replace(R.id.contenedor3, horariosDeComida)
                            .addToBackStack(null).commit();

                }
                return false;
            }
        });
        return view;
    }
}
