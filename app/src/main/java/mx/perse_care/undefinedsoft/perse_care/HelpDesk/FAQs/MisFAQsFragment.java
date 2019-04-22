package mx.perse_care.undefinedsoft.perse_care.HelpDesk.FAQs;

import android.content.Intent;
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

public class MisFAQsFragment extends Fragment {

    private View contactiew;
    private RecyclerView myRecyclerView;
    private DatabaseReference mRef;
    private ArrayList<FAQs> faQs;
    private RecyclerView faqsRecycler;
    private RecyclerViewAdapter adapter;
    public MisFAQsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mis_faqs_fragment, container, false);

//recyclerView
        faqsRecycler = (RecyclerView) view.findViewById(R.id.recycler);
        //set layout as LinearLayout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        faqsRecycler.setLayoutManager(linearLayoutManager);

        faQs= new ArrayList<FAQs>();
        //send query to firebaseDatabase
        mRef= FirebaseDatabase.getInstance().getReference().child("FAQs");


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    FAQs f= dataSnapshot1.getValue(FAQs.class);
                    faQs.add(f);

                }
                adapter= new RecyclerViewAdapter(getContext(), faQs);
                faqsRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                      Toast.makeText(getContext(), "Ups.... algo salio mal", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
