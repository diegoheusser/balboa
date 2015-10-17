package br.udesc.ceavi.cvfm.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import br.udesc.ceavi.cvfm.dao.core.DatabaseDefinitions;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE_ITEM =
            "CREATE TABLE " + DatabaseDefinitions.TABLE_NAME_ITEM + " ( " +
            DatabaseDefinitions.COLUMNS_NAMES_ITEM[0] + " INTEGER PRIMARY KEY, " +
            DatabaseDefinitions.COLUMNS_NAMES_ITEM[1] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_ITEM[2] + " INTEGER NOT NULL " +
            ") ";
    private static final String SQL_CREATE_TABLE_SOURCE =
            "CREATE TABLE " + DatabaseDefinitions.TABLE_NAME_SOURCE + " ( " +
            DatabaseDefinitions.COLUMNS_NAMES_SOURCE[0] + " INTEGER PRIMARY KEY, " +
            DatabaseDefinitions.COLUMNS_NAMES_SOURCE[1] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SOURCE[2] + " TEXT NOT NULL " +
            ") ";
    private static final String SQL_CREATE_TABLE_USER =
            "CREATE TABLE " + DatabaseDefinitions.TABLE_NAME_USER+ " ( " +
            DatabaseDefinitions.COLUMNS_NAMES_USER[0] + " INTEGER PRIMARY KEY, " +
            DatabaseDefinitions.COLUMNS_NAMES_USER[1] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_USER[2] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_USER[3] + " TEXT NOT NULL " +
            ") ";
    private static final String SQL_CREATE_TABLE_CONTROL =
            "CREATE TABLE " + DatabaseDefinitions.TABLE_NAME_CONTROL + " ( " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[0] + " INTEGER PRIMARY KEY, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[1] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[2] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[3] + " INTEGER DEFAULT 0, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[4] + " INTEGER NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[5] + " INTEGER NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[6] + " INTEGER NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[7] + " INTEGER NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_CONTROL[8] + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + DatabaseDefinitions.COLUMNS_NAMES_CONTROL[7] + ") REFERENCES " +
                    DatabaseDefinitions.TABLE_NAME_SOURCE + "(" + DatabaseDefinitions.COLUMNS_NAMES_SOURCE[0] + "), " +
            "FOREIGN KEY(" + DatabaseDefinitions.COLUMNS_NAMES_CONTROL[8] + ") REFERENCES " +
                    DatabaseDefinitions.TABLE_NAME_USER + "(" + DatabaseDefinitions.COLUMNS_NAMES_USER[0] + ") " +
            ") ";
    private static final String SQL_CREATE_TABLE_SEARCH =
            "CREATE TABLE " + DatabaseDefinitions.TABLE_NAME_SEARCH + " ( " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[0] + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[1] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[2] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[3] + " TEXT NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[4] + " REAL NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[5] + " TEXT, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[6] + " TEXT, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[7] + " TEXT, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[8] + " REAL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[9] + " INTEGER NOT NULL, " +
            DatabaseDefinitions.COLUMNS_NAMES_SEARCH[10] + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + DatabaseDefinitions.COLUMNS_NAMES_SEARCH[9] + ") REFERENCES " +
                    DatabaseDefinitions.TABLE_NAME_ITEM + "(" + DatabaseDefinitions.COLUMNS_NAMES_ITEM[0] + "), " +
            "FOREIGN KEY(" + DatabaseDefinitions.COLUMNS_NAMES_SEARCH[10] + ") REFERENCES " +
                    DatabaseDefinitions.TABLE_NAME_CONTROL + "(" + DatabaseDefinitions.COLUMNS_NAMES_CONTROL[0] + ") " +
            ") ";
    private static final String SQL_DROP_TABLE_ITEM = "DROP TABLE IF EXISTS " + DatabaseDefinitions.TABLE_NAME_ITEM;
    private static final String SQL_DROP_TABLE_SOURCE = "DROP TABLE IF EXISTS " + DatabaseDefinitions.TABLE_NAME_SOURCE;
    private static final String SQL_DROP_TABLE_USER = "DROP TABLE IF EXISTS " + DatabaseDefinitions.TABLE_NAME_USER;
    private static final String SQL_DROP_TABLE_CONTROL = "DROP TABLE IF EXISTS " + DatabaseDefinitions.TABLE_NAME_CONTROL;
    private static final String SQL_DROP_TABLE_SEARCH = "DROP TABLE IF EXISTS " + DatabaseDefinitions.TABLE_NAME_SEARCH;
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "custodevida";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("Database", "Creating database");
        db.execSQL(SQL_CREATE_TABLE_ITEM);
        db.execSQL(SQL_CREATE_TABLE_SOURCE);
        db.execSQL(SQL_CREATE_TABLE_USER);
        db.execSQL(SQL_CREATE_TABLE_CONTROL);
        db.execSQL(SQL_CREATE_TABLE_SEARCH);
        Log.i("Database", "Database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Database", "Updating database");
        Log.i("Database", "Deleting database");
        db.execSQL(SQL_DROP_TABLE_SEARCH);
        db.execSQL(SQL_DROP_TABLE_CONTROL);
        db.execSQL(SQL_DROP_TABLE_USER);
        db.execSQL(SQL_DROP_TABLE_SOURCE);
        db.execSQL(SQL_DROP_TABLE_ITEM);
        Log.i("Database", "Database deleted");
        Log.i("Database", "Updating database");
        onCreate(db);
        Log.i("Database", "Database updated");
    }
}
