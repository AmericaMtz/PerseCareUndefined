package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class MuestraFAQs extends AppCompatActivity {
    ListView muestraTodo;
    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_faqs);

        muestraTodo=(ListView)findViewById(R.id.listView);

        bbdd = FirebaseDatabase.getInstance().getReference(getString(R.string.FAQss));
        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado= new ArrayList<String>();

                for (DataSnapshot datasnapshot: dataSnapshot.getChildren()){

                    FAQs faQsss=  datasnapshot.getValue(FAQs.class);

                    String preguntax= faQsss.getPregunta();
                    String respuestax= faQsss.getRespuesta();
                    listado.add(preguntax);
                    listado.add(respuestax);
                }
                adaptador= new ArrayAdapter<String>(MuestraFAQs.this, android.R.layout.simple_list_item_1, listado);
                muestraTodo.setAdapter(adaptador);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
