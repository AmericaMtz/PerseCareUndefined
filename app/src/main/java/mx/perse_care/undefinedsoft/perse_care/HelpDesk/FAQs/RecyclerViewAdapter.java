package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PictureViewHolder>
        implements View.OnClickListener{

    private ArrayList<FAQs> fa;
    private View.OnClickListener listener;
    Dialog myDialog;
    private DatabaseReference mRef;
    private Context mContext;


    public RecyclerViewAdapter(ArrayList<FAQs> fa, Context mContext) {
        this.fa = fa;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.blog_row, parent,false);

        view.setOnClickListener(this);
        myDialog= new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_faqs);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final PictureViewHolder vHolder= new PictureViewHolder(view);



        vHolder.item_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               mRef = FirebaseDatabase.getInstance().getReference().child("FAQs");
                final EditText dialog_pregunta= (EditText) myDialog.findViewById(R.id.dialog_pregunta);
                final EditText dialog_respuesta= (EditText) myDialog.findViewById(R.id.dialog_respuesta);

                Button btn_edita= (Button) myDialog.findViewById(R.id.dialog_btn_guardar);
                Button btn_borra= (Button) myDialog.findViewById(R.id.dialog_btn_borrar);
                dialog_pregunta.setText(fa.get(vHolder.getAdapterPosition()).getPregunta());
                dialog_respuesta.setText(fa.get(vHolder.getAdapterPosition()).getRespuesta());
                //Dialog
                myDialog.show();
              btn_borra.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String pos= Integer.toString(fa.get(vHolder.getAdapterPosition()).getPosition());

                     fa.remove(vHolder.getAdapterPosition());
                     mRef.child(pos).removeValue();
                     notifyItemRemoved(vHolder.getAdapterPosition());
                     Toast.makeText(mContext, "Pregunta eliminada correctamente ", Toast.LENGTH_LONG).show();
                      myDialog.dismiss();
                  }
              });
                btn_edita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pos= Integer.toString(fa.get(vHolder.getAdapterPosition()).getPosition());
                        String textoP= dialog_pregunta.getText().toString();
                        String textoR= dialog_respuesta.getText().toString();

                        Map<String,Object> datosPreguntas= new HashMap<>();
                        datosPreguntas.put("pregunta", textoP );
                        datosPreguntas.put("respuesta", textoR);
                        datosPreguntas.put("position", fa.get(vHolder.getAdapterPosition()).getPosition());
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
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.PictureViewHolder holder, final int position) {
        FAQs f= fa.get(position);
        holder.pre.setText(f.getPregunta());
        holder.res.setText(f.getRespuesta());



    }

    @Override
    public int getItemCount() {
        return fa.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }



    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private CardView item_faq;
        private TextView pre;
        private TextView res;


        public PictureViewHolder(View itemView){
            super(itemView);
            item_faq=(CardView) itemView.findViewById(R.id.pictureCard);
            pre=(TextView) itemView.findViewById(R.id.textopregunta);
            res=(TextView)itemView.findViewById(R.id.textorespuesta);
        }
    }

}