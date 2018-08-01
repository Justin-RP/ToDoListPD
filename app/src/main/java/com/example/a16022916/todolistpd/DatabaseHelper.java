package com.example.a16022916.todolistpd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by 16022916 on 22/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    //DATABASE_NAME
    private static final String TABLE_NAME = "toDoItems";
    private static final String DATABASE_NAME = "toDoListManager";
    private static final String COL0ID = "ID";
    private static final String COL1Name = "itemName";
    private static final String COL2Time = "itemTime";
    private static final String COL3Checked = "isChecked";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

//    public DatabaseHelper(CustomAdapter customAdapter) {
//        super(customAdapter, DATABASE_NAME, null, 1);
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("+COL0ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1Name + " TEXT,"
                + COL2Time + " TEXT," + COL3Checked  + " TEXT)";
    db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldDb, int newDb) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(ToDoItem toDoItem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        String strId = String.valueOf(toDoItem.getId());
        String strGetName = toDoItem.getName().toString();
        String strGetTime = String.valueOf(toDoItem.getTime());
        String strGetChecked = String.valueOf(toDoItem.isChecked());


        contentValues.put(COL0ID,strId);
        contentValues.put(COL1Name, strGetName);
        contentValues.put(COL2Time,strGetTime);
        contentValues.put(COL3Checked, strGetChecked);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if(result == -1){
            Log.v(TAG,"AddData Error: -1");

        } else {
            Log.v(TAG,"Data Added Successfully");
        }

    }

    public ToDoItem getData(int id){

        SQLiteDatabase db = this.getReadableDatabase();

//        String query  = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.query(TABLE_NAME, new String[] { COL0ID,
                        COL1Name, COL2Time , COL3Checked }, COL0ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        boolean boolCheck = false;
        if(cursor.getString(3).equalsIgnoreCase("true")){
            boolCheck = true;
        } else if (cursor.getString(3).equalsIgnoreCase("false")){
            boolCheck = false;
        } else {
            Log.v(TAG, "BoolCheck: " + cursor.getString(3));
        }

        ToDoItem toDoList = new ToDoItem(String.valueOf(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),boolCheck);

        return toDoList;
    }
    public ArrayList<ToDoItem> getAllItems() {
        ArrayList<ToDoItem> toDo = new ArrayList<ToDoItem>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                int time = Integer.parseInt(cursor.getString(2));

                boolean newIsChecked;
                if(cursor.getString(3).equalsIgnoreCase("true")){
                    newIsChecked = true;
                } else {
                    newIsChecked = false;
                }

                toDo.add(new ToDoItem(id,name,time,newIsChecked));

            } while (cursor.moveToNext());
        }

        // return contact list
        return toDo;
    }

    public int updateList(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL1Name , toDoItem.getName());
        values.put(COL2Time , toDoItem.getTime());
        values.put(COL3Checked , toDoItem.isChecked());
        return db.update(TABLE_NAME,  values, COL0ID + " = ?", new String[] { String.valueOf(toDoItem.getId())});
    }

    public void deleteItem(ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COL0ID + " = ?", new String[]{String.valueOf(toDoItem.getId())});
        db.close();
    }

    public void updateAllData(ArrayList<ToDoItem> toDoList){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for(int i = 0; i < toDoList.size(); i++){

            ToDoItem currentItem = toDoList.get(i);

            String strGetName = currentItem.getName().toString();
            String strGetTime = String.valueOf(currentItem.getTime());
            String strGetChecked = String.valueOf(currentItem.isChecked());


            values.put(COL1Name , strGetName);
            values.put(COL2Time , strGetTime);
            values.put(COL3Checked , strGetChecked);
            db.update(TABLE_NAME,  values, COL0ID + " = ?", new String[] { String.valueOf(currentItem.getId())});
        }

        db.close();


    }
}
