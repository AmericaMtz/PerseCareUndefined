package mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.perse_care.undefinedsoft.perse_care.R;

public class LoginMantenimiento extends AppCompatActivity {

    Button entra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mantenimiento);
        entra=(Button) findViewById(R.id.loginMantenimiento);

        entra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginMantenimiento.this, ContenedorMantenimiento.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
