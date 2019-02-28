package mx.perse_care.undefinedsoft.perse_care;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mx.perse_care.undefinedsoft.perse_care.Activities.OvidasteActivity;

public class LoginActivity extends AppCompatActivity {

    TextView click;
    Button registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        click= (TextView) findViewById(R.id.clickAqui);
        registro=(Button) findViewById(R.id.registro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CrateAccountActivity.class);
                startActivity(intent);
            }
        });

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OvidasteActivity.class);
                startActivity(intent);
            }
        });
    }
}
