package com.regent.tech.med_manager.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for Med-Manager app. Manages database creation and version management.
 */

public class MedicineHelperClass extends SQLiteOpenHelper{

    public static final String TAG = MedicineHelperClass.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "medicine.db";

    /** Database version*/
    private static final int DATABASE_VERSION = 1;

    public MedicineHelperClass(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the database
        sqLiteDatabase.execSQL(MedicineContract.MedicineEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
