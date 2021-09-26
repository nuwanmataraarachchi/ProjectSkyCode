package com.example.listify.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelperTasks extends SQLiteOpenHelper {

    private Context context;

    public static final String DATABASE_NAME = "Listify.db";

    public DBHelperTasks( Context context) { super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + Master.Tasks.TABLE_NAME + " (" +
                        Master.Tasks._ID+ " INTEGER PRIMARY KEY," +
                        Master.Tasks.COLUMN_NAME_TITLE+ " TEXT," +
                        Master.Tasks.COLUMN_NAME_DATE+ " TEXT," +
                        Master.Tasks.COLUMN_NAME_START_TIME+ " TEXT," +
                        Master.Tasks.COLUMN_NAME_END_TIME+ " TEXT," +
                        Master.Tasks.COLUMN_NAME_DURATION+ " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public long addTask(String title, String date, String startTime, String endTime, String duration){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Master.Tasks.COLUMN_NAME_TITLE, title);
        values.put(Master.Tasks.COLUMN_NAME_DATE, date);
        values.put(Master.Tasks.COLUMN_NAME_START_TIME, startTime);
        values.put(Master.Tasks.COLUMN_NAME_END_TIME, endTime);
        values.put(Master.Tasks.COLUMN_NAME_DURATION, duration);

        return db.insert(Master.Tasks.TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Cursor readAllData(){
        String quarry = "SELECT * FROM " + Master.Tasks.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(quarry, null);
        }
        return cursor;
    }

    public void updateTask(String row_id, String title, String date, String startTime, String endTime, String duration ){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Master.Tasks.COLUMN_NAME_TITLE, title);
        cv.put(Master.Tasks.COLUMN_NAME_DATE, date);
        cv.put(Master.Tasks.COLUMN_NAME_START_TIME, startTime);
        cv.put(Master.Tasks.COLUMN_NAME_END_TIME, endTime);
        cv.put(Master.Tasks.COLUMN_NAME_DURATION, duration);

        long result =  db.update(Master.Tasks.TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteTask(String row_id){
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(Master.Tasks.TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
