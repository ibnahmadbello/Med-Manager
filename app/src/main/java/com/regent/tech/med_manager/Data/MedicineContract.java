package com.regent.tech.med_manager.Data;

import android.provider.BaseColumns;

public class MedicineContract {

    private MedicineContract(){}

    public static final class MedicineEntry implements BaseColumns{

        /** Name of database table for medicine */
        public final static String TABLE_NAME = "medicine";

        /**
         * Unique ID number for the medicine (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String COLUMN_ID = BaseColumns._ID;

        /**
         * Name of the medicine.
         *
         * Type: TEXT
         */
        public final static String COLUMN_MEDICINE_NAME = "medName";

        /**
         * Description of the medicine.
         *
         * Type: TEXT
         */
        public final static String COLUMN_MEDICINE_DECRIPTION = "medDescription";

        /**
         * Interval of the medicine.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_MEDICINE_INTERVAL = "medInterval";

        /**
         * Start date of the medicine.
         *
         * Type: DATE
         */
        public final static String COLUMN_MEDICINE_START_DATE = "startDate";

        /**
         * End date of the medicine.
         *
         * Type: TEXT
         */
        public final static String COLUMN_MEDICINE_END_DATE = "endDate";

        /**
         * Create table SQL query
         */
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "
                        + COLUMN_MEDICINE_DECRIPTION + " TEXT NOT NULL, "
                        + COLUMN_MEDICINE_INTERVAL + " INTEGER NOT NULL, "
                        + COLUMN_MEDICINE_START_DATE + " DATE NOT_NULL DEFAULT CURRENT_TIMESTAMP, "
                        + COLUMN_MEDICINE_END_DATE + " DATE NOT NULL, "
                        + ")";
    }

}
