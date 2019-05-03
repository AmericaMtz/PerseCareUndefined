package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs.ContenedorFAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class LoginHelpDesk extends AppCompatActivity {
    Button iniciaSesion;
    EditText usuario, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_help_desk);

        iniciaSesion= (Button) findViewById(R.id.loginHelp);
        usuario= (EditText)findViewById(R.id.usuario);
        contrasenia=(EditText) findViewById(R.id.password);

        iniciaSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(usuario.getText().toString().equals("Editor") && contrasenia.getText().toString().equals("1234")){
                 //   Toast.makeText(LoginHelpDesk.this, "Bienvenido Editor", Toast.LENGTH_LONG).show();
                   // Intent intent = new Intent(LoginHelpDesk.this, ContenedorFAQs.class);
//                    startActivity(intent);
  //                  finish();

    //            }else
      //              if (usuario.getText().toString().equals("Operador") && contrasenia.getText().toString().equals("1234")){
        //                    Toast.makeText(LoginHelpDesk.this, "Este es para operador/ Reporte de eventos", Toast.LENGTH_LONG).show();
          //              }else
            //            if (usuario.getText().toString().equals("IngenieroSoporte") && contrasenia.getText().toString().equals("1234")){
              //              Toast.makeText(LoginHelpDesk.this, "Este es para Ingeniero de soporte/ Reporte de eventos", Toast.LENGTH_LONG).show();
                //        }else
                  //      if (usuario.getText().toString().equals("GerenteSoporte") && contrasenia.getText().toString().equals("1234")){
                    //        Toast.makeText(LoginHelpDesk.this, "Este es para Gerente de soporte/ Reporte de eventos", Toast.LENGTH_LONG).show();
                      //  }else
//                        if (usuario.getText().toString().equals("Programador") && contrasenia.getText().toString().equals("1234")){
  //                          Toast.makeText(LoginHelpDesk.this, "Este es para el programador/ Reporte de mantenimiento", Toast.LENGTH_LONG).show();
    //                    }else
      //                  if (usuario.getText().toString().equals("GerenteMantenimiento") && contrasenia.getText().toString().equals("1234")){
        //                    Toast.makeText(LoginHelpDesk.this, "Este es para Gerente de mantenimiento/ Reporte de mantenimiento", Toast.LENGTH_LONG).show();
          //              }else
            //            if (usuario.getText().toString().equals("Cliente") && contrasenia.getText().toString().equals("1234")){
              //              Toast.makeText(LoginHelpDesk.this, "Este es para El cliente/ Reporte de eventos y mantenimiento", Toast.LENGTH_LONG).show();
                //        }else {
                  //          Toast.makeText(LoginHelpDesk.this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    //    }
                Intent intent = new Intent(LoginHelpDesk.this, ContenedorFAQs.class);
                startActivity(intent);
                finish();
                }

        });
    }
}
