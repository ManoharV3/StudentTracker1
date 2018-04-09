package com.example.manoh.studenttrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by manoh on 3/28/2018.
 */

public class AttendanceDB extends SQLiteOpenHelper{
//    previous database name = AttendanceTrack
    public static final String DATABASE_NAME = "Attendance_Report.db";
    public static final String ATTENDANCE_TABLE_NAME = "Attendance";
    public static final String ATTENDANCE_COLUMN_ID = "id";
    public static final String ATTENDANCE_COLUMN_SESSION = "session_id";
    public static final String ATTENDANCE_COLUMN_STATUS = "status";
    public static final String ATTENDANCE_COLUMN_TIMESTAMP = "timestamp";

    private HashMap hp;

    public AttendanceDB(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Attendance " +
                        "(id integer primary key, session_id text,status text,timestamp text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        onCreate(db);
    }

    public boolean insertValues (int id, int value, String timestamp) {
//        LocationActivity loc_act = new LocationActivity();
//        loc_act.getLocation();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("session_id", id);
        contentValues.put("status", value);
        contentValues.put("timestamp", timestamp);

        db.insert("Attendance", null, contentValues);
        return true;
    }
    public String getData() {
        String result = "";
        SQLiteDatabase db = this.getReadableDatabase();
//        SELECT * FROM Table ORDER BY date(dateColumn) DESC Limit 1
//        SELECT *
//                FROM (
//                        SELECT *
//                                ORDER BY date ASC, time ASC
//                ) AS sub
//        GROUP BY name
        Cursor res =  db.rawQuery( "select * from Attendance order by date(timestamp)", null );
        if(res.moveToFirst())
        {
            String[] values = res.getColumnNames();
            do{
                for(String name : values)
                {
                    result += String.format("%s,", res.getString(res.getColumnIndex(name)));
                }
                result += ":::";
            }while(res.moveToNext());
        }
        return result;
    }

//    public String getTableAsString(SQLiteDatabase db, String tableName) {
//        Log.d(TAG, "getTableAsString called");
//        String tableString = String.format("Table %s:\n", tableName);
//        Cursor allRows  = db.rawQuery("SELECT * FROM " + tableName, null);
//        if (allRows.moveToFirst() ){
//            String[] columnNames = allRows.getColumnNames();
//            do {
//                for (String name: columnNames) {
//                    tableString += String.format("%s: %s\n", name,
//                            allRows.getString(allRows.getColumnIndex(name)));
//                }
//                tableString += "\n";
//
//            } while (allRows.moveToNext());
//        }
//
//        return tableString;
//    }




//    public Cursor getData(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from Attendance where id="+id+"", null );
//        return res;
//    }
//
//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
//        return numRows;
//    }
//
//    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", name);
//        contentValues.put("phone", phone);
//        contentValues.put("email", email);
//        contentValues.put("street", street);
//        contentValues.put("place", place);
//        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
//        return true;
//    }
//
//    public Integer deleteContact (Integer id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("contacts",
//                "id = ? ",
//                new String[] { Integer.toString(id) });
//    }
//
//    public ArrayList<String> getAllCotacts() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}
