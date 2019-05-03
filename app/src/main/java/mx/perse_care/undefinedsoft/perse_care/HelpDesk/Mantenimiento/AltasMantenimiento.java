package mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.Mantenimiento;
import mx.perse_care.undefinedsoft.perse_care.R;

public class AltasMantenimiento extends Fragment {

    private Button guardar;
    private EditText problema, encargado, fecha;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;
    Mantenimiento mantenimientos;
    int maxim = 0;

    public AltasMantenimiento() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_altas_mantenimiento, container, false);
        problema = (EditText) v.findViewById(R.id.altaproblema);
        encargado = (EditText) v.findViewById(R.id.altaprogramador);
        guardar = (Button) v.findViewById(R.id.altaguardar);
        radioGroup = (RadioGroup) v.findViewById(R.id.altatipoDeMantenimiento);
        fecha=(EditText) v.findViewById(R.id.altafecha);
        radioButton1=(RadioButton) v.findViewById(R.id.altacorrectivo);
        radioButton2=(RadioButton) v.findViewById(R.id.altapreventivo);
        mantenimientos = new Mantenimiento();


                guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
                maxim = (int) (Math.random() * 200);
                String tipo;
                if (radioButton1.isChecked()){
                    tipo="Correctivo";
                }
                else {
                    tipo="Preventivo";
                }

                Map<String,Object> datosPreguntas= new HashMap<>();
                datosPreguntas.put("fecha", fecha.getText().toString() );
                datosPreguntas.put("nompreProgramador", encargado.getText().toString());
                datosPreguntas.put("problema",problema.getText().toString());
                datosPreguntas.put("tipo", tipo);
                datosPreguntas.put("position", maxim);
                mRootReference.child("Mantenimiento").child(Integer.toString(maxim)).setValue(datosPreguntas);
                Toast.makeText(v.getContext(), "Reporte guardado", Toast.LENGTH_SHORT).show();
                problema.setText("");
                encargado.setText("");
                fecha.setText("");
            }
        });
        return v;
    }
}

