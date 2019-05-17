package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mx.perse_care.undefinedsoft.perse_care.Model.Peces;
import mx.perse_care.undefinedsoft.perse_care.PreguntasParte3;
import mx.perse_care.undefinedsoft.perse_care.R;

public class RecyclerViewAdapterMisPeces extends RecyclerView.Adapter<RecyclerViewAdapterMisPeces.PictureHolderMisPeces>
        implements View.OnClickListener{
    private ArrayList<Peces> pe;
    private View.OnClickListener listener;
    Dialog myDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef, bbdd;
    private Context mContext;
    private static final Random rgenerador= new Random();
    private static final Integer[] imagenesID= {
            R.drawable.pez12, R.drawable.pez13, R.drawable.pez14, R.drawable.pez15,
            R.drawable.pez16, R.drawable.pez17, R.drawable.pez18, R.drawable.pez19, R.drawable.pez20,
            R.drawable.pez22, R.drawable.pez23, R.drawable.pez24, R.drawable.pez25,
            R.drawable.pez26, R.drawable.pez27, R.drawable.pez28, R.drawable.pez29, R.drawable.pez30};


    public RecyclerViewAdapterMisPeces(ArrayList<Peces> pe, Context mContext) {
        this.pe = pe;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public PictureHolderMisPeces onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        final View view=LayoutInflater.from(mContext).inflate(R.layout.blog_row_mispeces, parent, false);
        view.setOnClickListener(this);

        myDialog= new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_mispeces);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Integer q= imagenesID[rgenerador.nextInt(imagenesID.length)];
        final PictureHolderMisPeces vHolder= new PictureHolderMisPeces(view);
        mAuth= FirebaseAuth.getInstance();

        vHolder.item_misPeces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String user_id = mAuth.getCurrentUser().getUid();
                mRef=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("InfoPeces");
                final EditText dialog_nombre= (EditText) myDialog.findViewById(R.id.dialog_misPeces2Nombre);
                final String[] SPINNERLISTDulce ={"Pez ángel", "Pez Betta", "Pez cebra", "Pez dorado", "Pez guppy", "Pez gurami", "Pez Koi", "Pez Limpia peceras", "Desconocida"};
                final String[] SPINNERLISTSalada= {"Desconocido", "Pez cirujano", "Pez Damisela", "Pez Payaso", "Pez Mariposa", "Pez Abuela Loreto"};
                final ArrayAdapter<String> arrayAdapterDulce= new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, SPINNERLISTDulce);
                final ArrayAdapter<String> arrayAdapterSalada=new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, SPINNERLISTSalada);
                final MaterialBetterSpinner spinnerPez1=(MaterialBetterSpinner) myDialog.findViewById(R.id.dialog_misPeces2Spinner);
                FloatingActionButton btn_edita= (FloatingActionButton) myDialog.findViewById(R.id.dialog_misPeces2Edita);
                FloatingActionButton btn_borra= (FloatingActionButton) myDialog.findViewById(R.id.dialog_misPeces2Borra);
                FloatingActionButton btn_add=(FloatingActionButton) myDialog.findViewById(R.id.dialog_misPeces2Add);
                dialog_nombre.setText(pe.get(vHolder.getAdapterPosition()).getNombre());
                bbdd=  FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("InformacionPreguntas");
                bbdd.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                            Peces pe = datasnapshot.getValue(Peces.class);
                            String tipoAgua = pe.getTipoAguaDePeces();
                            String especie= pe.getEspecie();
                            if(tipoAgua.equals("De agua dulce.")){
                                    spinnerPez1.setAdapter(arrayAdapterDulce);


                            }else {
                                spinnerPez1.setAdapter(arrayAdapterSalada);

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(mContext, "ups... algo salio mal", Toast.LENGTH_SHORT).show();
                    }
                });
                myDialog.show();
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(mContext, AltaPez.class);
                        mContext.startActivity(intent);
                    }
                });
                btn_borra.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     String pos= pe.get(vHolder.getAdapterPosition()).getPosicion();
                                                     pe.remove(vHolder.getAdapterPosition());
                                                     mRef.child(pos).removeValue();
                                                     notifyItemRemoved(vHolder.getAdapterPosition());
                                                     Snackbar.make(v, "Datos Eliminados", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                                     myDialog.dismiss();
                                                 }
                    }
                );
                btn_edita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pos= pe.get(vHolder.getAdapterPosition()).getPosicion();
                        String textoNombre= dialog_nombre.getText().toString();
                        String textoSpinner= spinnerPez1.getText().toString();

                            Map<String,Object> datosPreguntas= new HashMap<>();
                            datosPreguntas.put("nombre", textoNombre );
                            datosPreguntas.put("especie", textoSpinner);
                            datosPreguntas.put("posicion", pe.get(vHolder.getAdapterPosition()).getPosicion());
                            mRef.child(pos).setValue(datosPreguntas);
                            Toast.makeText(mContext, "Editado correctamente", Toast.LENGTH_SHORT).show();
                            myDialog.dismiss();
                    }
                });

            }
        });
        return vHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterMisPeces.PictureHolderMisPeces holder, int position) {
        Peces p= pe.get(position);
        holder.especiePez.setText(p.getEspecie());
        holder.nombrePez.setText(p.getNombre());
        int resource= imagenesID[rgenerador.nextInt(imagenesID.length)];
        holder.imagenRandom.setImageResource(resource);
    }

    @Override
    public int getItemCount() {
        return pe.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }
    public class PictureHolderMisPeces extends RecyclerView.ViewHolder {
        private CardView item_misPeces;
        private TextView nombrePez;
        private TextView especiePez;
        private ImageView imagenRandom;

        public PictureHolderMisPeces(View itemView){
            super(itemView);
            item_misPeces=(CardView) itemView.findViewById(R.id.pictureCardMisPeces);
            nombrePez=(TextView) itemView.findViewById(R.id.MisPecesNombre);
            especiePez=(TextView) itemView.findViewById(R.id.MisPecesEspecie);
            imagenRandom=(ImageView) itemView.findViewById(R.id.MisPecesImagen);
        }

    }
}
