package com.example.sagii0707;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "san";
    private static final String TABLE_NAME2 = "ug";
    private static final String COL1 = "id";
    private static final String COL2 = "name";
    private static final String COL3 = "rank";
 //   private static final String COL3 = "Org";

    private static final String SEC0 = "ID";
    private static final String SEC1 = "ug";
    private static final String SEC2 = "order2";
    private static final String Mon = "mongol";
    private static final String RAT = "rat";

    public DatabaseHelper(Context context) {
        super(context, "sugii", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table san (id integer primary key autoincrement, name text, rank integer)";
        String createTable2 = "create table " + TABLE_NAME2 + " (id integer primary key autoincrement, " +
                SEC1 +" text, " + SEC2 + " integer, " + Mon + " text, "+ RAT + " integer  )";
        db.execSQL(createTable);
        db.execSQL(createTable2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean addData(String item, int star) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item);
        contentValues.put(COL3, star);
        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addData2(String item, int order, String mItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SEC1, item);
        contentValues.put(SEC2, order);
        contentValues.put(Mon, mItem);
        contentValues.put(RAT, 0);

        Log.d(TAG, "addData: Adding " + item + " " + order + " to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getData2(int order){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 +" WHERE " + SEC2 + " = '" + order + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getData3(int order){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 +" WHERE " + Mon + " = '" + order + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SEC0 + " FROM " + TABLE_NAME2 +
                " WHERE " + SEC1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getOrder(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor Questions(int orders){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + SEC1 + " FROM " + TABLE_NAME2 +
                " WHERE " + SEC2 + " = '" + orders + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor Answers(int orders){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Mon + " FROM " + TABLE_NAME2 +
                " WHERE " + SEC2 + " = '" + orders + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor Ug(int orders){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2 +
                " WHERE " + SEC2 + " = '" + orders + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME2 + " SET " + SEC1 +
                " = '" + newName + "' WHERE " + SEC0 + " = '" + id + "'" +
                " AND " + SEC1 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateName2(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();



        String query = "UPDATE " + TABLE_NAME2 + " SET " + Mon +
                " = '" + newName + "' WHERE " + SEC0 + " = '" + id + "'" +
                " AND " + Mon + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateRank(int rank, int order){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL3 +
                " = '" + rank + "' WHERE " + COL1 + " = " + order + " ;";


        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE "
                + SEC0 + " = '" + id + "'" +
                " AND " + SEC1 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public Cursor Count(int orders){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT (" + SEC1 + " ) FROM " + TABLE_NAME2 +
                " WHERE " + SEC2 + " = " + orders + "";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor Rating(int orders){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL3 + " FROM " + TABLE_NAME + " WHERE " +
                COL1 + " = '" + orders + "' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


}
