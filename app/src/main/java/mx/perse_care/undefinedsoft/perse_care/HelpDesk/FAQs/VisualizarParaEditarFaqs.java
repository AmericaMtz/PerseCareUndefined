package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import mx.perse_care.undefinedsoft.perse_care.R;

public class VisualizarParaEditarFaqs extends AppCompatActivity {
    private static final String TAG = "VisualizarParaEditarFaq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_para_editar_faqs);
        Log.d(TAG,"onCreate: started.");
        getIncomingIntent();
    }
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("pregunta")&& getIntent().hasExtra("respuesta")){
            Log.d(TAG, "getIncomingIntent: found intent estras.");

            String pregunta= getIntent().getStringExtra("pregunta");
            String respuesta= getIntent().getStringExtra("respuesta");
            setPregunta(pregunta, respuesta);

        }
    }
    private void setPregunta(String pregunta, String respuesta){
        Log.d(TAG, "setPRegunta: setting the pregunta and name widgets");

        TextView name= findViewById(R.id.editPregunta);
        name.setText(pregunta);

        TextView respuestaa= findViewById(R.id.editPregunta);
        respuestaa.setText(respuesta);

       // ImageView image= findViewById(R.id.idname);
        //Glide.whith(this).asBitmap()  <--agregar GLide
         //       .load(elparametroqueestamospasando)
           //     .into(image)
    }
}
