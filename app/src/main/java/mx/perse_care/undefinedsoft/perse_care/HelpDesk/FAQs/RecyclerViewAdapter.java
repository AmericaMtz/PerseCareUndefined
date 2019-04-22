package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<FAQs> faq;

    public RecyclerViewAdapter(Context c, ArrayList<FAQs> p){
        context=c;
        faq=p;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.blog_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.pregun.setText(faq.get(position).getPregunta());
        holder.respu.setText(faq.get(position).getRespuesta());
    }

    @Override
    public int getItemCount() {
        return faq.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pregun, respu;

        public MyViewHolder(View itemView){
            super(itemView);
            pregun=(TextView)itemView.findViewById(R.id.textopregunta);
            respu=(TextView)itemView.findViewById(R.id.textorespuesta);
        }
    }

}
