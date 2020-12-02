package com.example.notetakeapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UPDATE extends AppCompatActivity {

    TextView t1,t2;
    EditText ed1,ed2,ed3;
    Button b1,b2,b3;
    databasecreator db4;
String data;
String main_data;
    TimePickerDialog picker;
    DatePickerDialog.OnDateSetListener mdateselected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_p_d_a_t_e);
        t1=(TextView)findViewById(R.id.textView32);
        t2=(TextView)findViewById(R.id.textView42);
        ed1=(EditText)findViewById(R.id.edittitle);
        ed2=(EditText)findViewById(R.id.editpriority);
        ed3=(EditText)findViewById(R.id.editnote);
data=getIntent().getStringExtra("id");
        db4=new databasecreator(UPDATE.this) ;
        b1=(Button)findViewById(R.id.updatebutton);
        b2=(Button)findViewById(R.id.deletebutton);
        b3=(Button)findViewById(R.id.sharing);
        getIntentdata();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db4.update(Integer.parseInt(data),ed3.getText().toString().trim(),ed1.getText().toString().trim(),Integer.parseInt(ed2.getText().toString().trim()));

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db4.delete(Integer.parseInt(data));
                finish();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_data="TITLE OF NOTE:"+ed1.getText().toString().trim()+"\n"+
                        "      "+ed3.getText().toString().trim();
                Intent i1=new Intent(Intent.ACTION_SEND_MULTIPLE);
                i1.putExtra(Intent.EXTRA_TEXT,main_data);

                i1.setType("text/plain");
                Intent shareIntent=Intent.createChooser(i1,null);
                startActivity(shareIntent);
            }
        });
        mdateselected=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                t1.setText(year+"/"+month+"/"+dayOfMonth);
            }
        };
    }
    void getIntentdata(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titles")
        && getIntent().hasExtra("main_note") && getIntent().hasExtra("priority")){
        //    data=getIntent().getStringExtra("id");
            ed1.setText(getIntent().getStringExtra("titles"));
            ed2.setText(getIntent().getStringExtra("priority"));
            ed3.setText(getIntent().getStringExtra("main_note"));



        }
        else{
            Toast.makeText(this,"NO data ",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menus=getMenuInflater();
        menus.inflate(R.menu.reminder,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.remain){
            Calendar cal=Calendar.getInstance();
            int hour=cal.get(Calendar.HOUR_OF_DAY);
            int minitu=cal.get(Calendar.MINUTE);
            int year=cal.get(Calendar.YEAR);
            Date now=new Date();
            SimpleDateFormat smd=new SimpleDateFormat("E");
            String day_name=smd.format(now);
            SimpleDateFormat smp=new SimpleDateFormat("MMMM");
            String month_name=smp.format(new Date());
            String first=day_name+", "+" "+month_name;

            DatePickerDialog sete=new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,mdateselected,year,hour,minitu);
            sete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            sete.show();
            picker = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                            t2.setText(sHour + ":" + sMinute);
                        }
                    }, hour, minitu, true);
            picker.show();

        }

        return true;
    }
}