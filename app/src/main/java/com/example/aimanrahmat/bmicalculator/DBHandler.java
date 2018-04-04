package com.example.aimanrahmat.bmicalculator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION = 2;

    // Database Name
    public static final String DATABASE_NAME = "RecordDB";

    // Record table name
    public static final String TABLE_RECORD = "recordtable";

    // Record Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_BMI = "bmi";
    public static final String KEY_DATE = "date";

    public DBHandler(CalculatorActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHandler(RecordActivity context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORD_TABLE = "CREATE TABLE " + TABLE_RECORD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BMI + " TEXT,"+KEY_DATE+ " TEXT)";
        db.execSQL(CREATE_RECORD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORD);

        // Creating tables again
        onCreate(db);
    }

    public void addBmi(Record record) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BMI, record.getBmi()); // BMI value
        values.put(KEY_DATE, record.getDate());

        // Inserting Row
        db.insert(TABLE_RECORD, null, values);
        db.close(); // Closing database connection
    }

    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RECORD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setBmi(cursor.getString(1));
                record.setDate(cursor.getString(2));

                // Adding record to list
                recordList.add(record);
            } while (cursor.moveToNext());
        }

        // return record list
        return recordList;
    }

    public void deleteAllBmi() {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_RECORD;
        db.execSQL(deleteQuery);
        db.close();   // Closing database connection
    }

}
