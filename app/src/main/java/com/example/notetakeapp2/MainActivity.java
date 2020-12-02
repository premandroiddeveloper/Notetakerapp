package com.example.notetakeapp2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
RecyclerView rc1;
FloatingActionButton fg1;
ArrayList<String> Note_TIITLE = new ArrayList<>(),NOTE,PRIORITY,ID;
databasecreator db1;
ArrayAdapter ad1;
FULLADAPTER ad2;
TextView tj1;
ImageView imag1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc1=(RecyclerView)findViewById(R.id.recycle);
        db1=new databasecreator(MainActivity.this);
        tj1=(TextView)findViewById(R.id.nodata);
        imag1=(ImageView)findViewById(R.id.nilll);
        fg1=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        fg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(MainActivity.this,NOTES.class);
                startActivityForResult(i1,2);
            }
        });
        Note_TIITLE=new ArrayList<>();
        NOTE=new ArrayList<>();
        PRIORITY=new ArrayList<>();
        ID=new ArrayList<>();
        Storingdata();
        ad1=new ArrayAdapter(MainActivity.this,this,ID,Note_TIITLE,NOTE,PRIORITY);
        rc1.setAdapter(ad1);
        rc1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
    
   public void Storingdata(){
        Cursor cj1=db1.alldata();
        if(cj1.getCount()==0) {
            //Toast.makeText(MainActivity.this,"NO DATA FOUND",Toast.LENGTH_LONG).show();
            imag1.setVisibility(View.VISIBLE);
            tj1.setVisibility(View.VISIBLE);


        }
        else{
            while(cj1.moveToNext()){
                ID.add(cj1.getString(0));
                Note_TIITLE.add(cj1.getString(1));
                NOTE.add(cj1.getString(2));
                PRIORITY.add(cj1.getString(3));
            }
            imag1.setVisibility(View.GONE);
            tj1.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deletingprocess){
            db1.deletealldata();
            Toast.makeText(MainActivity.this,"ALL DATA DELETED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
            recreate();
            Intent check=new Intent(MainActivity.this,MainActivity.class);
            startActivity(check);
            finish();
        }
        else if(item.getItemId()==R.id.GridView){
            ad1=new ArrayAdapter(MainActivity.this,this,ID,Note_TIITLE,NOTE,PRIORITY);
            GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
            rc1.setLayoutManager(gridLayoutManager);
            rc1.setAdapter(ad1);
        }
        else if(item.getItemId()==R.id.RECYCLEView){
            ad1=new ArrayAdapter(MainActivity.this,this,ID,Note_TIITLE,NOTE,PRIORITY);

            rc1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rc1.setAdapter(ad1);
        }
        else if(item.getItemId()==R.id.fullView){
           ad2=new FULLADAPTER(MainActivity.this,this,ID,Note_TIITLE,NOTE,PRIORITY) ;
           rc1.setAdapter(ad2);
           rc1.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }

        return true;
    }
}