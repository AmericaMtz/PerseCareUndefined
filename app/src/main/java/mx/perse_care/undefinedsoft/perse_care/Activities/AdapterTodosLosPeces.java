package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.Peces;
import mx.perse_care.undefinedsoft.perse_care.R;

public class AdapterTodosLosPeces extends RecyclerView.Adapter<AdapterTodosLosPeces.PictureViewHolderTodosLosPeces>
implements View.OnClickListener {
    private ArrayList<Peces> pe;
    private  View.OnClickListener listener;
    private Context mContext;

    public AdapterTodosLosPeces(ArrayList<Peces> pe, Context mContext) {
        this.pe = pe;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PictureViewHolderTodosLosPeces onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.blog_row_todos_los_peces, parent, false);

        view.setOnClickListener(this);
        final PictureViewHolderTodosLosPeces vHolder= new PictureViewHolderTodosLosPeces(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTodosLosPeces.PictureViewHolderTodosLosPeces holder, int position) {
        Peces peces= pe.get(position);
        holder.alimentos.setText(peces.getAlimentacion());
        holder.colores.setText(peces.getColores());
        holder.comportamiento.setText(peces.getComportamiento());
        holder.cuidados.setText(peces.getCuidados());
        holder.horariosComida.setText(peces.getCuidados());
        holder.longevidad.setText(peces.getLongevidad());
        holder.ph.setText(peces.getPH());
        holder.tamanio.setText(peces.getTamaño());
        holder.temperatura.setText(peces.getTemperatura());
        holder.tipoagua.setText(peces.getTipoAguaDePeces());
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

    public class PictureViewHolderTodosLosPeces extends RecyclerView.ViewHolder{

        private TextView alimentos, colores, comportamiento, cuidados, horariosComida, longevidad,
        ph, tamanio, temperatura, tipoagua;

        public PictureViewHolderTodosLosPeces(View itemView){
            super(itemView);
            alimentos=(TextView)itemView.findViewById(R.id.todoslospeces_alimentacion);
            colores=(TextView) itemView.findViewById(R.id.todoslospeces_colores);
            cuidados=(TextView) itemView.findViewById(R.id.todoslospeces_cuidados);
            horariosComida=(TextView) itemView.findViewById(R.id.todoslospeces_horarios);
            longevidad=(TextView) itemView.findViewById(R.id.todoslospeces_longevidad);
            ph=(TextView) itemView.findViewById(R.id.todoslospeces_ph);
            tamanio=(TextView) itemView.findViewById(R.id.todoslospeces_tamaño);
            temperatura=(TextView) itemView.findViewById(R.id.todoslospeces_temperatura);
            tipoagua=(TextView) itemView.findViewById(R.id.todoslospeces_tipodeAgua);
            comportamiento=(TextView) itemView.findViewById(R.id.todoslospeces_comportamiento);

        }
    }
}
