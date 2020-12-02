package com.example.notetakeapp2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FULLADAPTER extends RecyclerView.Adapter<FULLADAPTER.ViewHolde> {
    Context con2;
    Activity ac2;
    ArrayList<String> Note_id,Note_Tit,Note,Priority;
    public FULLADAPTER(Activity activity,Context cl1, ArrayList<String> Note_id, ArrayList<String> Note_Tit, ArrayList<String> Note,ArrayList<String> preference) {
        this.ac2=activity;
        this.con2=cl1;
        this.Note=Note;
        this.Note_id=Note_id;
        this.Note_Tit=Note_Tit;
        this.Priority=preference;


    }
    @NonNull
    @Override
    public ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.fullview,parent,false);


        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, final int position) {
        holder.t1_2.setText(String.valueOf(Note_Tit.get(position)));

        holder.t2_2.setText(String.valueOf(Note.get(position)));
        holder.cl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itnew2=new Intent(con2,UPDATE.class);
                itnew2.putExtra("id",String.valueOf(Note_id.get(position)));
                itnew2.putExtra("titles",String.valueOf(Note_Tit.get(position)));
                itnew2.putExtra("main_note",String.valueOf(Note.get(position)));
                itnew2.putExtra("priority",String.valueOf(Priority.get(position)));
                ac2.startActivityForResult(itnew2,2);

            }
        });
    }



    @Override
    public int getItemCount() {
        return Note_id.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        TextView t1_2,t2_2;
        ConstraintLayout cl2;
        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            t1_2=(TextView)itemView.findViewById(R.id.fulltitle);
            t2_2=(TextView)itemView.findViewById(R.id.fullnote);
            cl2=(ConstraintLayout)itemView.findViewById(R.id.constrains);
        }
    }
}
