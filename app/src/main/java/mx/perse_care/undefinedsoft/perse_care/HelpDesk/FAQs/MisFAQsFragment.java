package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.FAQs;
import mx.perse_care.undefinedsoft.perse_care.R;

public class MisFAQsFragment extends Fragment{

    private RecyclerView myRecyclerView;
    private DatabaseReference mRef;
    private ArrayList<FAQs> faQs;
    private RecyclerView faqsRecycler;
    private RecyclerViewAdapter adapter;

    ImageView imagenBote;
    public MisFAQsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                              ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mis_faqs_fragment, container, false);
        mRef = FirebaseDatabase.getInstance().getReference().child("FAQs");

//recyclerView
        faqsRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        //set layout as LinearLayout

        faQs = new ArrayList<FAQs>();
        //send query to firebaseDatabase

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

        adapter= new RecyclerViewAdapter(faQs, getActivity());
        faqsRecycler.setAdapter(adapter);
    }
}
