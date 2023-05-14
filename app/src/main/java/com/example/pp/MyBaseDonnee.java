package com.example.pp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyBaseDonnee extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "gestion_employees.db";
    public static final String TABLE_NAME = "employees_table";
    public static final String TABLE_NAME2 = "admin_table";
    public static final String TABLE_NAME3 = "demande_conge";

    public static final String Table_name = "Student_table";
    public static final String col_id = "Id";
    public static final String col_name = "name";
    public static final String col_marks = "marks";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
    public static final String COL_5 = "demandeur";
    public static final String COL_6 = "date_debut";
    public static final String COL_7 = "date_fin";
    public static final String COL_8 = "MATRUCULE";
    public static final String COL_9 = "TYPE";


    public MyBaseDonnee(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME3 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,  DEMANDEUR TEXT,  DATE_DEBUT TEXT, DATE_FIN TEXT)");

        db.execSQL("create table " + Table_name +" (Id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT , marks INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS "+Table_name);

        onCreate(db);
    }

    // ajouter un employees dans le tableau employees
    public boolean insertEmployees(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    // ajouter un admi dans le tableau admi

    public boolean insertAdmin(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkusernameEmployees(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + "=?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernameAdmi(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + COL_2 + "=?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public Boolean checkusernamepasswordEmployees(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? AND " + COL_4 + "=?", new String[]{name, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepasswordAdmi(String name, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2 + " WHERE " + COL_2 + "=? AND " + COL_4 + "=?", new String[]{name, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean demandeConge(String demandeur, String dateDebut, String dateFin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, demandeur);
        contentValues.put(COL_6, dateDebut);
        contentValues.put(COL_7, dateFin);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public int addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_8, employee.getMatricule());
        values.put(COL_2, employee.getName());
        values.put(COL_9, employee.getType());
        long insertId = db.insert(TABLE_NAME, null, values);
        db.close();
        return (int) insertId;
    }

    public int deleteEmployee(int matricule) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_8 + "=?", new String[]{String.valueOf(matricule)});
    }

    public boolean updateEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2, employee.getName());
        values.put(COL_9, employee.getType());
        db.update(TABLE_NAME, values, COL_8 + "=?", new String[]{String.valueOf(employee.getMatricule())});
        return true;
    }


    public Boolean insertuserdata(String matricule, String name, String type) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8, matricule);
        contentValues.put(COL_2, name);
        contentValues.put(COL_9, type);
        long result = DB.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateuserdata(String name, String contact, String dob) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, contact);
        contentValues.put(COL_9, dob);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update(TABLE_NAME, contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete(TABLE_NAME, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean insertData(String name,String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_name,name);
        cv.put(col_marks,marks);
        Long result = db.insert(Table_name,null,cv);
        if(result == -1 )
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
    



