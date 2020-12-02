package com.example.notetakeapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NOTES extends AppCompatActivity {
TextView t1,t2;
EditText ed1,ed2,ed3;
Button b1;
ArrayAdapter arr1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_o_t_e_s);
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView4);
        ed1=(EditText)findViewById(R.id.editTextTextPersonName);
        ed2=(EditText)findViewById(R.id.editTextTextPersonName2);
        ed3=(EditText)findViewById(R.id.editTextTextMultiLine);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databasecreator db=new databasecreator(NOTES.this);
                db.insert(ed1.getText().toString().trim(),ed3.getText().toString(),Integer.parseInt(ed2.getText().toString().trim()));
finish();

            }
        });


    }



}