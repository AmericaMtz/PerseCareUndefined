package mx.perse_care.undefinedsoft.perse_care.HelpDesk.Mantenimiento;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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


import mx.perse_care.undefinedsoft.perse_care.Model.Mantenimiento;
import mx.perse_care.undefinedsoft.perse_care.R;

public class MantenimientoFragment extends Fragment {
    private DatabaseReference mRef;
    private ArrayList <Mantenimiento> mantenimientoss;
    private RecyclerView mantenimientosRecycler;
    private RecyclerViewAdapterMantenimiento adapter;

    public MantenimientoFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view= inflater.inflate(R.layout.activity_mantenimiento_fragment, container, false);
    mRef =FirebaseDatabase.getInstance().getReference().child("Mantenimiento");
    mantenimientosRecycler=(RecyclerView) view.findViewById(R.id.recyclerMM);
    mantenimientoss= new ArrayList<Mantenimiento>();
    traePre();
    return view;
    }

    private void traePre() {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mantenimientoss.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Mantenimiento m= dataSnapshot1.getValue(Mantenimiento.class);
                    mantenimientoss.add(m);
                }
                imprimeTarjeta();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Ups.... algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imprimeTarjeta() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        mantenimientosRecycler.setLayoutManager(linearLayoutManager);
        adapter= new RecyclerViewAdapterMantenimiento(mantenimientoss, getActivity());
        mantenimientosRecycler.setAdapter(adapter);
    }
}
