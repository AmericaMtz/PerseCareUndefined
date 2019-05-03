package mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mx.perse_care.undefinedsoft.perse_care.Model.Mantenimiento;
import mx.perse_care.undefinedsoft.perse_care.R;

public class RecyclerViewAdapterMantenimiento extends RecyclerView.Adapter<RecyclerViewAdapterMantenimiento.PictureViewHolderM>
        implements View.OnClickListener {

    private ArrayList <Mantenimiento> ma;
    private View.OnClickListener listener;
    Dialog myDialogM;
    private DatabaseReference mRef;
    private Context mContextM;

    public RecyclerViewAdapterMantenimiento(ArrayList<Mantenimiento> ma, Context mContextM){
        this.ma= ma;
        this.mContextM= mContextM;
    }

    @NonNull
    @Override
    public PictureViewHolderM onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContextM).inflate(R.layout.blog_row_mantenimiento, parent, false);
        view.setOnClickListener(this);
        myDialogM= new Dialog(mContextM);
        myDialogM.setContentView(R.layout.dialog_mantenimiento);
        myDialogM.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final PictureViewHolderM vHolder= new PictureViewHolderM(view);

        vHolder.item_mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef = FirebaseDatabase.getInstance().getReference().child("Mantenimiento");
                final EditText dialog_Problema=(EditText) myDialogM.findViewById(R.id.dialog_problema);
                final EditText dialog_Programador= (EditText) myDialogM.findViewById(R.id.dialog_programador);
                final RadioButton dialog_RPreventivo=(RadioButton) myDialogM.findViewById(R.id.dialog_Rpreventivo);
                final RadioButton dialog_RCorrectivo=(RadioButton) myDialogM.findViewById(R.id.dialog_Rcorrectivo);

               FloatingActionButton btn_edit=(FloatingActionButton) myDialogM.findViewById(R.id.dialog_btn_guardarMantenimiento);
               FloatingActionButton btn_borr=(FloatingActionButton) myDialogM.findViewById(R.id.dialog_btn_borrarMantenimiento);
               dialog_Problema.setText(ma.get(vHolder.getAdapterPosition()).getProblema());
               dialog_Programador.setText(ma.get(vHolder.getAdapterPosition()).getNompreProgramador());
               if(ma.get(vHolder.getAdapterPosition()).getTipo().equals("Preventivo")){
                   dialog_RPreventivo.isChecked();

               }else {
                   dialog_RCorrectivo.isChecked();
               }
               myDialogM.show();

               btn_borr.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String pos= Integer.toString(ma.get(vHolder.getAdapterPosition()).getPosition());
                        ma.remove(vHolder.getAdapterPosition());
                        mRef.child(pos).removeValue();
                       notifyItemRemoved(vHolder.getAdapterPosition());
                       Toast.makeText(mContextM, "Tu pregunta fue eliminada", Toast.LENGTH_LONG).show();
                       myDialogM.dismiss();
                   }
               });
               btn_edit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String pos= Integer.toString(ma.get(vHolder.getAdapterPosition()).getPosition());
                       String tipo;
                       if (dialog_RCorrectivo.isChecked()){
                           tipo= "Correctivo";
                       }else {
                           tipo="Preventivo";
                       }

                       Map<String, Object> datosMantenimiento= new HashMap<>();
                       datosMantenimiento.put("fecha", ma.get(vHolder.getAdapterPosition()).getFecha());
                       datosMantenimiento.put("nompreProgramador",dialog_Programador.getText().toString());
                       datosMantenimiento.put("problema",dialog_Problema.getText().toString());
                       datosMantenimiento.put("tipo", tipo);
                       datosMantenimiento.put("position", ma.get(vHolder.getAdapterPosition()).getPosition());
                       mRef.child(pos).setValue(datosMantenimiento);
                       Toast.makeText(mContextM, "Reporte modificado con éxito", Toast.LENGTH_SHORT).show();
                       myDialogM.dismiss();
                   }
               });
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterMantenimiento.PictureViewHolderM holder, final int position) {
        Mantenimiento m= ma.get(position);
        holder.fech.setText(m.getFecha());
        holder.programa.setText(m.getNompreProgramador());
        holder.proble.setText(m.getProblema());
        holder.tip.setText(m.getTipo());


    }

    @Override
    public int getItemCount() {
        return ma.size();
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
 public class PictureViewHolderM extends RecyclerView.ViewHolder{
        private CardView item_mantenimiento;
        private TextView proble;
        private TextView programa;
        private TextView tip;
        private TextView fech;

        public PictureViewHolderM(View itemView){
            super(itemView);
            item_mantenimiento=(CardView) itemView.findViewById(R.id.pictureCardMM);
            proble=(TextView) itemView.findViewById(R.id.txtproblem);
            programa=(TextView) itemView.findViewById(R.id.txtnombreProgramador);
            tip=(TextView)itemView.findViewById(R.id.txttipo);
            fech=(TextView) itemView.findViewById(R.id.txtfecha);
        }
 }
}


