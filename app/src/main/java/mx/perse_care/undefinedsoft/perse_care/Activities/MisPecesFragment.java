package mx.perse_care.undefinedsoft.perse_care.Activities;

import android.content.res.Resources;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mx.perse_care.undefinedsoft.perse_care.Model.Peces;
import mx.perse_care.undefinedsoft.perse_care.R;

public class MisPecesFragment extends Fragment {
    private DatabaseReference mRef;
    private ArrayList<Peces> peces;
    private RecyclerView pecesRecycler;
    private RecyclerViewAdapterMisPeces adapter;
    private FirebaseAuth mAuth;


    public MisPecesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth= FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();
        mRef=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("InfoPeces");
        View view = inflater.inflate(R.layout.activity_mis_peces_fragment,container,false);
        pecesRecycler= (RecyclerView) view.findViewById(R.id.recyclerMisPeces);
        peces= new ArrayList<Peces>();
        traeInformacionPeces();
        return view;
    }

    private void traeInformacionPeces() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                peces.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Peces p= dataSnapshot1.getValue(Peces.class);
                    peces.add(p);
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
        pecesRecycler.setLayoutManager(linearLayoutManager);
        adapter= new RecyclerViewAdapterMisPeces(peces, getActivity());
        pecesRecycler.setAdapter(adapter);
    }
}
