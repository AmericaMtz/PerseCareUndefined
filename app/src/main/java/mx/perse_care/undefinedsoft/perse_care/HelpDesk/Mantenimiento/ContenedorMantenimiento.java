package mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import mx.perse_care.undefinedsoft.perse_care.Activities.LoginHelpDesk;
import mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs.AltasFaqs;
import mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs.ContenedorFAQs;
import mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs.MisFAQsFragment;
import mx.perse_care.undefinedsoft.perse_care.R;

public class ContenedorMantenimiento extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_mantenimiento);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()== R.id.listas){


                }else
                if (item.getItemId()== R.id.consultass){
                    MantenimientoFragment mantenimientoFragment = new MantenimientoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, mantenimientoFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();

                }else

                if (item.getItemId()== R.id.salirs){
                    cierraSesion();
                }else if (item.getItemId()==R.id.add){
                    AltasMantenimiento altasMantenimiento= new AltasMantenimiento();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, altasMantenimiento)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                }
                return false;
            }

            private void cierraSesion() {
                Toast.makeText(ContenedorMantenimiento.this , "Cerraste tu sesión", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ContenedorMantenimiento.this, LoginMantenimiento.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }

