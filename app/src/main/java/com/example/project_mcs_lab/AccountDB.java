package com.example.project_mcs_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AccountDB {
    private DBHelper dbHelper;

    public AccountDB(Context ctx){
        dbHelper =new DBHelper(ctx);
    }

    public void insertAccount(Account account){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_USER_USERNAME, account.getUsername());
        cv.put(DBHelper.FIELD_USER_PASSWORD, account.getPassword());
        cv.put(DBHelper.FIELD_USER_PHONENUMBER, account.getPhonenumber());
        cv.put(DBHelper.FIELD_USER_DATEOFBIRTH, account.getDateofbirth());
        cv.put(DBHelper.FIELD_USER_GENDER, account.getGender());
        cv.put(DBHelper.FIELD_USER_NOMINAL, account.getNominal());

        db.insert(DBHelper.TABLE_ACCOUNT, "N/A", cv);

        db.close();
    }

    public Account getAccount(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(DBHelper.TABLE_ACCOUNT, null, selection, selectionArgs, null, null, null);

        Account account = null;

        if(cursor.moveToFirst()){
            account = new Account();
            account.setUserid(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_USER_ID)));
            account.setUsername(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_USERNAME)));
            account.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_PASSWORD)));
            account.setPhonenumber(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_PHONENUMBER)));
            account.setDateofbirth(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_DATEOFBIRTH)));
            account.setGender(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_USER_GENDER)));
            account.setNominal(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_USER_NOMINAL)));
        }

        cursor.close();
        db.close();
        return account;
    }

    public int countTableSize(){
        String count = "SELECT * FROM " + DBHelper.TABLE_ACCOUNT;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        int count_size = cursor.getCount();

        cursor.close();
        db.close();
        return count_size;
    }

    public void updateNominal(Account account, int id, int nominal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = account.getNominal() + nominal;
        cv.put(DBHelper.FIELD_USER_NOMINAL, temp);

        db.update(DBHelper.TABLE_ACCOUNT, cv, whereClause, whereClauseArgs);

        db.close();
    }

    public void minusNominal(Account account, int id, int nominal){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = account.getNominal() - nominal;
        cv.put(DBHelper.FIELD_USER_NOMINAL, temp);

        db.update(DBHelper.TABLE_ACCOUNT, cv, whereClause, whereClauseArgs);

        db.close();
    }
}
