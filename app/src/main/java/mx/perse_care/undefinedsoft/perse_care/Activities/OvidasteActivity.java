package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.perse_care.undefinedsoft.perse_care.CrateAccountActivity;
import mx.perse_care.undefinedsoft.perse_care.LoginActivity;
import mx.perse_care.undefinedsoft.perse_care.R;

public class OvidasteActivity extends AppCompatActivity {

    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        regresar=(Button) findViewById(R.id.regresar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovidaste);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OvidasteActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
