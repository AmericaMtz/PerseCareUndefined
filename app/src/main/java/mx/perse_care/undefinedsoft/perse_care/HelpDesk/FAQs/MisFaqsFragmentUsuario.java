package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class MisFaqsFragmentUsuario extends Fragment {
    private DatabaseReference mRef;
    private ArrayList<FAQs> faQs;
    private RecyclerView faqsRecycler;
    private RecyclerViewAdapterFaqsUsuario adapter;

    public MisFaqsFragmentUsuario() {
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mis_faqs_fragment_usuario, container, false);
        mRef = FirebaseDatabase.getInstance().getReference().child("FAQs");
        faqsRecycler = (RecyclerView) view.findViewById(R.id.recyclerUsuario);
        faQs = new ArrayList<FAQs>();
        traePreguntas();
        return view;
    }

    private void traePreguntas() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                faQs.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    FAQs f= dataSnapshot1.getValue(FAQs.class);
                    faQs.add(f);

                }
                imprimirTarjetas();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ups.... algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void imprimirTarjetas() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        faqsRecycler.setLayoutManager(linearLayoutManager);
        adapter= new RecyclerViewAdapterFaqsUsuario(faQs, getActivity());
        faqsRecycler.setAdapter(adapter);
    }
}
