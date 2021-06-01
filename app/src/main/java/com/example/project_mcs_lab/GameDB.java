package com.example.project_mcs_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GameDB {
    private DBHelper dbHelper;

    public GameDB(Context ctx){
        dbHelper = new DBHelper(ctx);
    }

    public void insertgame(Game game){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.FIELD_PRODUCT_NAME, game.getGamename());
        cv.put(DBHelper.FIELD_PRODUCT_MIN_PLAYER, game.getMinplayer());
        cv.put(DBHelper.FIELD_PRODUCT_MAX_PLAYER, game.getMaxplayer());
        cv.put(DBHelper.FIELD_PRODUCT_PRICE, game.getGameprice());
        cv.put(DBHelper.FIELD_PRODUCT_LATITUDE, game.getLatitude());
        cv.put(DBHelper.FIELD_PRODUCT_LONGITUDE, game.getLongitude());

        db.insert(DBHelper.TABLE_PRODUCTS, "N/A", cv);

        db.close();
    }

    public Game getGame(int productid){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "product_id=?";
        String[] selectionArgs = {"" + productid};

        Cursor cursor = db.query(DBHelper.TABLE_PRODUCTS, null, selection, selectionArgs, null, null, null);

        Game game = null;
        cursor.moveToFirst();
//        if(cursor.moveToFirst()){
            game = new Game();
            game.setProduct_id(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_ID)));
            game.setGamename(cursor.getString(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_NAME)));
            game.setMinplayer(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_MIN_PLAYER)));
            game.setMaxplayer(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_MAX_PLAYER)));
            game.setGameprice(cursor.getInt(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_PRICE)));
            game.setLatitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_LATITUDE)));
            game.setLongitude(cursor.getDouble(cursor.getColumnIndex(DBHelper.FIELD_PRODUCT_LONGITUDE)));
//        }

        cursor.close();
        db.close();
        return game;
    }

    public int countTableSize(){
        String count = "SELECT * FROM " + DBHelper.TABLE_PRODUCTS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        int count_size = cursor.getCount();

        cursor.close();
        db.close();
        return count_size;
    }
}
