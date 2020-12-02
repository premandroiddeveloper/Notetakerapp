package com.example.notetakeapp2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayAdapter extends RecyclerView.Adapter<ArrayAdapter.ViewHolder> {
    Context con1;
    Activity ac1;
    ArrayList<String> Note_id,Note_Tit,Note,Preference;
    public ArrayAdapter(Activity activity,Context cl1, ArrayList<String> Note_id, ArrayList<String> Note_Tit, ArrayList<String> Note,ArrayList<String> Preference) {
        this.ac1=activity;
        this.con1=cl1;
        this.Note=Note;
        this.Note_id=Note_id;
        this.Note_Tit=Note_Tit;
        this.Preference=Preference;

    }

    @NonNull
    @Override
    public ArrayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(con1);
        View view=inflater.inflate(R.layout.cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArrayAdapter.ViewHolder holder, final int position) {
//holder.t1.setText(String.valueOf(Note_id.get(position)));
        holder.t2.setText(String.valueOf(Note_Tit.get(position)));

        holder.t3.setText(String.valueOf(Note.get(position)));

        holder.t4.setText(String.valueOf(Preference.get(position)));
holder.cl1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent itnew=new Intent(con1,UPDATE.class);
        itnew.putExtra("id",String.valueOf(Note_id.get(position)));
        itnew.putExtra("titles",String.valueOf(Note_Tit.get(position)));
        itnew.putExtra("main_note",String.valueOf(Note.get(position)));
        itnew.putExtra("priority",String.valueOf(Preference.get(position)));
        ac1.startActivityForResult(itnew,1);

    }
});


    }

    @Override
    public int getItemCount() {
        return Note_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4;
ConstraintLayout cl1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // t1=(TextView)itemView.findViewById(R.id.number1);
            t2=(TextView)itemView.findViewById(R.id.titles);
            t3=(TextView)itemView.findViewById(R.id.MainContext);
            t4=(TextView)itemView.findViewById(R.id.ranking);
            cl1=(ConstraintLayout)itemView.findViewById(R.id.rootlayout);
        }
    }
}
