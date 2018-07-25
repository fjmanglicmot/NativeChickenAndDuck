package com.example.cholomanglicmot.nativechickenandduck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Project.db";
    public static final String TABLE_NAME = "profile_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "BREED";
    public static final String COL_3 = "REGION";
    public static final String COL_4 = "PROVINCE";
    public static final String COL_5 = "TOWN";
    public static final String COL_6 = "BARANGAY";
    public static final String COL_7 = "PHONE";
    public static final String COL_8 = "EMAIL";

 /*   public static final String TABLE_NAME2 = "pen_table";
    public static final String PEN_COL_1 = "PEN_NUMBER";
    public static final String PEN_COL_2 = "PEN_TYPE";
    public static final String PEN_COL_3 = "PEN_CAPACITY";
    public static final String PEN_COL_4 = "PEN_INVENTORY";*/

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID TEXT PRIMARY KEY,BREED TEXT,REGION TEXT,PROVINCE TEXT,TOWN TEXT,BARANGAY TEXT,PHONE TEXT,EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }



    public boolean insertDataAddress(String id, String breed, String region, String province, String town, String barangay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, breed);
        contentValues.put(COL_3, region);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, town);
        contentValues.put(COL_6, barangay);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;
    }
    public boolean insertDataContacts(String id, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_7, phone);
        contentValues.put(COL_8, email);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1)
            return  false;
        else
            return true;


    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }

    public boolean updateDataAddress(String id, String breed, String region, String province, String town, String barangay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, breed);
        contentValues.put(COL_3, region);
        contentValues.put(COL_4, province);
        contentValues.put(COL_5, town);
        contentValues.put(COL_6, barangay);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{ id });
        return true;
    }

    public boolean updateDataContacts(String id, String phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_7, phone);
        contentValues.put(COL_8, email);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{ id });
        return true;
    }
}
