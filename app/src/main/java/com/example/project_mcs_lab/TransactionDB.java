package com.example.project_mcs_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TransactionDB {
    private DBHelper dbHelper;

    public TransactionDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void insertTransaction(Transaction transaction) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_TR_USER_ID, transaction.getUser_id());
        cv.put(DBHelper.FIELD_TR_PRODUCT_ID, transaction.getProduct_id());
        cv.put(DBHelper.FIELD_TR_DATE, transaction.getTr_date());

        db.insert(DBHelper.TABLE_TRANSACTIONS, "N/A", cv);

        db.close();
    }

    public ArrayList<History> getTransaction(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_history = "SELECT " + DBHelper.FIELD_TR_ID + ", " + DBHelper.FIELD_PRODUCT_NAME + ", " + DBHelper.FIELD_TR_DATE + ", " + DBHelper.FIELD_PRODUCT_PRICE +
                " FROM " + DBHelper.TABLE_TRANSACTIONS + " t " +
                " JOIN " + DBHelper.TABLE_PRODUCTS + " p " +
                " ON t." + DBHelper.FIELD_TR_PRODUCT_ID + " = p." + DBHelper.FIELD_PRODUCT_ID +
                " WHERE t. " + DBHelper.FIELD_TR_USER_ID + " = " + id;

        Cursor cursor = db.rawQuery(get_history, null);

        ArrayList<History> histories = new ArrayList<>();

        if (cursor.moveToFirst() == true) {
            do {
                int idtr = cursor.getInt(0);
                String productname = cursor.getString(1);
                String tr_date = cursor.getString(2);
                int productprice = cursor.getInt(3);

                History history1 = new History();
                history1.setId_tr(idtr);
                history1.setProduct_name(productname);
                history1.setTr_date(tr_date);
                history1.setProduct_price(productprice);

                histories.add(history1);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return histories;
    }

    public void deleteTransaction(int tr_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String delete_transactions = " DELETE FROM " + DBHelper.TABLE_TRANSACTIONS +
                " WHERE " + DBHelper.FIELD_TR_ID + " = " + tr_id + ";";

        db.execSQL(delete_transactions);
    }
}
