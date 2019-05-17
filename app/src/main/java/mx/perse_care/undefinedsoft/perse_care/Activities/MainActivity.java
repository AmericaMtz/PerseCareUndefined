package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs.MisFaqsFragmentUsuario;
import mx.perse_care.undefinedsoft.perse_care.Model.Personas;
import mx.perse_care.undefinedsoft.perse_care.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private TextView name, correo;
    private DatabaseReference mRef;
    private ArrayList<Personas> personitas;
    private String user_id;
    private String corrreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

        mRef=FirebaseDatabase.getInstance().getReference().child("Users");
        Bundle recibe= new Bundle();
        recibe= this.getIntent().getExtras();
        corrreo= recibe.getString("nombre");
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
        traePersona();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
     misPEces();

    }

    private void traePersona(){
        mRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot datasnapshot : dataSnapshot.getChildren()){
                        final Personas personas= datasnapshot.getValue(Personas.class);
                        String correoFirebase= personas.getEmail();
                        if(correoFirebase.equals(corrreo)) {
                            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

                            View headView= navigationView.getHeaderView(0);
                            name=(TextView) headView.findViewById(R.id.nav_Nombre);
                            correo=(TextView) headView.findViewById(R.id.nav_correo);
                            ImageView imgProfile= headView.findViewById(R.id.nav_imageView);
                            //imgProfile.setBackground(getDrawable(R.drawable.basura));
                            imgProfile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intente= new Intent(MainActivity.this, Profile.class);
                                    Bundle datos= new Bundle();
                                    datos.putString("correo", corrreo);
                                    intente.putExtras(datos);
                                    startActivity(intente);
                                }
                            });
                            name.setText(personas.getNombre());
                            correo.setText(personas.getEmail());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al encontrar a la persona", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.termometro) {
            // Handle the camera action
        } else if (id == R.id.horarios) {
            ContenededorHorarios horariosDeLimpieza= new ContenededorHorarios();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_from_out_left)
                    .replace(R.id.contenedor2, horariosDeLimpieza)
                    .addToBackStack(null).commit();

        } else if (id == R.id.misPeces) {
           misPEces();

        }  else if (id == R.id.ajustes) {
            AjustesActivity ajustesActivity= new AjustesActivity();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_from_out_left)
                    .replace(R.id.contenedor2, ajustesActivity)
                    .addToBackStack(null).commit();

        } else if (id == R.id.FAQs) {
            MisFaqsFragmentUsuario misFAQsFragment = new MisFaqsFragmentUsuario();
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_from_out_left)
                    .replace(R.id.contenedor2, misFAQsFragment)
                    .addToBackStack(null).commit();
    }else if(id== R.id.cerrarSesion){
        cerrarSesion();
        }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void misPEces() {
        MisPecesFragment misPecesFragment= new MisPecesFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_from_out_left)
                .replace(R.id.contenedor2, misPecesFragment)
                .addToBackStack(null).commit();
    }

    private void cerrarSesion() {
        Intent intent = new Intent(MainActivity.this, LoginHelpDesk.class);
        Toast.makeText(this, "Saliste de tu cuenta", Toast.LENGTH_SHORT).show();
        startActivity(intent);
        finish();
    }
}
