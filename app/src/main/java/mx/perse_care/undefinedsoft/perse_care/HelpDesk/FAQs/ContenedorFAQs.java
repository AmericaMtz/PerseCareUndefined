package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import mx.perse_care.undefinedsoft.perse_care.Activities.LoginHelpDesk;
import mx.perse_care.undefinedsoft.perse_care.R;

public class  ContenedorFAQs extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_faqs);

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()== R.id.lista){


                }else
                    if (item.getItemId()== R.id.consulta){
                    MisFAQsFragment misFAQsFragment = new MisFAQsFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, misFAQsFragment)
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .addToBackStack(null).commit();

                    }else
                        if (item.getItemId()== R.id.add){
                    AltasFaqs altasFaqss= new AltasFaqs();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, altasFaqss)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();

                        }else
                            if (item.getItemId()== R.id.salir){
                            cierraSesion();
                            }
                return false;
            }

            private void cierraSesion() {
                Toast.makeText(ContenedorFAQs.this , "Sesión cerrada", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(ContenedorFAQs.this, LoginHelpDesk.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
