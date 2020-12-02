package com.example.notetakeapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class databasecreator extends SQLiteOpenHelper {
    Context con;
    public databasecreator(@Nullable Context context) {

        super(context,"NOTES.DB",null,1);
        this.con=context;
        Log.d("prem","creates");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String oncrete="CREATE TABLE note_main ( id INTEGER PRIMARY KEY AUTOINCREMENT, note_title TEXT, note TEXT, note_priority INTEGER)";
        db.execSQL(oncrete);
        Log.d("jay","NOTES CREATED");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS note_main";
        db.execSQL(query);
        onCreate(db);


    }
    public void insert(String title,String main_note,int priority){
        SQLiteDatabase sl1=this.getWritableDatabase();
        ContentValues cle2=new ContentValues();
       cle2.put("note_title",title);
       cle2.put("note",main_note);
       cle2.put("note_priority",priority);

        long result=sl1.insert("note_main",null,cle2);
        if(result==-1){
            Toast.makeText(con,"FAiled",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(con,"SUCCESSFULL DATA ADDED",Toast.LENGTH_SHORT).show();
        }
        sl1.close();
    }

    public Cursor alldata() {
        String query="SELECT * FROM note_main";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
                if(db!=null){
                    cursor=db.rawQuery(query,null);

                }
                return cursor;
    }
    void update(int key_id,String noteupdate,String notetitleupdata,int prirityupdate){
        SQLiteDatabase db1=this.getWritableDatabase();
        String query="UPDATE note_main SET" +" note_title"+" = '"+notetitleupdata+"', "+"note"+" = '"+noteupdate+"', "+"note_priority "+"= '"+prirityupdate+"' where "+"id"+"= " +key_id;
        db1.execSQL(query);
        db1.close();

    }
    void delete(int key_id){
        SQLiteDatabase db4=this.getWritableDatabase();
        String query="DELETE FROM note_main"+" WHERE "+"id "+"= "+key_id;
        db4.execSQL(query);
        Toast.makeText(con,"Data deleted successfully",Toast.LENGTH_SHORT).show();
        db4.close();
    }
    void deletealldata(){
        SQLiteDatabase sb=this.getWritableDatabase();
        sb.execSQL("DELETE FROM note_main");
        sb.close();

    }
}