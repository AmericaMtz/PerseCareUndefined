package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class RecyclerViewAdapterFaqsUsuario extends RecyclerView.Adapter<RecyclerViewAdapterFaqsUsuario.PictureViewHolderUsuario>
implements View.OnClickListener {
    private ArrayList<FAQs> fa;
    private View.OnClickListener listener;

    private Context mContext;


    public RecyclerViewAdapterFaqsUsuario(ArrayList<FAQs> fa, Context mContext) {
        this.fa = fa;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PictureViewHolderUsuario onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.blog_row, parent,false);

        view.setOnClickListener(this);
        final PictureViewHolderUsuario vHolder= new PictureViewHolderUsuario(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFaqsUsuario.PictureViewHolderUsuario holder, int position) {
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
    public void onClick(View v) {

        if(listener != null){
            listener.onClick(v);
        }
    }
    public class PictureViewHolderUsuario extends RecyclerView.ViewHolder{

        private TextView pre;
        private TextView res;


        public PictureViewHolderUsuario(View itemView){
            super(itemView);

            pre=(TextView) itemView.findViewById(R.id.textopregunta);
            res=(TextView)itemView.findViewById(R.id.textorespuesta);
        }
    }
}
