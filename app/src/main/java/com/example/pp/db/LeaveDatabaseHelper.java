package com.example.pp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pp.Employee;
import com.example.pp.model.Absence;
import com.example.pp.model.Leave;

import java.util.ArrayList;
import java.util.List;

public class LeaveDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "leave_management.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_EMPLOYEE = "employees";
    private static final String TABLE_ADMINISTRATOR = "administrators";
    private static final String TABLE_LEAVE = "leaves";
    private static final String TABLE_ABSENCE = "absences";

    // Common column names
    private static final String COLUMN_ID = "id";

    // Employee table columns
    private static final String COLUMN_EMPLOYEE_NAME = "employee_name";

    // Administrator table columns
    private static final String COLUMN_ADMINISTRATOR_NAME = "administrator_name";

    // Leave table columns
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_LEAVE_TYPE = "leave_type";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_LEAVE_STATUS = "leave_status";

    // Absence table columns
    private static final String COLUMN_ABSENCE_DATE = "absence_date";
    private static final String COLUMN_ABSENCE_REASON = "absence_reason";

    public LeaveDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create employee table
        String createEmployeeTableQuery = "CREATE TABLE " + TABLE_EMPLOYEE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMPLOYEE_NAME + " TEXT)";
        db.execSQL(createEmployeeTableQuery);

        // Create administrator table
        String createAdministratorTableQuery = "CREATE TABLE " + TABLE_ADMINISTRATOR + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ADMINISTRATOR_NAME + " TEXT)";
        db.execSQL(createAdministratorTableQuery);

        // Create leave table
        String createLeaveTableQuery = "CREATE TABLE " + TABLE_LEAVE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMPLOYEE_ID + " INTEGER, " +
                COLUMN_LEAVE_TYPE + " TEXT, " +
                COLUMN_START_DATE + " TEXT, " +
                COLUMN_END_DATE + " TEXT, " +
                COLUMN_LEAVE_STATUS + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_EMPLOYEE_ID + ") REFERENCES " +
                TABLE_EMPLOYEE + "(" + COLUMN_ID + "))";
        db.execSQL(createLeaveTableQuery);

        // Create absence table
        String createAbsenceTableQuery = "CREATE TABLE " + TABLE_ABSENCE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMPLOYEE_ID + " INTEGER, " +
                COLUMN_ABSENCE_DATE + " TEXT, " +
                COLUMN_ABSENCE_REASON + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_EMPLOYEE_ID + ") REFERENCES " +
                TABLE_EMPLOYEE + "(" + COLUMN_ID + "))";
        db.execSQL(createAbsenceTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEAVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABSENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMINISTRATOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

        // Recreate the tables
        onCreate(db);
    }

    // Method to insert a new employee
    public long insertEmployee(String employeeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_NAME, employeeName);
        return db.insert(TABLE_EMPLOYEE, null, values);
    }

    // Method to insert a new administrator
    public long insertAdministrator(String administratorName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ADMINISTRATOR_NAME, administratorName);
        return db.insert(TABLE_ADMINISTRATOR, null, values);
    }

    // Method to insert a new leave record
    public long insertLeave(int employeeId, String leaveType, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_ID, employeeId);
        values.put(COLUMN_LEAVE_TYPE, leaveType);
        values.put(COLUMN_START_DATE, startDate);
        values.put(COLUMN_END_DATE, endDate);
        values.put(COLUMN_LEAVE_STATUS, "pending");
        return db.insert(TABLE_LEAVE, null, values);
    }

    // Method to insert a new absence record
    public long insertAbsence(int employeeId, String absenceDate, String absenceReason) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMPLOYEE_ID, employeeId);
        values.put(COLUMN_ABSENCE_DATE, absenceDate);
        values.put(COLUMN_ABSENCE_REASON, absenceReason);
        return db.insert(TABLE_ABSENCE, null, values);
    }

    // Method to retrieve all leave records for a specific employee
    public List<Leave> getLeaveHistory(int employeeId) {
        List<Leave> leaveList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE +
                " WHERE " + COLUMN_EMPLOYEE_ID + " = " + employeeId;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leave leave = new Leave();
                leave.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                leave.setEmployee_id(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)));
                leave.setLeave_type(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVE_TYPE)));
                leave.setStart_date(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
                leave.setEnd_date(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));
                leave.setLeave_status(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVE_STATUS)));
                leaveList.add(leave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return leaveList;
    }

    // Method to retrieve all absence records for a specific employee
    public List<Absence> getAbsenceHistory(int employeeId) {
        List<Absence> absenceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_ABSENCE +
                " WHERE " + COLUMN_EMPLOYEE_ID + " = " + employeeId;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Absence absence = new Absence();
                absence.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                absence.setEmployeeId
                        (cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)));
                absence.setAbsenceDate(cursor.getString(cursor.getColumnIndex(COLUMN_ABSENCE_DATE)));
                absence.setAbsenceReason(cursor.getString(cursor.getColumnIndex(COLUMN_ABSENCE_REASON)));
                absenceList.add(absence);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return absenceList;
    }

    // Method to retrieve all leave demands
    public List<Leave> getAllLeaveDemands() {
        List<Leave> leaveList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leave leave =setLeaveData(cursor);
                leaveList.add(leave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return leaveList;
    }
    // Method to retrieve all pending leave demands sent by employees
    public List<Leave> getAllPendingLeaveDemands() {
        List<Leave> leaveList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE +
                " WHERE " + COLUMN_LEAVE_STATUS + " = 'pending'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leave leave =setLeaveData(cursor);
                leaveList.add(leave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return leaveList;
    }


    // Method to retrieve all approved leave demands sent by employees

    public List<Leave> getAllApprovedLeaveDemands() {
        List<Leave> leaveList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE +
                " WHERE " + COLUMN_LEAVE_STATUS + " = 'approved'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leave leave =setLeaveData(cursor);
                leaveList.add(leave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return leaveList;
    }

    // Method to retrieve all refused leave demands sent by employees
    public List<Leave> getAllRefusedLeaveDemands() {
        List<Leave> leaveList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LEAVE +
                " WHERE " + COLUMN_LEAVE_STATUS + " = 'refuses'";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Leave leave =setLeaveData(cursor);
                leaveList.add(leave);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return leaveList;
    }

    Leave setLeaveData(Cursor cursor){
        Leave leave = new Leave();
        leave.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        leave.setEmployee_id(cursor.getInt(cursor.getColumnIndex(COLUMN_EMPLOYEE_ID)));
        leave.setLeave_type(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVE_TYPE)));
        leave.setStart_date(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
        leave.setEnd_date(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));
        leave.setLeave_status(cursor.getString(cursor.getColumnIndex(COLUMN_LEAVE_STATUS)));
        return  leave;
    }

    // Method to update the status of a leave demand and return true/false based on the success of the update
    public boolean updateLeaveStatus(int leaveId, String leaveStatus, int employeeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LEAVE_STATUS, leaveStatus);

        String whereClause = COLUMN_ID + " = ? AND " + COLUMN_EMPLOYEE_ID + " = ?";
        String[] whereArgs = {String.valueOf(leaveId), String.valueOf(employeeId)};

        int rowsAffected = db.update(TABLE_LEAVE, values, whereClause, whereArgs);
        return rowsAffected > 0;
    }

    // Method to retrieve all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_EMPLOYEE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                employee.setName(cursor.getString(cursor.getColumnIndex(COLUMN_EMPLOYEE_NAME)));
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return employeeList;
    }


}

