package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.CacheRequest;

import mx.perse_care.undefinedsoft.perse_care.Activities.codigoActivity;

public class CrateAccountActivity extends AppCompatActivity {

    Button siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crate_account);

        siguiente=(Button) findViewById(R.id.siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrateAccountActivity.this, codigoActivity.class);
                startActivity(intent);
            }
        });
    }
}
