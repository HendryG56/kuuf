package com.example.project_mcs_lab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "KuufDB.db";
    private static final Integer DB_VERSION = 1;

    public static final String TABLE_ACCOUNT = "Account";
    public static final String FIELD_USER_ID = "id";
    public static final String FIELD_USER_USERNAME = "username";
    public static final String FIELD_USER_PASSWORD = "password";
    public static final String FIELD_USER_PHONENUMBER = "phonenumber";
    public static final String FIELD_USER_DATEOFBIRTH = "dateofbirth";
    public static final String FIELD_USER_GENDER = "gender";
    public static final String FIELD_USER_NOMINAL = "nominal";

    private static final String CREATE_ACCOUNT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_ACCOUNT +" (" +
            FIELD_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            FIELD_USER_USERNAME + " TEXT NOT NULL," +
            FIELD_USER_PASSWORD + " TEXT NOT NULL," +
            FIELD_USER_PHONENUMBER + " TEXT NOT NULL," +
            FIELD_USER_DATEOFBIRTH + " TEXT NOT NULL," +
            FIELD_USER_GENDER + " TEXT NOT NULL," +
            FIELD_USER_NOMINAL + " INTEGER)";

    private static final String DROP_ACCOUNT = "DROP TABLE IF EXISTS " + TABLE_ACCOUNT;

    public static final String TABLE_PRODUCTS = "Products";
    public static final String FIELD_PRODUCT_ID = "product_id";
    public static final String FIELD_PRODUCT_NAME = "product_name";
    public static final String FIELD_PRODUCT_MIN_PLAYER = "product_min_player";
    public static final String FIELD_PRODUCT_MAX_PLAYER = "product_max_player";
    public static final String FIELD_PRODUCT_PRICE = "product_price";
    public static final String FIELD_PRODUCT_LATITUDE = "product_latitude";
    public static final String FIELD_PRODUCT_LONGITUDE = "product_longitude";

    private static final String CREATE_PRODUCTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS +" (" +
                    FIELD_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_PRODUCT_NAME + " TEXT NOT NULL," +
                    FIELD_PRODUCT_MIN_PLAYER + " INTEGER," +
                    FIELD_PRODUCT_MAX_PLAYER + " INTEGER," +
                    FIELD_PRODUCT_PRICE + " INTEGER," +
                    FIELD_PRODUCT_LATITUDE + " REAL," +
                    FIELD_PRODUCT_LONGITUDE + " REAL)";

    private static final String DROP_PRODUCTS= "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;

    public static final String TABLE_TRANSACTIONS = "Transactions";
    public static final String FIELD_TR_ID = "tr_id";
    public static final String FIELD_TR_USER_ID = "tr_user_id";
    public static final String FIELD_TR_PRODUCT_ID = "tr_product_id";
    public static final String FIELD_TR_DATE = "tr_date";

    private static final String CREATE_TRANSACTIONS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSACTIONS +" (" +
                    FIELD_TR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FIELD_TR_USER_ID + " INTEGER NOT NULL REFERENCES " + TABLE_ACCOUNT + "(" + FIELD_USER_ID + ") ON UPDATE CASCADE," +
                    FIELD_TR_PRODUCT_ID + " INTEGER NOT NULL REFERENCES " + TABLE_PRODUCTS + "(" + FIELD_PRODUCT_ID + ") ON UPDATE CASCADE," +
                    FIELD_TR_DATE + " TEXT NOT NULL)";

    private static final String DROP_TRANSACTIONS = "DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS;


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT);
        db.execSQL(CREATE_PRODUCTS);
        db.execSQL(CREATE_TRANSACTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_ACCOUNT);
        db.execSQL(DROP_PRODUCTS);
        db.execSQL(DROP_TRANSACTIONS);
        onCreate(db);
    }
}
