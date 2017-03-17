package com.example.admin.sqliteprogram;

 import android.content.ContentValues;
import android.content.Context;
 import android.content.Intent;
 import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 9/6/2016.
 */


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String col_1="ID";
    public static final String col_2="NAME";
    public static final String col_3="SURNAME";
    public static final String col_4="MARKS";
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT ,SURNAME TEXT ,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,surname);
        contentValues.put(col_4,marks);
       long result = db.insert(TABLE_NAME,null,contentValues);
       if(result==-1)
           return true;
        else
           return false;

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select  * from " +TABLE_NAME,null);
        return cursor;
    }

    public boolean updateData(String id,String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseHelper.col_1,id);
        contentValues.put(DataBaseHelper.col_2,name);
        contentValues.put(DataBaseHelper.col_3,surname);
        contentValues.put(DataBaseHelper.col_4,marks);

        db.update(TABLE_NAME,contentValues, " ID = ? " , new String[] { id });
//          db.update(TABLE_NAME,contentValues,DataBaseHelper.col_1+ "="+id,null);
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
       return db.delete(TABLE_NAME, " ID = ? " , new String[] { id });


    }
}
